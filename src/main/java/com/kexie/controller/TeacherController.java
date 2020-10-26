package com.kexie.controller;


import com.kexie.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/kexie/teacher")
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;

}

