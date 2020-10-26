package com.kexie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 学生信息表
 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Student对象", description="学生信息表")
public class Student implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "学号")
    @TableId(value = "studentId", type = IdType.ID_WORKER)
    private String studentId;

    @ApiModelProperty(value = "学生姓名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "学院")
    private String xueyuan;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "班级")
    private String clas;

    @ApiModelProperty(value = "角色")
    private Integer role;

    @ApiModelProperty(value = "启用状态（1：启用  2：不启用）")
    @TableLogic
    private Integer statu;


}
