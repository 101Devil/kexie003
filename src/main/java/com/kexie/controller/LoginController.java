package com.kexie.controller;

import com.kexie.common.ResponseResult;
import com.kexie.common.ResponseResultEnum;
import com.kexie.entity.Student;
import com.kexie.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class LoginController {



    @GetMapping("/login")
    public ModelAndView toLoginPage(){
        return new ModelAndView("login");
    }

    /**
     * @Description : 用户登录
     * @methodName : login
     * @param student :
     * @return : com.xwkj.cost.common.ResponseResult
     * @exception : 
     * @author : 张俊龙
     */
    @PostMapping("/loginUser")
    public ResponseResult login(Student student){
        ResponseResult responseResult = null;
        try {
            System.out.println(student);
            UsernamePasswordToken token = new UsernamePasswordToken();
            token.setUsername(student.getStudentId());
            String password = MD5Util.getMD5(student.getPassword());
            token.setPassword(password.toCharArray());
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                subject.login(token);
            }
            log.info("登录成功！");
            responseResult = ResponseResult.success(ResponseResultEnum.SUCCESS.getCode(), null,"登录成功！");
        } catch (IncorrectCredentialsException ice) {
            return ResponseResult.failure("密码错误");
        } catch (UnknownAccountException uae) {
            return ResponseResult.failure("账号不存在");
        } catch (LockedAccountException e) {
            return ResponseResult.failure("账号被锁定");
        } catch (ExcessiveAttemptsException eae) {
            return ResponseResult.failure("操作频繁，请稍后再试");
        }catch(Exception e){
            log.error("登录失败",e);
            responseResult = ResponseResult.failure("登录失败");
        }
        return responseResult;
    }

    /**
     * @Description : 取消登录
     * @methodName : logout
     * @return : org.springframework.web.servlet.ModelAndView
     * @exception : 
     * @author : 张俊龙
     */
    @GetMapping("/logout")
    public ModelAndView logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();
        }
        return new ModelAndView("redirect:login");
    }


}
