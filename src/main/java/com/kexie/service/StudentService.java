package com.kexie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kexie.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kexie.util.PageUtil;

import java.util.List;

/**
 * <p>
 * 学生信息表 服务类
 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
public interface StudentService extends IService<Student> {
    //获取学生列表信息
    IPage<Student> getStudentList(Student student, PageUtil pageUtil);
    //添加学生信息
    boolean addStudent(Student student);
    //修改学生信息
    void updateStudent(Student student);
    //删除学生信息
    void deleteStudent(String studentId);
    //重置密码
    void resetPassword(Student student);
    //修改密码
    boolean updateStudentPassword(String oldPassword, String password);
    //根据id获取学生信息
    List<Object> getStudentById(String studentId);
}
