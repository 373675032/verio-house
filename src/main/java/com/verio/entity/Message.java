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
 * 消息实体
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = -13012886727763475L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 房子ID
     */
    private Integer roomId;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 发送人ID
     */
    private Integer sendId;

    /**
     * 接受人ID
     */
    private Integer acceptId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态
     */
    private String status;

    /**
     * 处理意见
     */
    private String remark;

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

    @TableField(exist = false)
    private RoomOrder roomOrder;

    @TableField(exist = false)
    private Room room;

    @TableField(exist = false)
    private User sender;

    @TableField(exist = false)
    private User accepter;
}
