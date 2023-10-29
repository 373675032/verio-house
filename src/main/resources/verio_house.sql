/*
 Navicat Premium Data Transfer

 Source Server         : 本地-127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : verio_house

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 14/04/2022 12:18:38

   <!--
    ==========================================================================
    郑重说明：本项目免费开源！原创作者为：薛伟同学，严禁私自出售。
    ==========================================================================
    B站账号：薛伟同学
    微信公众号：薛伟同学
    作者博客：http://xuewei.world
    ==========================================================================
    陆陆续续总会收到粉丝的提醒，总会有些人为了赚取利益倒卖我的开源项目。
    不乏有粉丝朋友出现钱付过去，那边只把代码发给他就跑路的，最后还是根据线索找到我。。
    希望各位朋友擦亮慧眼，谨防上当受骗！
    ==========================================================================
  -->

*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `reply_id`    int(11) DEFAULT NULL COMMENT '回复评论ID',
    `room_id`     int(11) DEFAULT NULL COMMENT '房间ID',
    `user_id`     int(11) DEFAULT NULL COMMENT '用户ID',
    `content`     text COMMENT '内容',
    `rate_count`  int(2) DEFAULT NULL COMMENT '评价星数',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment`
VALUES (3, NULL, 33, 2, '住房体验非常不错，环境也很好！', 5, '2022-04-13 18:16:23', '2022-04-14 10:16:28');
INSERT INTO `comment`
VALUES (4, NULL, 31, 2, '环境很不错，设施也很齐全，房东人很好！', 4, '2022-04-14 10:23:21', '2022-04-14 10:23:21');
INSERT INTO `comment`
VALUES (5, NULL, 33, 2, '很不错！', 4, '2022-04-14 10:24:45', '2022-04-14 10:24:45');
COMMIT;

-- ----------------------------
-- Table structure for favor
-- ----------------------------
DROP TABLE IF EXISTS `favor`;
CREATE TABLE `favor`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     int(11) DEFAULT NULL COMMENT '用户ID',
    `room_id`     int(11) DEFAULT NULL COMMENT '房子ID',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of favor
-- ----------------------------
BEGIN;
INSERT INTO `favor`
VALUES (38, 1, 32, '2022-04-14 10:19:04', '2022-04-14 10:19:04');
INSERT INTO `favor`
VALUES (39, 1, 31, '2022-04-14 10:19:05', '2022-04-14 10:19:05');
INSERT INTO `favor`
VALUES (40, 1, 29, '2022-04-14 10:19:07', '2022-04-14 10:19:07');
INSERT INTO `favor`
VALUES (41, 1, 30, '2022-04-14 10:19:08', '2022-04-14 10:19:08');
INSERT INTO `favor`
VALUES (42, 1, 27, '2022-04-14 10:19:11', '2022-04-14 10:19:11');
INSERT INTO `favor`
VALUES (43, 2, 32, '2022-04-14 10:20:41', '2022-04-14 10:20:41');
INSERT INTO `favor`
VALUES (44, 2, 31, '2022-04-14 10:20:42', '2022-04-14 10:20:42');
INSERT INTO `favor`
VALUES (45, 2, 30, '2022-04-14 10:20:43', '2022-04-14 10:20:43');
INSERT INTO `favor`
VALUES (46, 2, 29, '2022-04-14 10:20:45', '2022-04-14 10:20:45');
INSERT INTO `favor`
VALUES (47, 2, 26, '2022-04-14 10:20:47', '2022-04-14 10:20:47');
COMMIT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `room_id`     int(11) DEFAULT NULL COMMENT '房子ID',
    `order_id`    int(11) DEFAULT NULL,
    `send_id`     int(11) DEFAULT NULL COMMENT '发送人ID',
    `accept_id`   int(11) DEFAULT NULL COMMENT '接受人ID',
    `title`       varchar(50)  DEFAULT NULL COMMENT '标题',
    `content`     text COMMENT '内容',
    `status`      varchar(10)  DEFAULT NULL COMMENT '状态',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remark`      varchar(255) DEFAULT NULL COMMENT '处理意见',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of message
-- ----------------------------
BEGIN;
INSERT INTO `message`
VALUES (3, 31, 5, 2, 1, '主卧室的灯坏了', '主卧室的灯坏了，开关损坏。', '已接收', '2022-04-14 10:23:56',
        '2022-04-14 10:27:08', '好的，周末安排师傅处理！');
INSERT INTO `message`
VALUES (4, 31, 5, 2, 1, '冰箱问题', '冰箱冷冻层失效', '未处理', '2022-04-14 10:24:21', '2022-04-14 10:24:21', NULL);
INSERT INTO `message`
VALUES (5, 33, 4, 2, 1, '门锁坏了', '房东，可以讲门锁改成密码锁吗？', '未处理', '2022-04-14 10:25:28',
        '2022-04-14 10:25:28', NULL);
COMMIT;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `owner_id`    int(11) DEFAULT NULL COMMENT '房东ID',
    `title`       varchar(50) DEFAULT NULL COMMENT '标题',
    `sub_title`   varchar(50) DEFAULT NULL COMMENT '副标题',
    `month_price` double      DEFAULT NULL COMMENT '月租价格',
    `location`    varchar(50) DEFAULT NULL COMMENT '所在地',
    `info`        text COMMENT '描述',
    `content`     text COMMENT '内容',
    `img`         text COMMENT '封面',
    `images`      text COMMENT '图片',
    `create_time` datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `look_count`  int(8) DEFAULT NULL COMMENT '浏览量',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of room
-- ----------------------------
BEGIN;
INSERT INTO `room`
VALUES (25, 1, '民航广场公寓楼 1室1厅1卫', '1室1厅1卫  42.84 平  精装修 南  高层 / 23层', 2100, '福建-福州',
        '地处五一路市中心，大利嘉城对面！周边有永辉、沃尔玛超市，离地铁、公交站近，周边生活配套完善.面积45平，高层视野开阔！有外阳台可晒衣服！屋内刚重新装修一年，租金2100元！一年起租！压二付一！随时看房随时可入住！',
        '邻地铁 配套齐全 精装修 南北通透 出租要求不养宠物 一年起租 租户稳定 作息正常',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/9b85b53a7abe4033b6b6c5add7ee6c48.jpeg',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/76184b1558cf41508b2fb7c7b1046d9e.jpeg,https://su-share.oss-cn-beijing.aliyuncs.com/1/445a77fdc0844d21bbab636e3771c796.jpeg,https://su-share.oss-cn-beijing.aliyuncs.com/1/6de1eedf97504d09bc54a27baf293376.jpeg,',
        '2022-04-13 17:02:38', '2022-04-13 17:02:38', 3);
INSERT INTO `room`
VALUES (26, 1, '银湖花园 3室1厅1卫', '3室1厅1卫  12 平  简单装修 南  高层 / 8层', 990, '福建-福州',
        '出租单间。厨卫全新装修，全屋刷新，正面朝南三房，现出租其中一间，男女皆可。光线充足好心情，顶楼还可以种花种菜，家电家具齐全拎包可住，周边配套设施齐全，下楼三个车站还有地铁，交通极其便利，超市学校钟，很成熟的舒适小区。适合学生、上班族和家庭居住。我是房东。',
        '已传房本 独卫 邻地铁 南北通透 租户稳定 作息正常',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/3cdba693d3684023b5b8d0966dc8171c.jpeg',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/237d11abcead4b099b08fc304409dcdb.jpeg,https://su-share.oss-cn-beijing.aliyuncs.com/1/3da4e1af96834cc7ba55618b36d32dbc.jpeg,https://su-share.oss-cn-beijing.aliyuncs.com/1/f6c2cfeedadc47558e63d3d21e066eac.jpeg,',
        '2022-04-13 17:07:20', '2022-04-13 17:07:20', 0);
INSERT INTO `room`
VALUES (27, 1, '正荣财富中心(闽侯) 4室1厅2卫', '4室1厅2卫  110 平  豪华装修 东  中层 / 32层', 3700, '福建-福州',
        '个人房源，非中介，非中介，非中介！自住房！房子方正，东端头，三面采光通透，无遮挡，可看江，夏天通风空调都可以省点电，另外还有一个电梯口大车位，可以一起租，电车可申请充电桩。家电配置齐全，净水器都安装了，中央空调，大彩电，空气能热水器，200兆宽带等全部都有。看房方便，随时入住。房租可以押二付一。',
        '-', 'https://su-share.oss-cn-beijing.aliyuncs.com/1/9b5d9212bf574ff58d11bb2e658c7b45.jpeg',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/661b4becd2064e229dbcdcc6d01e2323.jpeg,https://su-share.oss-cn-beijing.aliyuncs.com/1/0be0273ac9554662aba65a41ce0e7565.jpeg,',
        '2022-04-13 17:10:47', '2022-04-13 17:10:47', 1);
INSERT INTO `room`
VALUES (29, 1, '碧桂园十里江湾 3室2厅1卫', '3室2厅1卫  80 平  精装修 南  高层 / 33层', 2500, '福建-福州',
        '自住精装修，拎包入住，看江高层，南北通透，温馨舒适，看房随时联系', '已传房本 配套齐全 精装修 南北通透',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/67e0a28707d94634a3eafcafbaeab7c5.jpeg',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/7fe664a7562847e88a34a71ff8949f17.jpeg,', '2022-04-13 17:41:14',
        '2022-04-13 17:41:14', 2);
INSERT INTO `room`
VALUES (30, 1, '中海寰宇天下 1室1厅1卫', '1室1厅1卫  44 平  精装修  北  中层 / 24层', 1800, '福建-福州', '-', '-',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/57401f6c6cc74afc94c0626fed29704c.jpeg',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/c15f86d1fc3a429a86a2bb993abe7e19.jpeg,', '2022-04-13 17:49:04',
        '2022-04-13 17:49:04', 1);
INSERT INTO `room`
VALUES (31, 1, '万科又一城 2室1厅1卫', '2室1厅1卫  76 平  精装修 东南  高层 / 31层', 2500, '福建-福州',
        '房东自住装修，家具齐全，拎包入住。出门就是高新区、西客站，地铁2号线师大站，交通方便。高新区创新园几百米。高新区上班人士方便，上班走路即可。',
        '-', 'https://su-share.oss-cn-beijing.aliyuncs.com/1/4206a032c3e64df1a7a73e983fe83c32.jpeg',
        'https://su-share.oss-cn-beijing.aliyuncs.com/1/ff500e50d269444c9857d8ddc8a8a1a1.jpeg,https://su-share.oss-cn-beijing.aliyuncs.com/1/2ad2c938f70c4e4a828575e6c9c69c86.jpeg,',
        '2022-04-13 17:52:05', '2022-04-13 17:52:05', 7);
INSERT INTO `room`
VALUES (32, 1, '世茂禹洲璀璨江山 1室1厅1卫', '1室1厅1卫  40 平  豪华装修 南  中层 / 17层', 2300, '福建-福州',
        '近地铁交通方便，人才聚集地，豪华装修让居住成为一种享受', '-',
        'https://su-share.oss-cn-beijing.aliyuncs.com/5/aadf9cb71fed42b59aebcd8fe34d2d06.jpeg',
        'https://su-share.oss-cn-beijing.aliyuncs.com/5/4fffa1f71acb4699ae0f29456cbfe154.jpeg,', '2022-04-13 18:08:23',
        '2022-04-14 10:15:35', 15);
INSERT INTO `room`
VALUES (33, 1, '左海二环边 软件园 武警医院 省直屏西 拎包即住', '1室1厅1卫  45.00 平  精装修 二医院 南北  中层 / 30层',
        1200, '福建-福州',
        '休闲购物配套：万嘉超市、苏宁小店、生鲜超市、电影院、咖啡馆、酒吧、KTV、6大商业综合体：永嘉天地综合体、博仕后购物广场、正荣商业综合体、群升商业综合体、新华都购物广场及金城湾综合体等。万福，创业大厦，永福电力，博思软件，市勘测院，中青大厦，久策大厦，华建大厦，山亚国际，邦邦财富，网讯智慧大厦等等就在对面。',
        '房子装修挺不错的，符合您的需求，采光不错，三面采光，南北通透，户型很好，楼层，价格合理。社区环境很好，小区高，小区人文素质高，停车方便。吃饭方便，有N个吃饭的地方、生活购物都方便。有N多路公交车、上班方便，生活一条龙服务。',
        'https://su-share.oss-cn-beijing.aliyuncs.com/5/58d289b3923549048df68edc522a24d1.jpeg',
        'https://su-share.oss-cn-beijing.aliyuncs.com/5/fc2cda90a2144f568e517f1a4eae36c4.jpeg,https://su-share.oss-cn-beijing.aliyuncs.com/5/80bcf25062d547479a2bf0fafeb7a715.jpeg,https://su-share.oss-cn-beijing.aliyuncs.com/5/d04e76aec52d406f86e0fd2ce38c887f.jpeg,',
        '2022-04-13 18:14:12', '2022-04-14 10:15:37', 4);
COMMIT;

-- ----------------------------
-- Table structure for room_detail
-- ----------------------------
DROP TABLE IF EXISTS `room_detail`;
CREATE TABLE `room_detail`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `room_id`        int(11) DEFAULT NULL COMMENT '房间ID',
    `bedroom_count`  int(10) DEFAULT NULL COMMENT '房间数量',
    `parlour_count`  int(10) DEFAULT NULL COMMENT '客厅数量',
    `restroom_count` int(10) DEFAULT NULL COMMENT '卫生间数量',
    `bathroom_count` int(10) DEFAULT NULL COMMENT '浴室数量',
    `capacity`       varchar(50)  DEFAULT NULL COMMENT '大小',
    `garage`         int(10) DEFAULT NULL COMMENT '车库数量',
    `area`           varchar(50)  DEFAULT NULL COMMENT '小区',
    `address`        varchar(255) DEFAULT NULL COMMENT '详细地址',
    `type`           varchar(50)  DEFAULT NULL COMMENT '类型',
    `build_year`     varchar(50)  DEFAULT NULL COMMENT '建造年限',
    `status`         varchar(50)  DEFAULT NULL COMMENT '状态',
    `elevator`       varchar(50)  DEFAULT NULL COMMENT '是否有电梯',
    `kitchen`        varchar(50)  DEFAULT NULL COMMENT '是否有厨房',
    `free_wifi`      varchar(50)  DEFAULT NULL COMMENT '免费Wi-Fi',
    `window`         varchar(50)  DEFAULT NULL COMMENT '窗户',
    `metro`          varchar(50)  DEFAULT NULL COMMENT '是否有地铁',
    `rent_type`      varchar(50)  DEFAULT NULL COMMENT '租赁方式',
    `create_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of room_detail
-- ----------------------------
BEGIN;
INSERT INTO `room_detail`
VALUES (25, 25, 1, 1, 1, 1, '42.84 平', 0, '民航广场公寓楼', '五一中路132号', '公寓', '1999', '空闲', '有', '有', '有',
        '有', '距离地铁1号线茶亭站1008米', '整租', '2022-04-13 17:02:38', '2022-04-13 17:02:47');
INSERT INTO `room_detail`
VALUES (26, 26, 3, 1, 1, 1, '12 平', 0, '银湖花园', '西洪路101号', '住宅', '2000', '空闲', '有', '有', '有', '有',
        '距离地铁1号线东街口站1293米', '单间', '2022-04-13 17:07:20', '2022-04-13 17:10:54');
INSERT INTO `room_detail`
VALUES (27, 27, 4, 1, 2, 2, '110 平', 0, '正荣财富中心(闽侯)', '新保路18号', '住宅', '2000', '空闲', '有', '有', '有',
        '有', '无', '整租', '2022-04-13 17:10:47', '2022-04-13 17:10:55');
INSERT INTO `room_detail`
VALUES (28, 29, 3, 2, 1, 1, '80平', 0, '碧桂园十里江湾', '高新大道65号', '住宅', '2000', '空闲', '有', '有', '有', '有',
        '有', '整租', '2022-04-13 17:41:14', '2022-04-13 17:49:13');
INSERT INTO `room_detail`
VALUES (29, 30, 1, 1, 1, 1, '44平', 0, '中海寰宇天下', '高新大道1-1号', '公寓', '1990', '空闲', '有', '有', '有', '有',
        '距离地铁2号线厚庭站1246米', '整租', '2022-04-13 17:49:04', '2022-04-13 17:49:14');
INSERT INTO `room_detail`
VALUES (30, 31, 2, 1, 1, 1, '76', 0, '万科又一城', '明德路6号', '住宅', '1998', '已租', '有', '有', '有', '有',
        '距离地铁2号线董屿·福建师大站1365米', '整租', '2022-04-13 17:52:05', '2022-04-13 17:52:14');
INSERT INTO `room_detail`
VALUES (31, 32, 1, 1, 1, 1, '50平', 1, '世茂禹洲璀璨江山', '乌龙江大道', '公寓', '1999', '空闲', '有', '有', '有', '有',
        '距离地铁2号线厚庭站1175米', '整租', '2022-04-13 18:08:23', '2022-04-13 18:08:28');
INSERT INTO `room_detail`
VALUES (32, 33, 1, 1, 1, 1, '45平', 0, '中海寰宇天下', '高新大道1-1号', '公寓', '1980', '已租', '有', '有', '有', '有',
        '距离地铁2号线厚庭站1246米', '整租', '2022-04-13 18:14:12', '2022-04-13 18:14:19');
COMMIT;

-- ----------------------------
-- Table structure for room_order
-- ----------------------------
DROP TABLE IF EXISTS `room_order`;
CREATE TABLE `room_order`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `room_id`     int(11) DEFAULT NULL COMMENT '房子ID',
    `user_id`     int(11) DEFAULT NULL COMMENT '用户ID',
    `owner_id`    int(11) DEFAULT NULL COMMENT '房东ID',
    `order_num`   varchar(200) DEFAULT NULL COMMENT '订单编号',
    `pay_order`   varchar(200) DEFAULT NULL COMMENT '订单流水号',
    `title`       varchar(50)  DEFAULT NULL COMMENT '标题',
    `sub_title`   varchar(50)  DEFAULT NULL COMMENT '副标题',
    `pay_money`   double       DEFAULT NULL COMMENT '价格',
    `content`     text COMMENT '内容',
    `attachment`  varchar(500) DEFAULT NULL COMMENT '附件文件',
    `status`      varchar(10)  DEFAULT NULL COMMENT '状态',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `pay_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `from_time`   datetime     DEFAULT NULL,
    `to_time`     datetime     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of room_order
-- ----------------------------
BEGIN;
INSERT INTO `room_order`
VALUES (4, 33, 2, 1, 'ORDER1649844945024NUM', '1649844964567PAYED', '左海二环边 软件园 武警医院 省直屏西 拎包即住 x 6',
        '1室1厅1卫  45.00 平  精装修 二医院 南北  中层 / 30层', 7200, '租期为6个月，共1入住。', NULL, '已支付',
        '2022-04-13 18:15:45', '2022-04-13 18:15:45', '2022-04-14 10:16:59', '2022-04-13 18:15:45',
        '2022-10-13 18:15:45');
INSERT INTO `room_order`
VALUES (5, 31, 2, 1, 'ORDER1649902879822NUM', '1649902946203PAYED', '万科又一城 2室1厅1卫 x 12',
        '2室1厅1卫  76 平  精装修 东南  高层 / 31层', 30000, '租期为12个月，共2入住。', NULL, '已支付',
        '2022-04-14 10:21:19', '2022-04-14 10:21:19', '2022-04-14 10:21:19', '2022-04-14 10:21:20',
        '2023-04-14 10:21:20');
INSERT INTO `room_order`
VALUES (6, 29, 2, 1, 'ORDER1649902920590NUM', NULL, '碧桂园十里江湾 3室2厅1卫 x 12',
        '3室2厅1卫  80 平  精装修 南  高层 / 33层', 30000, '租期为12个月，共2入住。', NULL, '未支付',
        '2022-04-14 10:22:00', '2022-04-14 10:22:00', '2022-04-14 10:22:00', '2022-04-14 10:22:01',
        '2023-04-14 10:22:01');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(50)  DEFAULT NULL COMMENT '姓名',
    `password`    varchar(50)  DEFAULT NULL COMMENT '登陆密码',
    `sex`         varchar(2)   DEFAULT NULL COMMENT '性别',
    `birth`       varchar(10)  DEFAULT NULL COMMENT '出生年月',
    `idcard_num`  varchar(30)  DEFAULT NULL COMMENT '身份证号码',
    `phone`       varchar(20)  DEFAULT NULL COMMENT '手机号',
    `email`       varchar(50)  DEFAULT NULL COMMENT '邮箱',
    `location`    varchar(50)  DEFAULT NULL COMMENT '所在地',
    `about`       varchar(500) DEFAULT NULL COMMENT '关于',
    `role`        varchar(50)  DEFAULT NULL COMMENT '角色',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user`
VALUES (1, '张三', '12345678', '男', '2000-07-29', '674899864923765938', '17879990909', 'zs@qq.com', '福建-福州',
        '我是一个普通人！', '房东', '2022-04-06 13:42:47', '2022-04-14 10:17:27');
INSERT INTO `user`
VALUES (2, '李四', '12345678', '男', '2001-05-19', '674899864923765938', '17879540090', 'ls@qq.com', '福建-福州', NULL,
        '租客', '2022-04-11 19:18:12', '2022-04-14 10:17:31');
COMMIT;

SET
FOREIGN_KEY_CHECKS = 1;
