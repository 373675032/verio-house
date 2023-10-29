package com.verio.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.verio.entity.Room;
import org.springframework.stereotype.Repository;

/**
 * 房屋数据库访问
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Repository
public interface RoomDao extends BaseMapper<Room> {

}
