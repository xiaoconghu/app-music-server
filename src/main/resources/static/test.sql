/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50647
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50647
 File Encoding         : 65001

 Date: 01/03/2020 17:56:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cd
-- ----------------------------
DROP TABLE IF EXISTS `cd`;
CREATE TABLE `cd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `singer_id` int(11) DEFAULT NULL,
  `cd_name` varchar(255) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `cd_pic` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `singer_id` (`singer_id`),
  CONSTRAINT `singer_id` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cd
-- ----------------------------
BEGIN;
INSERT INTO `cd` VALUES (7, 13, '胡小聪的歌单', NULL, '2020-02-13 17:25:10', '', '');
INSERT INTO `cd` VALUES (8, 7, '富士山下', NULL, '2020-02-13 18:19:29', '', '');
INSERT INTO `cd` VALUES (10, 6, '刘俊杰的歌单', NULL, '2020-02-13 18:20:59', '', '这里有好听的歌哦');
INSERT INTO `cd` VALUES (11, 5, '周杰伦歌单', NULL, '2020-02-13 18:21:30', '', '杰伦的所有歌曲');
COMMIT;

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `singer_name` varchar(255) DEFAULT NULL,
  `singer_nick_name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `singer_pic` varchar(255) DEFAULT NULL,
  `singer_gender` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of singer
-- ----------------------------
BEGIN;
INSERT INTO `singer` VALUES (5, '周杰伦', '小伦伦', NULL, '1', '1', '1');
INSERT INTO `singer` VALUES (6, '刘俊杰', '小刘', NULL, '', '0', '');
INSERT INTO `singer` VALUES (7, '陈奕迅1', '小陈1', '2020-02-15 09:20:03', '', '0', '');
INSERT INTO `singer` VALUES (8, '邓丽君', '小邓1', '2020-02-13 10:35:45', '', '0', '');
INSERT INTO `singer` VALUES (9, '潘玮柏', '小潘1', '2020-02-13 10:35:11', '', '0', '');
INSERT INTO `singer` VALUES (10, '五月天', '小五1', '2020-02-13 10:34:55', '', '0', '');
INSERT INTO `singer` VALUES (13, '胡孝聪1', '聪聪1', '2020-02-15 09:20:17', '', '1', '');
INSERT INTO `singer` VALUES (14, '刘亦菲', '菲菲', NULL, '', '2', '');
INSERT INTO `singer` VALUES (21, '汪峰峰', '小汪汪', NULL, NULL, '1', NULL);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song
-- ----------------------------
BEGIN;
INSERT INTO `song` VALUES (7, 7, '最远的你是我最近的爱', '/Users/huxiaocong/music/最远的你是我最近的爱.mp3', NULL, '', '', '');
INSERT INTO `song` VALUES (8, 7, '17岁', '/Users/huxiaocong/music/17岁.mp3', NULL, '', '', '');
INSERT INTO `song` VALUES (10, 7, '我曾用心的爱着你', '/Users/huxiaocong/music/我曾用心的爱着你.mp3', NULL, NULL, NULL, NULL);
INSERT INTO `song` VALUES (13, 8, 'Amy Deasismont - Heartbeats', '/Users/huxiaocong/music/Amy Deasismont - Heartbeats.mp3', NULL, NULL, NULL, NULL);
INSERT INTO `song` VALUES (14, 0, '刘德华 - 17岁(粤)', '/Users/huxiaocong/music/刘德华 - 17岁(粤).mp3', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1582700216', '1216621881@qq.com', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
