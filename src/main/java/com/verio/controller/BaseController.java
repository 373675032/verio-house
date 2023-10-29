package com.verio.controller;

import com.verio.dto.Resp;
import com.verio.dto.RespResult;
import com.verio.entity.User;
import com.verio.service.*;
import com.verio.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 基础控制器
 * <p>
 * ==========================================================================
 * 郑重说明：本项目免费开源！原创作者为：薛伟同学，严禁私自出售。
 * ==========================================================================
 * B站账号：薛伟同学
 * 微信公众号：薛伟同学
 * 作者博客：http://xuewei.world
 * ==========================================================================
 * 陆陆续续总会收到粉丝的提醒，总会有些人为了赚取利益倒卖我的开源项目。
 * 不乏有粉丝朋友出现钱付过去，那边只把代码发给他就跑路的，最后还是根据线索找到我。。
 * 希望各位朋友擦亮慧眼，谨防上当受骗！
 * ==========================================================================
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
public class BaseController<T> {

    @Autowired
    protected UserService userService;
    @Autowired
    protected CommentService commentService;
    @Autowired
    protected FavorService favorService;
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected RoomService roomService;
    @Autowired
    protected RoomDetailService roomDetailService;
    @Autowired
    protected RoomOrderService roomOrderService;

    @Autowired
    protected BaseService<T> service;

    protected User loginUser;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    /**
     * 通用保存接口
     */
    @ResponseBody
    @PostMapping("save")
    public String save(T obj) {
        if (Assert.isEmpty(obj)) {
            return Resp.resp(RespResult.fail("保存对象不能为空"));
        }
        obj = service.save(obj);
        return Resp.resp(RespResult.success("保存成功", obj));
    }

    /**
     * 通用删除接口
     */
    @ResponseBody
    @PostMapping("/delete")
    public String delete(Integer id) {
        if (Assert.isEmpty(id)) {
            return Resp.resp(RespResult.fail("删除ID不能为空"));
        }
        if (service.delete(id) == 0) {
            T t = service.get(id);
            if (Assert.isEmpty(t)) {
                return Resp.resp(RespResult.notFound("数据不存在"));
            }
            return Resp.resp(RespResult.fail("删除失败"));
        }
        return Resp.resp(RespResult.success("删除成功"));
    }

    /**
     * 在每个子类方法调用之前先调用
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
    }
}
