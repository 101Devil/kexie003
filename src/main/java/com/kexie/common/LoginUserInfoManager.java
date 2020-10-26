package com.kexie.common;

import com.kexie.entity.Student;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;

/**
 * 获取当前登录的用户信息
 */
public class LoginUserInfoManager {
    private static Log log = LogFactory.getLog(LoginUserInfoManager.class);
    /**
     * 获取当前登录的用户信息
     * @return
     */
    public static Student getStudent(){
        Object object = SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        log.info(object);
        return (Student)object;
    }

}
