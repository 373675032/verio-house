package com.verio.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.verio.entity.RoomOrder;
import org.springframework.stereotype.Repository;

/**
 * 订单数据库访问
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Repository
public interface RoomOrderDao extends BaseMapper<RoomOrder> {

}
