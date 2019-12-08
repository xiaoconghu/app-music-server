/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-08 17:39:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cd
-- ----------------------------
DROP TABLE IF EXISTS `cd`;
CREATE TABLE `cd` (
  `cd_name` varchar(255) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `cd_pic` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cd
-- ----------------------------

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cd_id` int(11) DEFAULT NULL,
  `song_name` varchar(255) DEFAULT NULL,
  `song_url` varchar(255) DEFAULT NULL,
  `singer` varchar(255) DEFAULT NULL,
  `song_pic` varchar(255) DEFAULT NULL,
  `song_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES ('1', '0', '只要平凡你好', 'D:/music/只要平凡.mp3', '张杰-学霸', '', 'ss', '');
INSERT INTO `song` VALUES ('3', '0', '喜欢的歌', 'D:/music/喜欢的歌.mp3', '张杰', '暂无', 'cd', '简版');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1582700216', '1216621881@qq.com', null);
INSERT INTO `user` VALUES ('2', 'admin01', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1582702016', 'huxc10086@163.com', '2019-12-08 15:29:51');
INSERT INTO `user` VALUES ('3', 'admin1111', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '11111111111', 'huxc10086@163.com', '2019-12-08 15:32:20');
INSERT INTO `user` VALUES ('4', 'admin2222', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1515151515', 'huxc10086@163.com', '2019-12-08 15:35:13');
INSERT INTO `user` VALUES ('5', 'admin1122222', 'admin1122222', 'e10adc3949ba59abbe56e057f20f883e', '1111111', 'huxc10086@163.com', '2019-12-08 15:38:38');
INSERT INTO `user` VALUES ('6', '胡志勇', '胡志勇', 'e10adc3949ba59abbe56e057f20f883e', '11111', 'huxc10086@163.com', '2019-12-08 15:39:23');
INSERT INTO `user` VALUES ('8', '胡孝聪', '胡孝聪', 'e10adc3949ba59abbe56e057f20f883e', '15151515', 'huxc10086@163.com', '2019-12-08 15:40:24');
INSERT INTO `user` VALUES ('9', 'test001', 'test001', 'fa820cc1ad39a4e99283e9fa555035ec', '15154545454545', 'huxc10086@163.com', '2019-12-08 17:27:16');
