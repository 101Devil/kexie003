package com.kexie.service.impl;

import com.kexie.entity.Role;
import com.kexie.mapper.RoleMapper;
import com.kexie.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
