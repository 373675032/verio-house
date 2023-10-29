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
 * 订单实体
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("room_order")
public class RoomOrder implements Serializable {

    private static final long serialVersionUID = -80953369372984856L;

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
     * 用户ID
     */
    private Integer userId;

    /**
     * 房东ID
     */
    private Integer ownerId;

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 订单流水号
     */
    private String payOrder;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 价格
     */
    private Double payMoney;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件文件
     */
    private String attachment;

    /**
     * 状态
     */
    private String status;

    /**
     * 支付时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

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
     * 开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date fromTime;

    /**
     * 结束时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date toTime;

    @TableField(exist = false)
    private Room room;
    @TableField(exist = false)
    private RoomDetail detail;
    @TableField(exist = false)
    private User owner;
    @TableField(exist = false)
    private User user;
}
