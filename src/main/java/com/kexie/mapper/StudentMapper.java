package com.kexie.mapper;

import com.kexie.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
 * <p>
 * 学生信息表 Mapper 接口
 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
