package com.verio.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.verio.dao.CommentDao;
import com.verio.entity.Comment;
import com.verio.utils.Assert;
import com.verio.utils.BeanUtils;
import com.verio.utils.VariableNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 评价服务
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Service
public class CommentService extends BaseService<Comment> {

    @Autowired
    protected CommentDao selfDao;

    @Override
    public List<Comment> query(Comment o) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
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
    public List<Comment> all() {
        return query(null);
    }

    @Override
    public Comment save(Comment o) {
        if (Assert.isEmpty(o.getId())) {
            selfDao.insert(o);
        } else {
            selfDao.updateById(o);
        }
        return selfDao.selectById(o.getId());
    }

    @Override
    public Comment get(Serializable id) {
        return selfDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return selfDao.deleteById(id);
    }
}