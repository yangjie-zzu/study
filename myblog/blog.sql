/*
Navicat MySQL Data Transfer

Source Server         : jsp
Source Server Version : 50636
Source Host           : 127.0.0.1:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-11-01 22:02:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `publishtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `publisher` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `hit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `publisher` (`publisher`),
  KEY `type` (`type`),
  CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`publisher`) REFERENCES `blogger` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of articles
-- ----------------------------

-- ----------------------------
-- Table structure for blogger
-- ----------------------------
DROP TABLE IF EXISTS `blogger`;
CREATE TABLE `blogger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `profile` text,
  `nickName` varchar(50) DEFAULT NULL,
  `sign` varchar(100) DEFAULT NULL,
  `imageName` varchar(100) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `emailvalidate` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger
-- ----------------------------
INSERT INTO `blogger` VALUES ('1', 'yangjie', '1CA1CB9799A79BB5E76A0C7A2EF181E7', null, null, null, null, '2017-10-19 00:04:40', null, '0');
INSERT INTO `blogger` VALUES ('2', 'yangjie-zzu', '1CA1CB9799A79BB5E76A0C7A2EF181E7', null, null, null, null, '2017-10-19 00:04:40', null, '0');
INSERT INTO `blogger` VALUES ('3', 'yangjie-test', '1CA1CB9799A79BB5E76A0C7A2EF181E7', '', 'yangjie-test', '', null, '2017-11-01 21:01:39', '123@qq.com', '0');
INSERT INTO `blogger` VALUES ('4', 'yangjie-test1', '1CA1CB9799A79BB5E76A0C7A2EF181E7', '', 'yangjie-test1', '', null, '2017-11-01 21:06:16', '1234@qq.com', '0');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `publishtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `author` varchar(255) DEFAULT NULL,
  `artid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `artid` (`artid`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`artid`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `name` varchar(255) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of types
-- ----------------------------
DROP TRIGGER IF EXISTS `updatetime`;
DELIMITER ;;
CREATE TRIGGER `updatetime` AFTER INSERT ON `comments` FOR EACH ROW BEGIN
	update articles set lasttime=NOW() where comments.artid=id;
END
;;
DELIMITER ;
