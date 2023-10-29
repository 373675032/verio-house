package com.verio.controller;

import com.verio.entity.*;
import com.verio.utils.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统跳转控制器
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
@Controller
public class SystemController extends BaseController<User> {

    /**
     * 登录后门
     */
    @RequestMapping("/admin")
    public String admin() {
        User user = userService.get(1);
        session.setAttribute("loginUser", user);
        return "redirect:/my-profile";
    }

    /**
     * 跳转首页
     */
    @RequestMapping("/")
    public String toIndex() {
        return "redirect:/index";
    }

    /**
     * 跳转首页
     */
    @RequestMapping("/index")
    public String index(Map<String, Object> map) {
        List<Favor> favors = new ArrayList<>();
        if (Assert.notEmpty(loginUser)) {
            favors = favorService.query(Favor.builder().userId(loginUser.getId()).build());
        }
        Map<Integer, Favor> favorMap = favors.stream().collect(Collectors.toMap(Favor::getRoomId, favor -> favor));
        List<Room> rooms = roomService.all();
        for (Room room : rooms) {
            List<RoomDetail> details = roomDetailService.query(RoomDetail.builder().roomId(room.getId()).build());
            room.setDetail(details.get(0));
            if (favorMap.keySet().contains(room.getId())) {
                room.setFavorId(favorMap.get(room.getId()).getId());
            }
        }
        rooms = rooms.stream()
                .filter(r -> "空闲".equals(r.getDetail().getStatus()))
                .sorted((r1, r2) -> (int) (r2.getUpdateTime().getTime() - r1.getUpdateTime().getTime()))
                .limit(6)
                .collect(Collectors.toList());
        map.put("rooms", rooms);
        return "index";
    }

    /**
     * 跳转搜索页面
     */
    @RequestMapping("/search")
    public String search(String key, String metro, String type, String rentType, Integer bedroomCount, String monthPriceRange, String order, Map<String, Object> map) {
        List<Favor> favors = new ArrayList<>();
        if (Assert.notEmpty(loginUser)) {
            favors = favorService.query(Favor.builder().userId(loginUser.getId()).build());
        }
        Map<Integer, Favor> favorMap = favors.stream().collect(Collectors.toMap(Favor::getRoomId, favor -> favor));
        List<Room> rooms = roomService.all();
        for (Room room : rooms) {
            List<RoomDetail> details = roomDetailService.query(RoomDetail.builder().roomId(room.getId()).build());
            room.setDetail(details.get(0));
            if (favorMap.keySet().contains(room.getId())) {
                room.setFavorId(favorMap.get(room.getId()).getId());
            }
        }
        rooms = rooms.stream()
                .filter(r -> "空闲".equals(r.getDetail().getStatus()))
                .sorted((r1, r2) -> (int) (r2.getUpdateTime().getTime() - r1.getUpdateTime().getTime()))
                .collect(Collectors.toList());
        if (Assert.notEmpty(key)) {
            rooms = rooms.stream().filter(r ->
                    r.getTitle().contains(key) || r.getSubTitle().contains(key) || r.getInfo().contains(key) || r.getContent().contains(key)
            ).collect(Collectors.toList());
        }
        if (Assert.notEmpty(metro)) {
            rooms = rooms.stream().filter(r ->
                    r.getDetail().getMetro().contains(metro)
            ).collect(Collectors.toList());
        }
        if (Assert.notEmpty(type)) {
            rooms = rooms.stream().filter(r ->
                    r.getDetail().getType().contains(type)
            ).collect(Collectors.toList());
        }
        if (Assert.notEmpty(rentType)) {
            rooms = rooms.stream().filter(r ->
                    r.getDetail().getRentType().contains(rentType)
            ).collect(Collectors.toList());
        }
        if (Assert.notEmpty(bedroomCount)) {
            rooms = rooms.stream().filter(r ->
                    r.getDetail().getBedroomCount() == bedroomCount.intValue()
            ).collect(Collectors.toList());
        }
        if (Assert.notEmpty(monthPriceRange)) {
            String[] split = monthPriceRange.split(";");
            rooms = rooms.stream().filter(r ->
                    r.getMonthPrice() >= new Double(split[0]) && r.getMonthPrice() <= new Double(split[1])
            ).collect(Collectors.toList());
        }
        if (Assert.notEmpty(order)) {
            switch (order) {
                case "价格升序":
                    rooms = rooms.stream().sorted(Comparator.comparing(Room::getMonthPrice)).collect(Collectors.toList());
                    break;
                case "价格降序":
                    rooms = rooms.stream().sorted(Comparator.comparing(Room::getMonthPrice).reversed()).collect(Collectors.toList());
                    break;
                case "浏览多":
                    rooms = rooms.stream().sorted(Comparator.comparing(Room::getLookCount).reversed()).collect(Collectors.toList());
                default:
                    break;
            }
        }

        map.put("key", key);
        map.put("metro", metro);
        map.put("type", type);
        map.put("rentType", rentType);
        map.put("bedroomCount", bedroomCount);
        map.put("monthPriceRange", monthPriceRange);

        map.put("rooms", rooms);
        map.put("order", order);
        return "search";
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/index";
    }

    /**
     * 个人资料
     */
    @GetMapping("/my-profile")
    public String myProfile(Map<String, Object> map) {
        map.put("user", loginUser);
        return "my-profile";
    }

    /**
     * 我的收藏
     */
    @GetMapping("/my-favor")
    public String myFavor(Map<String, Object> map) {
        List<Favor> favors = favorService.query(Favor.builder().userId(loginUser.getId()).build());
        Map<Integer, Favor> favorMap = favors.stream().collect(Collectors.toMap(Favor::getRoomId, favor -> favor));

        List<Integer> roomIds = favors.stream().map(Favor::getRoomId).collect(Collectors.toList());
        List<Room> rooms = roomService.all();

        rooms = rooms.stream().filter(r -> roomIds.contains(r.getId())).collect(Collectors.toList());
        for (Room room : rooms) {
            room.setFavorId(favorMap.get(room.getId()).getId());
            List<RoomDetail> details = roomDetailService.query(RoomDetail.builder().roomId(room.getId()).build());
            room.setDetail(details.get(0));
            room.setUser(userService.get(room.getOwnerId()));
        }

        map.put("rooms", rooms);
        return "my-favor";
    }

    /**
     * 我的反馈
     */
    @GetMapping("/my-back")
    public String myBack(Map<String, Object> map) {
        List<Message> messages = messageService.query(Message.builder().sendId(loginUser.getId()).build());
        messages.forEach(m -> {
            m.setSender(userService.get(m.getSendId()));
            m.setAccepter(userService.get(m.getAcceptId()));
            m.setRoomOrder(roomOrderService.get(m.getOrderId()));
            m.setRoom(roomService.get(m.getRoomId()));
        });

        map.put("backs", messages);
        return "my-back";
    }


    /**
     * 反馈中心
     */
    @GetMapping("/backs")
    public String backs(Map<String, Object> map) {
        List<Message> messages = messageService.query(Message.builder().acceptId(loginUser.getId()).build());
        messages.forEach(m -> {
            m.setSender(userService.get(m.getSendId()));
            m.setAccepter(userService.get(m.getAcceptId()));
            m.setRoomOrder(roomOrderService.get(m.getOrderId()));
            m.setRoom(roomService.get(m.getRoomId()));
        });

        map.put("backs", messages);
        return "my-back";
    }

    /**
     * 房间详情
     */
    @GetMapping("/room")
    public String room(Integer id, Map<String, Object> map) {
        if (Assert.isEmpty(id)) {
            return "redirect:/index";
        }
        Room room = roomService.get(id);
        room.setLookCount(room.getLookCount() + 1);
        roomService.save(room);

        List<RoomDetail> details = roomDetailService.query(RoomDetail.builder().roomId(room.getId()).build());
        room.setDetail(details.get(0));

        List<String> images = new ArrayList();
        if (Assert.notEmpty(room.getImages())) {
            images = Arrays.asList(room.getImages().split(","));
        }

        room.setUser(userService.get(room.getOwnerId()));

        // 是否收藏
        if (Assert.notEmpty(loginUser)) {
            List<Favor> favors = favorService.query(Favor.builder().userId(loginUser.getId()).roomId(id).build());
            if (Assert.notEmpty(favors)) {
                room.setFavorId(favors.get(0).getId());
            }
        }

        // 评论
        List<Comment> comments = commentService.query(Comment.builder().roomId(id).build());
        comments.forEach(c -> c.setUser(userService.get(c.getUserId())));

        // 每日推荐
        List<Room> rooms = roomService.all();
        for (Room r : rooms) {
            r.setDetail(roomDetailService.query(RoomDetail.builder().roomId(r.getId()).build()).get(0));
        }
        rooms = rooms.stream()
                .filter(r -> "空闲".equals(r.getDetail().getStatus()))
                .sorted((r1, r2) -> (int) (r2.getUpdateTime().getTime() - r1.getUpdateTime().getTime()))
                .limit(6)
                .collect(Collectors.toList());

        map.put("rooms", rooms);
        map.put("room", room);
        map.put("comments", comments);
        map.put("images", images);
        return "room";
    }

    /**
     * 发布房源
     */
    @GetMapping("/new-room")
    public String newRoom(Map<String, Object> map) {
        return "new-room";
    }

    /**
     * 发布房源
     */
    @GetMapping("/edit-room")
    public String editRoom(Integer id, Map<String, Object> map) {
        if (Assert.isEmpty(id)) {
            return "new-room";
        }
        Room room = roomService.get(id);
        List<RoomDetail> details = roomDetailService.query(RoomDetail.builder().roomId(id).build());
        List<String> images = new ArrayList();
        if (Assert.notEmpty(room.getImages())) {
            images = Arrays.asList(room.getImages().split(","));
        }

        map.put("room", room);
        map.put("detail", details.get(0));
        map.put("images", images);

        return "edit-room";
    }

    /**
     * 我的房源
     */
    @GetMapping("/my-rooms")
    public String myRooms(Map<String, Object> map) {
        List<Room> rooms = roomService.query(Room.builder().ownerId(loginUser.getId()).build());
        for (Room room : rooms) {
            List<RoomDetail> details = roomDetailService.query(RoomDetail.builder().roomId(room.getId()).build());
            room.setDetail(details.get(0));
        }
        map.put("rooms", rooms);
        return "my-rooms";
    }

    /**
     * 修改密码
     */
    @GetMapping("/change-password")
    public String changePassword(Map<String, Object> map) {
        return "change-password";
    }

    /**
     * 我的订单
     */
    @GetMapping("/my-order")
    public String myOrder(Map<String, Object> map) {
        List<RoomOrder> roomOrders = roomOrderService.query(RoomOrder.builder().userId(loginUser.getId()).build());
        roomOrders.forEach(o -> {
            o.setOwner(userService.get(o.getOwnerId()));
            o.setRoom(roomService.get(o.getRoomId()));
            o.setUser(loginUser);
        });

        map.put("orders", roomOrders);
        return "my-order";
    }

    /**
     * 订单管理
     */
    @GetMapping("/orders")
    public String orders(Map<String, Object> map) {
        List<RoomOrder> roomOrders = roomOrderService.query(RoomOrder.builder().ownerId(loginUser.getId()).build());
        roomOrders.forEach(o -> {
            o.setOwner(loginUser);
            o.setUser(userService.get(o.getUserId()));
            o.setRoom(roomService.get(o.getRoomId()));
        });

        map.put("orders", roomOrders);
        return "orders";
    }

    /**
     * 我的订单
     */
    @GetMapping("/pay")
    public String pay(Integer id, Map<String, Object> map) {
        RoomOrder order = roomOrderService.get(id);
        Room room = roomService.get(order.getRoomId());
        List<RoomDetail> details = roomDetailService.query(RoomDetail.builder().roomId(room.getId()).build());
        room.setDetail(details.get(0));

        List<String> images = new ArrayList();
        if (Assert.notEmpty(room.getImages())) {
            images = Arrays.asList(room.getImages().split(","));
        }

        User user = userService.get(order.getOwnerId());

        room.setUser(user);

        order.setOwner(user);
        order.setRoom(room);
        order.setUser(userService.get(order.getUserId()));

        map.put("room", room);
        map.put("user", user);
        map.put("order", order);
        map.put("images", images);
        return "pay";
    }

}