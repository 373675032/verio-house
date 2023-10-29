package com.verio.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.verio.dao.FavorDao;
import com.verio.entity.Favor;
import com.verio.utils.Assert;
import com.verio.utils.BeanUtils;
import com.verio.utils.VariableNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 收藏服务
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Service
public class FavorService extends BaseService<Favor> {

    @Autowired
    protected FavorDao selfDao;

    @Override
    public List<Favor> query(Favor o) {
        QueryWrapper<Favor> wrapper = new QueryWrapper<>();
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
    public List<Favor> all() {
        return query(null);
    }

    @Override
    public Favor save(Favor o) {
        if (Assert.isEmpty(o.getId())) {
            List<Favor> query = query(Favor.builder().userId(o.getUserId()).roomId(o.getRoomId()).build());
            if (Assert.isEmpty(query)) {
                selfDao.insert(o);
            } else {
                o = query.get(0);
            }
        } else {
            selfDao.updateById(o);
        }
        return selfDao.selectById(o.getId());
    }

    @Override
    public Favor get(Serializable id) {
        return selfDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return selfDao.deleteById(id);
    }
}