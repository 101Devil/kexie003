package com.kexie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kexie.common.LoginUserInfoManager;
import com.kexie.entity.Student;
import com.kexie.mapper.StudentMapper;
import com.kexie.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kexie.util.MD5Util;
import com.kexie.util.PageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 学生信息表 服务实现类
 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public IPage<Student> getStudentList(Student student, PageUtil pageUtil) {
        IPage<Student> iPage = new Page<>(pageUtil.getPage(),pageUtil.getLimit());
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if (student.getName() != null && !"".equals(student.getName())){
            queryWrapper.like("name",student.getName());
        }
        queryWrapper.eq("statu",1);
        iPage = studentMapper.selectPage(iPage,queryWrapper);
        return iPage;
    }

    @Override
    public boolean addStudent(Student student) {
        student.setPassword(MD5Util.getMD5("123456"));
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("studentId",student.getStudentId());
        queryWrapper.eq("statu",1);
        Student info = studentMapper.selectOne(queryWrapper);
        if (info != null){
            return false;
        }
        studentMapper.insert(student);
        return true;
    }

    @Override
    public void updateStudent(Student student) {
       studentMapper.updateById(student);
    }

    @Override
    public void deleteStudent(String studentId) {
        Student student=new Student();
        student.setStudentId(studentId);
        student.setStatu(0);
        studentMapper.updateById(student);
    }

    @Override
    public void resetPassword(Student student) {
        student.setPassword(MD5Util.getMD5("123456"));
        studentMapper.updateById(student);
    }

    @Override
    public boolean updateStudentPassword(String oldPassword, String password) {
        Student student = LoginUserInfoManager.getStudent();
        if (!student.getPassword().equals(MD5Util.getMD5(oldPassword))){
            return false;
        }
        Student info = new Student();
        info.setStudentId(student.getStudentId());
        info.setPassword(MD5Util.getMD5(password));
        studentMapper.updateById(info);
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }
        return true;
    }

    @Override
    public List<Object> getStudentById(String studentId) {
        List<Object> list = new ArrayList<>();
        Student student = studentMapper.selectById(studentId);
        list.add(student);
        return list;
    }
}
