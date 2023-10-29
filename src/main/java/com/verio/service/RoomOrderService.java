package com.verio.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.verio.dao.RoomOrderDao;
import com.verio.entity.RoomOrder;
import com.verio.utils.Assert;
import com.verio.utils.BeanUtils;
import com.verio.utils.VariableNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 订单服务
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Service
public class RoomOrderService extends BaseService<RoomOrder> {

    @Autowired
    protected RoomOrderDao selfDao;

    @Override
    public List<RoomOrder> query(RoomOrder o) {
        QueryWrapper<RoomOrder> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtils.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return selfDao.selectList(wrapper);
    }

    @Override
    public List<RoomOrder> all() {
        return query(null);
    }

    @Override
    public RoomOrder save(RoomOrder o) {
        if (Assert.isEmpty(o.getId())) {
            selfDao.insert(o);
        } else {
            selfDao.updateById(o);
        }
        return selfDao.selectById(o.getId());
    }

    @Override
    public RoomOrder get(Serializable id) {
        return selfDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return selfDao.deleteById(id);
    }
}