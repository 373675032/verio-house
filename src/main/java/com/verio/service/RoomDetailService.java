package com.verio.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.verio.dao.RoomDetailDao;
import com.verio.entity.RoomDetail;
import com.verio.utils.Assert;
import com.verio.utils.BeanUtils;
import com.verio.utils.VariableNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 房屋详情服务
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Service
public class RoomDetailService extends BaseService<RoomDetail> {

    @Autowired
    protected RoomDetailDao selfDao;

    @Override
    public List<RoomDetail> query(RoomDetail o) {
        QueryWrapper<RoomDetail> wrapper = new QueryWrapper<>();
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
    public List<RoomDetail> all() {
        return query(null);
    }

    @Override
    public RoomDetail save(RoomDetail o) {
        if (Assert.isEmpty(o.getId())) {
            selfDao.insert(o);
        } else {
            selfDao.updateById(o);
        }
        return selfDao.selectById(o.getId());
    }

    @Override
    public RoomDetail get(Serializable id) {
        return selfDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return selfDao.deleteById(id);
    }
}