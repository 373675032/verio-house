package com.verio.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 房屋实体
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("room")
public class Room implements Serializable {

    private static final long serialVersionUID = -49534036834714000L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 房东ID
     */
    private Integer ownerId;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 月租价格
     */
    private Double monthPrice;

    /**
     * 所在地
     */
    private String location;

    /**
     * 描述
     */
    private String info;

    /**
     * 内容
     */
    private String content;

    /**
     * 封面
     */
    private String img;

    /**
     * 图片
     */
    private String images;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 浏览量
     */
    private Integer lookCount;

    @TableField(exist = false)
    private RoomDetail detail;

    @TableField(exist = false)
    private Integer favorId;

    @TableField(exist = false)
    private User user;

}
