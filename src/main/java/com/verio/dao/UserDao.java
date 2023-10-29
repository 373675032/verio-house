package com.verio.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.verio.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户数据库访问
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Repository
public interface UserDao extends BaseMapper<User> {

}
