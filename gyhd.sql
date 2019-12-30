/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : 127.0.0.1:3306
Source Database       : d3xia

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-12-30 11:45:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `content` varchar(1020) DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '审核状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('33', '义卖活动', '2019-02-09在xxxxx,举行毕业学生书籍的义卖活动', '1');
INSERT INTO `activity` VALUES ('35', '众筹', '2019-02-19众筹', '2');
INSERT INTO `activity` VALUES ('36', '集资购买全民健身器材', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', '2');
INSERT INTO `activity` VALUES ('37', '绿色出行', '健康出行', '2');
INSERT INTO `activity` VALUES ('38', '弘扬中国梦', '从基础做起', '3');
INSERT INTO `activity` VALUES ('39', '新活动', '啊啊啊啊啊啊啊啊啊啊啊sdfdsfsdf', '0');
INSERT INTO `activity` VALUES ('40', '李四创建活动test', 'aefdsfsdfsddd', '1');

-- ----------------------------
-- Table structure for create_activity
-- ----------------------------
DROP TABLE IF EXISTS `create_activity`;
CREATE TABLE `create_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of create_activity
-- ----------------------------
INSERT INTO `create_activity` VALUES ('4', '1', '33');
INSERT INTO `create_activity` VALUES ('5', '1', '34');
INSERT INTO `create_activity` VALUES ('6', '1', '39');
INSERT INTO `create_activity` VALUES ('7', '2', '40');

-- ----------------------------
-- Table structure for join_activity
-- ----------------------------
DROP TABLE IF EXISTS `join_activity`;
CREATE TABLE `join_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '0未通过1已通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of join_activity
-- ----------------------------
INSERT INTO `join_activity` VALUES ('4', '2', '33', '好，非常赞', '1');
INSERT INTO `join_activity` VALUES ('5', '2', '34', '不太行', '1');
INSERT INTO `join_activity` VALUES ('6', '1', '33', '晕蛋活动，不值得参加', '1');
INSERT INTO `join_activity` VALUES ('7', '1', '35', '还可以', '1');
INSERT INTO `join_activity` VALUES ('20', '1', '35', '', '1');
INSERT INTO `join_activity` VALUES ('21', '12', '35', '好', '0');
INSERT INTO `join_activity` VALUES ('22', '12', '38', '不错的活动', '0');
INSERT INTO `join_activity` VALUES ('23', '2', '37', '环保', '0');

-- ----------------------------
-- Table structure for logo
-- ----------------------------
DROP TABLE IF EXISTS `logo`;
CREATE TABLE `logo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `picture_link` varchar(100) DEFAULT NULL,
  `show_status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logo
-- ----------------------------
INSERT INTO `logo` VALUES ('1', '/img/logo/1.jpg', '2');
INSERT INTO `logo` VALUES ('2', '/img/logo/2.jpg', '2');
INSERT INTO `logo` VALUES ('3', '/img/logo/3.jpg', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) DEFAULT NULL,
  `real_name` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  `gender` int(1) DEFAULT NULL COMMENT '性别：1 女  2 男',
  `birthday` datetime DEFAULT NULL,
  `user_type` int(1) DEFAULT NULL COMMENT '1管理员  2经理  3普通用户',
  `user_point` int(10) DEFAULT '0' COMMENT '用户积分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', '张三', '1234', '2', '2003-11-12 00:00:07', '1', '21');
INSERT INTO `user` VALUES ('2', 'lisi', '李四', '123', '1', '2010-06-07 10:04:45', '2', '56');
INSERT INTO `user` VALUES ('12', 'wangwu', '王五', '456', '1', '2012-02-01 00:00:00', '2', '6');
INSERT INTO `user` VALUES ('13', 'mengliang', '孟良', '123456', '2', '1997-02-12 15:57:16', '2', '0');
INSERT INTO `user` VALUES ('14', 'renguang', '任广健', '123456', '2', '1998-10-22 15:58:05', '2', '0');
INSERT INTO `user` VALUES ('15', 'mamingrui', '马明睿', '123456', '2', '1998-03-21 15:58:38', '2', '0');
INSERT INTO `user` VALUES ('16', 'qvzili', '曲自立', '123456', '2', '1998-02-05 15:59:17', '2', '0');
