/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : moviedb

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-06-09 23:48:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_activity`
-- ----------------------------
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` text NOT NULL COMMENT '内容',
  `number` int(11) DEFAULT NULL COMMENT '参与人数',
  `start_time` datetime NOT NULL COMMENT '开始时间（格式2022-01-02）',
  `end_time` datetime NOT NULL COMMENT '结束时间（格式2022-01-02）',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间（格式2022-01-14\\n11:37:03）',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间 (格式2022-01)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='4.2.8 活动表（t_activity）';

-- ----------------------------
-- Records of t_activity
-- ----------------------------
INSERT INTO `t_activity` VALUES ('1', '活动简述:为了满足广大儿童与家长的娱乐、交流、学习需求,让我们的孩子们有-块 专属于自己的屏幕与天空，晚报拟将晚报内部电影厅打造成专属的儿童亲子影院，定期播放专属儿童的各类电 影与演出活动,如英语专场、爸爸去看电影专场、假期电影周、动漫专场等等,让亲子家庭在娱乐中提升、在快 乐中沉思。同时影院将并与商业机构合作,开展各类知识讲座与交流,如儿童保险、家庭理财、儿童教育、母婴 用品交流等等，在丰富儿童的业余生活，服务亲子家庭的基本需求的同时，促进家庭和谐，让父母与子女相互 尊重、共同教育、-起成长。', '1', '2021-04-01 00:00:00', '2021-04-03 00:00:00', '2022-05-17 15:52:06', '2022-05-17 15:52:06');
INSERT INTO `t_activity` VALUES ('2', '活动简述:为了满足广大儿童与家长的娱乐、交流、学习需求,让我们的孩子们有-块 专属于自己的屏幕与天空，晚报拟将晚报内部电影厅打造成专属的儿童亲子影院，定期播放专属儿童的各类电 影与演出活动,如英语专场、爸爸去看电影专场、假期电影周、动漫专场等等,让亲子家庭在娱乐中提升、在快 乐中沉思。同时影院将并与商业机构合作,开展各类知识讲座与交流,如儿童保险、家庭理财、儿童教育、母婴 用品交流等等，在丰富儿童的业余生活，服务亲子家庭的基本需求的同时，促进家庭和谐，让父母与子女相互 尊重、共同教育、-起成长。', '1', '2021-04-01 00:00:00', '2021-04-03 00:00:00', '2022-05-17 15:52:06', '2022-05-17 15:52:06');
INSERT INTO `t_activity` VALUES ('3', '活动简述:为了满足广大儿童与家长的娱乐、交流、学习需求,让我们的孩子们有-块 专属于自己的屏幕与天空，晚报拟将晚报内部电影厅打造成专属的儿童亲子影院，定期播放专属儿童的各类电 影与演出活动,如英语专场、爸爸去看电影专场、假期电影周、动漫专场等等,让亲子家庭在娱乐中提升、在快 乐中沉思。同时影院将并与商业机构合作,开展各类知识讲座与交流,如儿童保险、家庭理财、儿童教育、母婴 用品交流等等，在丰富儿童的业余生活，服务亲子家庭的基本需求的同时，促进家庭和谐，让父母与子女相互 尊重、共同教育、-起成长。', '1', '2021-04-01 00:00:00', '2021-04-03 00:00:00', '2022-05-17 15:52:06', '2022-05-17 15:52:06');
INSERT INTO `t_activity` VALUES ('4', '活动简述:为了满足广大儿童与家长的娱乐、交流、学习需求,让我们的孩子们有-块 专属于自己的屏幕与天空，晚报拟将晚报内部电影厅打造成专属的儿童亲子影院，定期播放专属儿童的各类电 影与演出活动,如英语专场、爸爸去看电影专场、假期电影周、动漫专场等等,让亲子家庭在娱乐中提升、在快 乐中沉思。同时影院将并与商业机构合作,开展各类知识讲座与交流,如儿童保险、家庭理财、儿童教育、母婴 用品交流等等，在丰富儿童的业余生活，服务亲子家庭的基本需求的同时，促进家庭和谐，让父母与子女相互 尊重、共同教育、-起成长。', '0', '2021-04-01 00:00:00', '2021-04-03 00:00:00', '2022-05-17 15:52:06', '2022-05-17 15:52:06');
INSERT INTO `t_activity` VALUES ('6', '促销活动', '0', '2022-06-23 16:00:00', '2022-06-29 16:00:00', '2022-06-09 08:14:27', '2022-06-09 08:14:27');

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员信息表';

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '123456', '/images/1.jpg');

-- ----------------------------
-- Table structure for `t_arrangement`
-- ----------------------------
DROP TABLE IF EXISTS `t_arrangement`;
CREATE TABLE `t_arrangement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fid` int(11) NOT NULL COMMENT '电影主键（关联t_film表）',
  `name` varchar(255) NOT NULL COMMENT '电影名',
  `seat_number` int(11) NOT NULL COMMENT '开放多少座位',
  `box_office` int(11) NOT NULL COMMENT '票房统计',
  `price` decimal(10,1) NOT NULL COMMENT '价格',
  `type` varchar(255) NOT NULL COMMENT '放映类型 2D 3D',
  `date` datetime NOT NULL COMMENT '电影放映日期（格式2022-01-\n14）',
  `start_time` datetime NOT NULL COMMENT '电影开始时间（格式11:37:03',
  `end_time` datetime NOT NULL COMMENT '电影结束时间（格式11:37:03）',
  `founder` varchar(255) NOT NULL COMMENT '排片人',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间（格式2022-01-14\\n11:37:03）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='4.2.11 排片表（t_arrangement）';

-- ----------------------------
-- Records of t_arrangement
-- ----------------------------
INSERT INTO `t_arrangement` VALUES ('7', '1', '狮子王', '50', '0', '100.0', '3D', '2022-06-23 16:00:00', '2022-06-24 09:00:00', '2022-06-24 10:03:00', 'admin', '2022-06-09 03:09:16', '2022-06-09 03:09:16');
INSERT INTO `t_arrangement` VALUES ('8', '2', '小小的愿望', '50', '0', '80.0', '2D', '2022-06-24 16:00:00', '2022-06-24 23:02:00', '2022-06-25 00:04:00', 'admin', '2022-06-09 03:11:33', '2022-06-09 03:11:33');
INSERT INTO `t_arrangement` VALUES ('9', '1', '狮子王', '45', '0', '60.0', '2D', '2022-06-26 16:00:00', '2022-06-27 08:00:00', '2022-06-27 10:02:00', 'admin', '2022-06-09 03:12:09', '2022-06-09 03:12:09');
INSERT INTO `t_arrangement` VALUES ('10', '1', '狮子王', '30', '0', '78.0', '2D', '2022-06-26 16:00:00', '2022-06-27 06:00:00', '2022-06-27 08:00:00', 'admin', '2022-06-09 03:14:28', '2022-06-09 03:14:28');
INSERT INTO `t_arrangement` VALUES ('11', '2', '小小的愿望', '40', '0', '55.0', '2D', '2022-06-25 16:00:00', '2022-06-26 12:00:00', '2022-06-26 13:10:00', 'admin', '2022-06-09 03:15:20', '2022-06-09 03:15:20');

-- ----------------------------
-- Table structure for `t_cart`
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '用户主键（关联t_user表）',
  `aid` int(11) NOT NULL COMMENT '排片id（关联\nt_arrangement）',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `phone` varchar(11) NOT NULL COMMENT '处理结果',
  `seats` varchar(255) NOT NULL COMMENT '座位',
  `price` decimal(10,0) NOT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='4.2.15 购物车表（t_cart）';

-- ----------------------------
-- Records of t_cart
-- ----------------------------
INSERT INTO `t_cart` VALUES ('41', '1', '9', '2', '11111111111', '24', '60', '2022-06-09 15:04:46', '2022-06-09 15:04:46');

-- ----------------------------
-- Table structure for `t_film`
-- ----------------------------
DROP TABLE IF EXISTS `t_film`;
CREATE TABLE `t_film` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '电影名',
  `release_time` datetime NOT NULL COMMENT '上映时间（格式2022-01-\n14）',
  `type` varchar(255) NOT NULL COMMENT '类型',
  `status` tinyint(4) NOT NULL COMMENT '状态 1-上架；0-下架',
  `region` varchar(255) NOT NULL COMMENT '地区',
  `hot` int(11) DEFAULT NULL COMMENT '热度',
  `introduction` text NOT NULL COMMENT '介绍',
  `cover` varchar(255) NOT NULL COMMENT '图片id（关联t_film.id）',
  `duration` int(11) NOT NULL COMMENT '电影时长 单位：分钟',
  `is_delete` varchar(2) DEFAULT NULL COMMENT '删除标志 1-已被删除 0-未\n删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`,`region`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='4.2.10 电影表（t_film）';

-- ----------------------------
-- Records of t_film
-- ----------------------------
INSERT INTO `t_film` VALUES ('1', '狮子王', '2022-06-22 16:00:00', '儿童', '1', '美国', '83', '该片讲述了小狮子王辛巴在众多热情的朋友的陪伴下，最终成为草原之王的故事 [1]  。2019年12月17日，《狮子王》入围第92届奥斯卡奖最佳视觉效果奖提名名单', '/images/狮子王_834f8719-8076-4e8d-9eef-d8d9f0f53e81.jpg', '120', '0', '2022-06-09 02:28:09', '2022-06-09 02:28:09');
INSERT INTO `t_film` VALUES ('2', '小小的愿望', '2022-06-24 16:00:00', '喜剧', '1', '内地', '97', '该片改编自韩国电影《伟大的愿望》，讲述了18岁的少年高远因患有绝症即将离世，死党徐浩与张正阳得知此噩耗后，决定帮他圆梦。三兄弟在圆梦过程中，遭遇一系列令人啼笑皆非的经历的故事', '/images/小小愿望_9924e316-1a90-4cc9-a4e6-bcf79865dc49.jpg', '123', '0', '2022-06-09 02:30:23', '2022-06-09 02:30:23');
INSERT INTO `t_film` VALUES ('13', '日不落酒店', '2022-06-22 16:00:00', '喜剧', '1', '内地', '83', '该片讲述了在一家复古英式酒店工作的经理石月意外升职，获得额外假期的他携娇妻橘子出游“观星之旅”，谁料各种乌龙离奇事件就此纷至沓来，严谨无趣的生活彻底被打破重组的故事', '/images/日不落酒店_1936e745-3c95-4ab1-8813-fd93b68da0da.jpg', '125', '0', '2022-06-09 02:16:22', '2022-06-09 02:16:22');
INSERT INTO `t_film` VALUES ('14', '哥斯拉大战金刚', '2022-06-23 16:00:00', '恐怖', '1', '美国', '86', '该片讲述了人类计划将所有巨兽从地球上抹去，而传说中哥斯拉和金刚两个王者被设计进行了对决，最终两大巨兽联手破坏人类计划的故事', '/images/哥斯拉_e8bf918e-472a-4bef-875d-9f344b372829.jpg', '112', '0', '2022-06-09 02:21:52', '2022-06-09 02:21:52');
INSERT INTO `t_film` VALUES ('15', '你好李焕英', '2022-06-24 16:00:00', '家庭', '1', '中国大陆', '98', '该片根据2016年的同名小品及贾玲亲身经历改编，讲述了刚考上大学的女孩贾晓玲经历了一次人生的大起大落后情绪失控，随后意外穿越回到了二十世纪八十年代，与20年前正值青春的母亲李焕英相遇的故事', '/images/lihuanying_c7352a90-ae99-471a-a330-acb32b9ca842.jpg', '112', '0', '2022-06-09 02:23:56', '2022-06-09 02:23:56');
INSERT INTO `t_film` VALUES ('16', '人潮汹涌', '2022-06-24 16:00:00', '犯罪', '1', '中国大陆', '88', '影片根据日本电影《盗钥匙的方法》而改编，讲述了冷血杀手周全和落魄龙套陈小萌，在一次意外中交换了彼此的身份，从而引出一系列阴差阳错的幽默故事', '/images/renchaoxiongyong_02e154aa-fa39-477c-aed4-462e1bfb8642.jpg', '120', '0', '2022-06-09 02:24:40', '2022-06-09 02:24:40');
INSERT INTO `t_film` VALUES ('17', '波斯语课', '2022-06-22 16:00:00', '冒险', '1', '中国大陆', '70', '该片改编自沃夫冈·柯尔海斯受到真实事件启发所著的短篇小说 ，讲述了年轻的比利时犹太人吉尔斯为求活命，假冒波斯人为集中营的军官科赫教授波斯语，二人之间产生不同寻常的友谊的故事', '/images/波斯语课_ee7da75d-d4bd-425c-8440-b107d4d71776.jpg', '120', '0', '2022-06-09 02:25:50', '2022-06-09 02:25:50');
INSERT INTO `t_film` VALUES ('18', '受益人', '2022-06-22 16:00:00', '犯罪', '1', '内地', '75', '该片讲述了吴海为了给罹患哮喘的6岁儿子治病，在好友钟振江的怂恿下，刻意结识了一个与他同样身处边缘和底层的网络女主播淼淼，决心酝酿一场别有用心的婚姻骗局的故事', '/images/shouyiren_93b9fd80-72ea-4520-98a2-ae69ab297e9e.jpg', '125', '0', '2022-06-09 02:26:26', '2022-06-09 02:26:26');
INSERT INTO `t_film` VALUES ('21', '唐人街探案3', '2022-06-22 16:00:00', '喜剧', '1', '内地', '80', '该片讲述了继“曼谷夺金杀人案”“纽约五行连环杀人案”后，唐仁、秦风被野田昊请到东京，调查一桩离奇的谋杀案的故事', '/images/唐人街探案_99fffae9-da1e-40c4-bb55-2b54294b2961.jpg', '118', '0', '2022-06-09 02:29:06', '2022-06-09 02:29:06');
INSERT INTO `t_film` VALUES ('22', '我的姐姐', '2022-06-23 16:00:00', '家庭', '1', '内地', '90', '该片讲述了失去父母的姐姐在面对追求个人独立生活还是抚养弟弟的问题上，发展的一段亲情故事', '/images/我的姐姐_065478bd-343e-448d-b5f7-c6fb8946bee8.jpg', '120', '0', '2022-06-09 02:29:34', '2022-06-09 02:29:34');
INSERT INTO `t_film` VALUES ('24', '刺杀小说家', '2022-06-23 16:00:00', '冒险', '1', '内地', '0', '该片根据双雪涛短篇小说集《飞行家》中的同名短篇小说改编，讲述了一名父亲为找到失踪的女儿，接下刺杀小说家的任务，而小说家笔下的奇幻世界，也正悄悄影响着现实世界中众人的命运的故事', '/images/cishaxiaoshuojia_a8b035db-8f39-4588-8860-c437dc92eb92.jpg', '135', '0', '2022-06-09 03:10:52', '2022-06-09 03:10:52');

-- ----------------------------
-- Table structure for `t_film_evaluate`
-- ----------------------------
DROP TABLE IF EXISTS `t_film_evaluate`;
CREATE TABLE `t_film_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键（主键索引），自增',
  `fid` int(11) DEFAULT NULL COMMENT '电影id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `star` float DEFAULT NULL COMMENT '评分',
  `comment` varchar(2000) DEFAULT NULL COMMENT '内容',
  `create_at` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='电影评论表';

-- ----------------------------
-- Records of t_film_evaluate
-- ----------------------------
INSERT INTO `t_film_evaluate` VALUES ('1', '1', '1', '2', '非常好看', '2022-05-16 15:48:16');
INSERT INTO `t_film_evaluate` VALUES ('2', '1', '1', '4.5', 'nice', '2022-05-16 17:55:54');
INSERT INTO `t_film_evaluate` VALUES ('3', '1', '1', '3.5', '很棒', '2022-05-16 18:18:52');
INSERT INTO `t_film_evaluate` VALUES ('4', '1', '1', '3', '不错', '2022-05-16 18:31:14');
INSERT INTO `t_film_evaluate` VALUES ('5', '1', '1', '1', '不好看', '2022-05-16 18:34:19');
INSERT INTO `t_film_evaluate` VALUES ('6', '2', '1', '5', '好看', '2022-05-16 18:59:49');
INSERT INTO `t_film_evaluate` VALUES ('7', '3', '1', '5', '《你好，李焕英》让人喜欢的地方，在于这是一个反向的追溯的温情过程：女儿贾晓玲回到过去、认识成为晓玲妈妈之前的李焕英，陪李焕英去做属于她那个时代属于她自己的事情。', '2022-05-19 16:04:34');
INSERT INTO `t_film_evaluate` VALUES ('8', '3', '1', '3', '好看', '2022-05-19 16:22:33');
INSERT INTO `t_film_evaluate` VALUES ('9', '3', '1', '3', '推荐一家人去看', '2022-05-19 16:24:01');

-- ----------------------------
-- Table structure for `t_leaving_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_leaving_message`;
CREATE TABLE `t_leaving_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '用户主键（关联t_user表）',
  `content` text NOT NULL COMMENT '留言内容',
  `reply` text COMMENT '回复内容',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间（格式2022-01-14\\n11:37:03）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='4.2.6 影院留言表（t_leaving_message）';

-- ----------------------------
-- Records of t_leaving_message
-- ----------------------------
INSERT INTO `t_leaving_message` VALUES ('2', '2', '留言内容2', 'aa', '2022-05-18 10:50:50', '2022-06-09 08:12:31');
INSERT INTO `t_leaving_message` VALUES ('3', '2', '留言内容1', '22', '2022-05-18 11:19:51', '2022-06-08 16:19:15');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(11) NOT NULL COMMENT '用户主键（关联t_user表）',
  `phone` varchar(11) NOT NULL COMMENT '电话号',
  `aid` int(11) NOT NULL COMMENT '排片id（关联t_arrangement）',
  `seats` varchar(255) NOT NULL COMMENT '座位',
  `price` decimal(10,0) NOT NULL COMMENT '金额',
  `status` int(11) NOT NULL COMMENT '状态 0-等待支付；1-支付失败；2-支付成\n功；3-已被撤销',
  `pay_at` datetime DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间（格式2022-01-14 11:37:03）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='4.2.13 订单表（t_order）';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('36', '1', '11111111111', '9', '24', '60', '3', '2022-06-09 07:11:58', '2022-06-09 15:04:56', '2022-06-09 15:04:56');
INSERT INTO `t_order` VALUES ('37', '1', '11111111111', '9', '24', '60', '2', '2022-06-09 07:12:06', '2022-06-09 15:05:19', '2022-06-09 15:05:19');

-- ----------------------------
-- Table structure for `t_order_exception`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_exception`;
CREATE TABLE `t_order_exception` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `oid` int(255) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `status` int(4) NOT NULL,
  `result` varchar(255) DEFAULT NULL,
  `reviewer` varchar(255) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `end_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_exception
-- ----------------------------
INSERT INTO `t_order_exception` VALUES ('6', '36', '异常', '1', '处理成功', 'admin', '2022-06-09 07:13:00', '2022-06-09 07:13:00');

-- ----------------------------
-- Table structure for `t_poster`
-- ----------------------------
DROP TABLE IF EXISTS `t_poster`;
CREATE TABLE `t_poster` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fid` int(11) NOT NULL COMMENT '电影id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `url` varchar(255) NOT NULL COMMENT '图片id（关联t_film.id）',
  `status` int(4) NOT NULL COMMENT '状态1-上架；0-下架',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间（格式2022-01-14 11:37:03）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='4.2.5 轮播图广告表（t_poster）';

-- ----------------------------
-- Records of t_poster
-- ----------------------------
INSERT INTO `t_poster` VALUES ('1', '1', '狮子王', '/images/shiziwang_b4d1fb2d-78d7-4c0d-9d20-0c34314b6385.jpg', '1', '2022-06-09 14:03:55', '2022-06-09 14:03:55');
INSERT INTO `t_poster` VALUES ('2', '2', '小小的愿望', '/images/xiaoxiaodeyuanwang_b54f3ecc-2050-47ac-99a8-61ad775cbb59.jpg', '1', '2022-06-09 14:04:57', '2022-06-09 14:04:57');
INSERT INTO `t_poster` VALUES ('3', '3', '海兽之子', '/images/hszz_d3d1d693-2237-430a-bc1e-1bcb786125f7.jpg', '1', '2022-06-09 10:37:28', '2022-06-09 10:37:28');
INSERT INTO `t_poster` VALUES ('4', '4', '孔子', '/images/kz_67b1dd9b-1906-48f2-81b0-4683ee0785dc.jpg', '0', '2022-06-09 10:37:52', '2022-06-09 10:37:52');

-- ----------------------------
-- Table structure for `t_registration`
-- ----------------------------
DROP TABLE IF EXISTS `t_registration`;
CREATE TABLE `t_registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `aid` int(11) NOT NULL COMMENT '活动主键（关联t_activity表）',
  `uid` int(11) NOT NULL COMMENT '用户主键（关联t_user表）',
  `create_time` datetime NOT NULL COMMENT '添加时间（格式2022-01-14\\n11:37:03）',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='4.2.9 活动参与表（t_registration）';

-- ----------------------------
-- Records of t_registration
-- ----------------------------
INSERT INTO `t_registration` VALUES ('2', '1', '1', '2022-05-18 09:49:47', '2022-05-18 09:49:47');
INSERT INTO `t_registration` VALUES ('3', '2', '1', '2022-05-18 09:51:02', '2022-05-18 09:51:02');
INSERT INTO `t_registration` VALUES ('4', '1', '2', '2022-06-07 10:52:56', '2022-06-07 10:52:56');
INSERT INTO `t_registration` VALUES ('5', '1', '2', '2022-06-07 11:15:09', '2022-06-07 11:15:09');
INSERT INTO `t_registration` VALUES ('6', '6', '2', '2022-06-09 16:14:37', '2022-06-09 16:14:37');

-- ----------------------------
-- Table structure for `t_seat`
-- ----------------------------
DROP TABLE IF EXISTS `t_seat`;
CREATE TABLE `t_seat` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `aid` int(11) NOT NULL COMMENT '排片id',
  `seat` int(11) NOT NULL COMMENT '已选座位号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seat
-- ----------------------------
INSERT INTO `t_seat` VALUES ('1', '1', '3', '2022-05-17 10:56:08', '2022-05-17 10:56:10');
INSERT INTO `t_seat` VALUES ('2', '1', '4', '2022-05-17 10:56:36', '2022-05-17 10:56:39');
INSERT INTO `t_seat` VALUES ('3', '2', '5', '2022-05-17 10:56:51', '2022-05-17 10:56:53');
INSERT INTO `t_seat` VALUES ('4', '2', '6', '2022-05-17 10:57:00', '2022-05-17 10:57:03');
INSERT INTO `t_seat` VALUES ('5', '2', '7', '2022-05-17 10:57:11', '2022-05-17 10:57:13');
INSERT INTO `t_seat` VALUES ('23', '1', '1', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('24', '1', '2', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('25', '1', '3', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('26', '1', '1', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('27', '1', '2', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('28', '1', '3', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('29', '1', '6', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('30', '1', '7', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('31', '1', '8', '2022-05-18 11:12:39', '2022-05-18 11:12:39');
INSERT INTO `t_seat` VALUES ('32', '2', '20', '2022-06-07 10:56:42', '2022-06-07 10:56:42');
INSERT INTO `t_seat` VALUES ('33', '2', '21', '2022-06-07 10:56:42', '2022-06-07 10:56:42');
INSERT INTO `t_seat` VALUES ('34', '2', '22', '2022-06-07 10:56:42', '2022-06-07 10:56:42');
INSERT INTO `t_seat` VALUES ('35', '2', '23', '2022-06-07 10:56:42', '2022-06-07 10:56:42');
INSERT INTO `t_seat` VALUES ('36', '7', '6', '2022-06-09 14:07:12', '2022-06-09 14:07:12');
INSERT INTO `t_seat` VALUES ('37', '7', '7', '2022-06-09 14:07:12', '2022-06-09 14:07:12');
INSERT INTO `t_seat` VALUES ('38', '7', '8', '2022-06-09 14:07:12', '2022-06-09 14:07:12');
INSERT INTO `t_seat` VALUES ('39', '7', '9', '2022-06-09 14:07:12', '2022-06-09 14:07:12');
INSERT INTO `t_seat` VALUES ('40', '9', '19', '2022-06-09 14:11:28', '2022-06-09 14:11:28');
INSERT INTO `t_seat` VALUES ('41', '9', '20', '2022-06-09 14:11:28', '2022-06-09 14:11:28');
INSERT INTO `t_seat` VALUES ('42', '9', '22', '2022-06-09 14:11:28', '2022-06-09 14:11:28');
INSERT INTO `t_seat` VALUES ('43', '9', '21', '2022-06-09 14:11:28', '2022-06-09 14:11:28');
INSERT INTO `t_seat` VALUES ('44', '9', '6', '2022-06-09 14:34:05', '2022-06-09 14:34:05');
INSERT INTO `t_seat` VALUES ('45', '9', '7', '2022-06-09 14:34:05', '2022-06-09 14:34:05');
INSERT INTO `t_seat` VALUES ('46', '9', '8', '2022-06-09 14:34:05', '2022-06-09 14:34:05');
INSERT INTO `t_seat` VALUES ('47', '9', '9', '2022-06-09 14:34:05', '2022-06-09 14:34:05');
INSERT INTO `t_seat` VALUES ('48', '9', '24', '2022-06-09 14:42:17', '2022-06-09 14:42:17');
INSERT INTO `t_seat` VALUES ('49', '9', '25', '2022-06-09 14:42:17', '2022-06-09 14:42:17');
INSERT INTO `t_seat` VALUES ('50', '9', '24', '2022-06-09 14:46:53', '2022-06-09 14:46:53');
INSERT INTO `t_seat` VALUES ('51', '9', '24', '2022-06-09 14:48:57', '2022-06-09 14:48:57');
INSERT INTO `t_seat` VALUES ('52', '9', '24', '2022-06-09 15:01:28', '2022-06-09 15:01:28');
INSERT INTO `t_seat` VALUES ('53', '9', '24', '2022-06-09 15:03:31', '2022-06-09 15:03:31');
INSERT INTO `t_seat` VALUES ('54', '9', '24', '2022-06-09 15:04:56', '2022-06-09 15:04:56');
INSERT INTO `t_seat` VALUES ('55', '9', '24', '2022-06-09 15:05:19', '2022-06-09 15:05:19');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '''主键''',
  `username` varchar(255) NOT NULL COMMENT '''用户名''',
  `password` varchar(255) NOT NULL COMMENT '''密码''',
  `slat` char(10) NOT NULL COMMENT '''盐''',
  `slat_index` int(11) NOT NULL COMMENT '''索引''',
  `avatar` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL COMMENT '''昵称''',
  `email` varchar(255) DEFAULT NULL COMMENT '''邮箱''',
  `birthday` datetime DEFAULT NULL COMMENT '生日（格式2022-01-02）\n',
  `gender` varchar(2) DEFAULT NULL COMMENT '''性别（男生；女生）''',
  `info` varchar(255) DEFAULT NULL COMMENT '''个人简介''',
  `create_time` datetime DEFAULT NULL COMMENT '''创建时间（格式2022-01-14''',
  `update_time` datetime DEFAULT NULL COMMENT '''更新时间（格式2022-01-14''',
  `error_num` int(11) NOT NULL DEFAULT '0' COMMENT '''错误次数''',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '''状态''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='4.2.1 用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'username1', 'EB341820CD3A3485461A61B1E97D31B1', 'JyOqjmvRrm', '4', '/images/1_2365b535-67af-4a54-b5db-20367acc45ce.jpg', 'nick1', '1111111111@qq.com', '2000-02-01 16:00:00', '女', 'username1info', '2022-06-09 01:46:11', '2022-06-09 01:46:11', '0', '0');
INSERT INTO `t_user` VALUES ('2', 'username2', 'E801B5CA9D07015213B73B8BE64D0B8B', 'yzjHZuKznL', '5', '/images/2_54ddfb44-febc-443d-83d6-b3a97bc772c1.jpg', 'nick2', '2222222222@qq.com', '2001-02-07 16:00:00', '男', 'username2info', '2022-06-09 02:02:09', '2022-06-09 02:02:09', '0', '0');
INSERT INTO `t_user` VALUES ('3', 'username3', '8F341C20673E34C54A1EA1F18DB731F1', 'BeiIkozSsV', '3', '/images/3_5a3d758e-50c2-41e2-9e57-a73de2e00f5a.jpg', 'nick3', '3333333333@qq.com', '1999-10-13 16:00:00', '男', 'username3', '2022-06-09 02:03:14', '2022-06-09 02:03:14', '0', '0');
INSERT INTO `t_user` VALUES ('4', 'username4', '5F781C20347E78C98A1EA1F15DB471F1', 'VirlpZXlWB', '8', '/images/4_c955c520-cb59-493b-aff3-85713267eac3.jpg', 'nick4', '4444444@qq.com', '2000-07-10 16:00:00', '男', 'username4info', '2022-06-09 01:49:45', '2022-06-09 01:49:45', '0', '0');
INSERT INTO `t_user` VALUES ('5', 'username5', 'EB341820CD3A3485461A61B1E97D31B1', 'OvUHohzdjd', '0', '/images/5_5d7acee5-c039-4231-acdb-4e523a0e1022.jpg', 'nick5', '5555555555@qq.com', '2002-10-16 16:00:00', '男', 'username5info', '2022-06-09 02:05:22', '2022-06-09 02:05:22', '0', '0');
INSERT INTO `t_user` VALUES ('6', 'username6', 'EB9A7386CD959A30A17517B7E42D97B7', 'cKHfvKVVQQ', '9', '/images/6_e7791ea8-66ae-4144-b4fc-5abc724e9593.jpg', 'nick6', '6666666666@qq.com', '2005-07-18 16:00:00', '男', 'username6info', '2022-06-09 02:07:48', '2022-06-09 02:07:48', '0', '0');

-- ----------------------------
-- Table structure for `t_worker`
-- ----------------------------
DROP TABLE IF EXISTS `t_worker`;
CREATE TABLE `t_worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像（图片id，关联t_film.id）',
  `nickname` varchar(255) NOT NULL COMMENT '昵称',
  `phone` varchar(255) NOT NULL COMMENT '电话',
  `entry` varchar(255) NOT NULL COMMENT '状态1-在职 0-离职',
  `gender` varchar(250) NOT NULL COMMENT '性别（男；女）',
  `department` varchar(255) NOT NULL COMMENT '部门',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间（格式2022-01-14\\n11:37:03）',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间（格式2022-01-14\\n11:37:03）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='4.2.3 客服表（t_worker）';

-- ----------------------------
-- Records of t_worker
-- ----------------------------
INSERT INTO `t_worker` VALUES ('4', 'worker1', '123456', '/images/3-1_17bd99d8-47a8-4fc2-8120-424f2d4ba18b.jpg', 'wnick1', '31111111111', '1', '女', '运营部', '2022-06-09 07:14:38', '2022-06-09 08:17:43');
INSERT INTO `t_worker` VALUES ('5', 'worker2', '123456', '/images/3-2_4b2216e9-f7e6-49df-a6c5-f4903a695cca.jpg', 'wnick2', '32222222222', '1', '男', '运营部', '2022-06-09 07:15:25', '2022-06-09 07:15:25');

-- ----------------------------
-- Table structure for `t_worker_evaluate`
-- ----------------------------
DROP TABLE IF EXISTS `t_worker_evaluate`;
CREATE TABLE `t_worker_evaluate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `wid` int(11) NOT NULL COMMENT '客服主键（关联t_worker表）',
  `uid` int(11) NOT NULL COMMENT '用户主键（关联t_user表）',
  `content` varchar(255) DEFAULT NULL COMMENT '回复内容',
  `type` varchar(255) NOT NULL COMMENT '类型(满意；不满意；非常满意)',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间（格式2022-01-14\\n11:37:03）',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='4.2.7 客服评价表（t_worker_evaluate）';

-- ----------------------------
-- Records of t_worker_evaluate
-- ----------------------------
INSERT INTO `t_worker_evaluate` VALUES ('37', '5', '2', 'aaaa', '满意', '2022-06-09 07:54:28', '2022-06-09 16:15:47');
INSERT INTO `t_worker_evaluate` VALUES ('38', '5', '2', 'bbbb', '满意', '2022-06-09 07:55:52', '2022-06-09 16:15:43');
INSERT INTO `t_worker_evaluate` VALUES ('39', '5', '2', 'cccc', '满意', '2022-06-09 08:04:13', '2022-06-09 16:15:39');
INSERT INTO `t_worker_evaluate` VALUES ('40', '5', '2', 'dddd', '满意', '2022-06-09 08:05:53', '2022-06-09 16:15:37');
INSERT INTO `t_worker_evaluate` VALUES ('41', '5', '2', 'eeee', '不满意', '2022-06-09 08:06:35', '2022-06-09 16:15:35');
INSERT INTO `t_worker_evaluate` VALUES ('42', '4', '2', 'ffff', '满意', '2022-06-09 08:09:30', '2022-06-09 16:15:32');
INSERT INTO `t_worker_evaluate` VALUES ('43', '5', '2', 'gggg', '非常满意', '2022-06-09 08:09:40', '2022-06-09 16:15:04');
INSERT INTO `t_worker_evaluate` VALUES ('44', '4', '2', 'hhhh', '满意', '2022-06-09 08:10:57', '2022-06-09 08:10:57');
