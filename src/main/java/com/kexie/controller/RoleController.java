package com.kexie.controller;


import com.kexie.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/kexie/role")
public class RoleController {
 @Autowired
    private RoleServiceImpl roleService;
}

