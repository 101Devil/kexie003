package com.kexie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 获奖信息表

 * </p>
 *
 * @author 张俊龙
 * @since 2020-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Prize对象", description="获奖信息表 ")
public class Prize implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "比赛名称 ")
    @TableField("nontestName")
    private String nontestName;

    @ApiModelProperty(value = "作品名称")
    @TableField("workName")
    private String workName;

    @ApiModelProperty(value = "指导教师")
    private String teacher;

    @ApiModelProperty(value = "赛事级别")
    private String level;

    @ApiModelProperty(value = "获奖等级")
    private String grade;

    @ApiModelProperty(value = "获奖时间")
    private Date winTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("Create_time")
    private Date createTime;

    @ApiModelProperty(value = "图片")
    @TableField("pic")
    private String pic;

    @ApiModelProperty(value = "用户学号")
    @TableField("studentId")
    private String studentId;

    @ApiModelProperty(value = "启用状态（1：启用  2：不启用）")
    @TableLogic
    private Integer statu;


}
