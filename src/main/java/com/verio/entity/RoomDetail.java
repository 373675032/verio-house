package com.verio.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 房屋详情实体
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("room_detail")
public class RoomDetail implements Serializable {

    private static final long serialVersionUID = 707297687564965826L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 房间ID
     */
    private Integer roomId;

    /**
     * 房间数量
     */
    private Integer bedroomCount;

    /**
     * 客厅数量
     */
    private Integer parlourCount;

    /**
     * 卫生间数量
     */
    private Integer restroomCount;

    /**
     * 浴室数量
     */
    private Integer bathroomCount;

    /**
     * 大小
     */
    private String capacity;

    /**
     * 车库数量
     */
    private Integer garage;

    /**
     * 小区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 类型
     */
    private String type;

    /**
     * 建造年限
     */
    private String buildYear;

    /**
     * 状态
     */
    private String status;

    /**
     * 是否有电梯
     */
    private String elevator;

    /**
     * 是否有厨房
     */
    private String kitchen;

    /**
     * 免费Wi-Fi
     */
    private String freeWifi;

    /**
     * 窗户
     */
    private String window;

    /**
     * 是否有地铁
     */
    private String metro;

    /**
     * 租赁方式
     */
    private String rentType;

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

}
