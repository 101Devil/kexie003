package com.kexie.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kexie.common.ResponseResult;
import com.kexie.common.ResponseResultEnum;
import com.kexie.entity.Student;
import com.kexie.service.impl.RoleServiceImpl;
import com.kexie.service.impl.StudentServiceImpl;
import com.kexie.util.PageUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 学生信息表 前端控制器
 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
@Slf4j
@RestController
@Api(tags = "学生表接口")
@RequestMapping("/kexie/student")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private RoleServiceImpl roleService;
    /**
     * @Description : 跳转到添加学生页面
     * @methodName : toAddStudent
     * @return : org.springframework.web.servlet.ModelAndView
     * @exception :
     * @author : 张俊龙
     */
    @GetMapping("/toAddStudent")
    public ModelAndView toAddStudent(Map<String,Object> map){
//        QueryWrapper<RoleInfo> queryWrapper = new QueryWrapper<>();
////        queryWrapper.eq("is_enable",1);
////        List<RoleInfo> list = roleInfoService.list(queryWrapper);
////        map.put("roleInfoList",list);
////        return new ModelAnd(View("user/user-add");
        return new ModelAndView("Student/student-add");
    }
    /**
     * @Description : 跳转到修改学生页面
     * @methodName : toUpdateUserInfoPage
     * @return : ModelAndView
     * @exception :
     * @author : 张俊龙
     */
    @GetMapping("/toUpdateStudentPage")
    public ModelAndView toUpdateStudentPage(){
        return new ModelAndView("Student/student-edit");
    }
    /**
     * @Description : 获取学生列表信息
     * @methodName : getStudentList
     * @param student :
     * @param pageUtil :
     * @return : com.kexie.storage.common.ResponseResult
     * @exception :
     * @author : 张俊龙
     */
    @GetMapping("/getStudentList")
    public ResponseResult getStudentList(Student student, PageUtil pageUtil){
        ResponseResult responseResult = null;
        try {
            IPage<Student> iPage = studentService.getStudentList(student,pageUtil);
            log.info("获取学生列表信息成功！");
            responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(),iPage,"获取学生列表信息成功！");
        } catch(Exception e){
            log.error("获取学生列表信息失败！",e);
            responseResult = ResponseResult.failure("获取学生列表信息失败！");
        }
        return responseResult;
    }
    /**
     * @Description : 添加学生信息
     * @methodName : addStudent
     * @param student :
     * @return : com.kexie.storage.common.ResponseResult
     * @exception :
     * @author : 张俊龙
     */
    @PostMapping("/addStudent")
    public ResponseResult addStudent(Student student){
        ResponseResult responseResult = null;
        try {
            boolean b =studentService.addStudent(student);
            if (b){
                log.info("添加学生信息成功！");
                responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(),null,"添加学生信息成功！");
            }else {
                log.info("用户名重复，添加学生信息失败！");
                responseResult = ResponseResult.failure("用户名重复，添加学生信息失败！");
            }
        } catch(Exception e){
            log.error("添加学生失败！",e);
            responseResult = ResponseResult.failure("添加学生信息失败！");
        }
        return responseResult;
    }

    /**
     * @Description : 修改学生信息
     * @methodName : updateStudent
     * @param student :
     * @return : com.kexie.storage.common.ResponseResult
     * @exception :
     * @author : 张永辉
     */
    @PutMapping("/updateStudent")
    public ResponseResult updateUserInfo(Student student) {
        ResponseResult responseResult = null;
        try {
            studentService.updateStudent(student);
            log.info("修改学生信息成功！");
            responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(),null,"修改学生信息成功！");
        } catch(Exception e){
            log.error("修改学生信息失败！",e);
            responseResult = ResponseResult.failure("修改学生信息失败！");
        }
        return responseResult;
    }

    /**
     * @Description : 删除学生信息
     * @methodName : deleteStudent
     * @param id :
     * @return : com.kexie.storage.common.ResponseResult
     * @exception :
     * @author : 张俊龙
     */
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseResult deleteStudent(@PathVariable("id") String id) {
        ResponseResult responseResult = null;
        try {
            studentService.deleteStudent(id);
            log.info("删除学生信息成功！");
            responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(),null,"删除学生信息成功！");
        } catch(Exception e){
            log.error("删除学生信息失败！",e);
            responseResult = ResponseResult.failure("删除学生信息失败！");
        }
        return responseResult;
    }

    /**
     * @Description : 根据id获取学生信息
     * @methodName : getStudentById
     * @param id :
     * @return : com.kexie.storage.common.ResponseResult
     * @exception :
     * @author : 张俊龙
     */
    @GetMapping("/getStudentById/{id}")
    public ResponseResult getStudentById(@PathVariable("id") String id){
        ResponseResult responseResult = null;
        try {
            List<Object> list = studentService.getStudentById(id);
            log.info("根据id获取学生信息成功");
            responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(),list,"根据id获取学生信息成功");
        } catch(Exception e){
            log.error("根据id获取学生信息失败",e);
            responseResult = ResponseResult.failure("根据id获取学生信息失败");
        }
        return responseResult;
    }

    /**
     * @Description : 重置密码
     * @methodName : resetPassword
     * @param student :
     * @return : com.kexie.storage.common.ResponseResult
     * @exception :
     * @author : 张俊龙
     */
    @PutMapping("/resetPassword")
    public ResponseResult resetPassword(Student student) {
        ResponseResult responseResult = null;
        try {
            studentService.resetPassword(student);
            log.info("重置密码成功");
            responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(),null,"重置密码成功");
        } catch(Exception e){
            log.error("重置密码失败",e);
            responseResult = ResponseResult.failure("重置密码失败");
        }
        return responseResult;
    }

    /**
     * @Description : 修改学生密码
     * @methodName : updatePassword
     * @param password :
     * @return : com.kexie.storage.common.ResponseResult
     * @exception :
     * @author : 张俊龙
     */
    @PutMapping("/updatePassword")
    public ResponseResult updatePassword(String oldPassword, String password){
        ResponseResult responseResult = null;
        try {
            boolean b =studentService.updateStudentPassword(oldPassword,password);
            if (b){
                log.info("修改学生密码成功");
                responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(),null,"修改学生密码成功");
            }else {
                log.info("原始密码错误！");
                responseResult = ResponseResult.failure("原始密码错误！");
            }
        } catch(Exception e){
            log.error("修改学生密码失败",e);
            responseResult = ResponseResult.failure("修改学生密码失败");
        }
        return responseResult;
    }

    /**
     * @Description : 获取学生列表，用于下拉框
     * @methodName : getStudents
     * @return : com.kexie.storage.common.ResponseResult
     * @exception :
     * @author : 张俊龙
     */
    @GetMapping("/getStudents")
    public ResponseResult getStudents(){
        ResponseResult responseResult = null;
        try {
            QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("is_enable",1);
            List<Student> list = studentService.list(queryWrapper);
            log.info("获取学生列表，用于下拉框成功");
            responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(),list,"获取学生列表，用于下拉框成功");
        } catch(Exception e){
            log.error("获取学生列表，用于下拉框失败",e);
            responseResult = ResponseResult.failure("获取学生列表，用于下拉框失败");
        }
        return responseResult;
    }
}

