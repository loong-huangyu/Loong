/*
Navicat MySQL Data Transfer

Source Server         : sql
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : db_stu

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-03-13 19:44:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for math
-- ----------------------------
DROP TABLE IF EXISTS `math`;
CREATE TABLE `math` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of math
-- ----------------------------
INSERT INTO `math` VALUES ('1', '90', '1');
INSERT INTO `math` VALUES ('2', '90', '1');
INSERT INTO `math` VALUES ('3', '77', '2');
INSERT INTO `math` VALUES ('4', '88', '2');
INSERT INTO `math` VALUES ('5', '95', '3');
INSERT INTO `math` VALUES ('6', '100', '1');
INSERT INTO `math` VALUES ('7', '88', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'huangyu', '123456');
INSERT INTO `user` VALUES ('2', 'asda', 'asd');
