/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : boot

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-05-05 15:45:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mood
-- ----------------------------
DROP TABLE IF EXISTS `mood`;
CREATE TABLE `mood` (
  `moodID` varchar(11) NOT NULL COMMENT '主键',
  `content` varchar(50) DEFAULT NULL COMMENT '内容',
  `userID` varchar(11) DEFAULT NULL COMMENT '用户主键',
  `praiseNum` int(11) DEFAULT NULL COMMENT '点赞数',
  `publishTime` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`moodID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='说说表';

-- ----------------------------
-- Records of mood
-- ----------------------------
INSERT INTO `mood` VALUES ('1', '第一条说说', '1', '1', '2018-12-24 21:30:50');
INSERT INTO `mood` VALUES ('2', '元旦快乐', '2', '0', '2019-01-01 20:49:03');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(10) NOT NULL AUTO_INCREMENT COMMENT '角色主键',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ADMIN');
INSERT INTO `role` VALUES ('2', 'USER');

-- ----------------------------
-- Table structure for test_extension
-- ----------------------------
DROP TABLE IF EXISTS `test_extension`;
CREATE TABLE `test_extension` (
  `extensionId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tableName` varchar(20) NOT NULL COMMENT '表名',
  `attributeName` varchar(20) DEFAULT NULL COMMENT '字段名称',
  `dataType` int(2) DEFAULT NULL COMMENT '值类型：1-int 2-double 3-varchar',
  PRIMARY KEY (`extensionId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_extension
-- ----------------------------
INSERT INTO `test_extension` VALUES ('1', 'test_main', '总数', '1');
INSERT INTO `test_extension` VALUES ('2', 'test_main', '比率', '2');
INSERT INTO `test_extension` VALUES ('3', 'test_main', 'bi', '3');

-- ----------------------------
-- Table structure for test_main
-- ----------------------------
DROP TABLE IF EXISTS `test_main`;
CREATE TABLE `test_main` (
  `testId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `testName` varchar(255) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`testId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_main
-- ----------------------------
INSERT INTO `test_main` VALUES ('1', 'test');

-- ----------------------------
-- Table structure for test_value_double
-- ----------------------------
DROP TABLE IF EXISTS `test_value_double`;
CREATE TABLE `test_value_double` (
  `valueId` int(11) NOT NULL AUTO_INCREMENT COMMENT '值ID',
  `mainId` int(11) NOT NULL COMMENT '主表ID',
  `extensionId` int(11) NOT NULL COMMENT '扩展字段ID',
  `value` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`valueId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_value_double
-- ----------------------------

-- ----------------------------
-- Table structure for test_value_int
-- ----------------------------
DROP TABLE IF EXISTS `test_value_int`;
CREATE TABLE `test_value_int` (
  `valueId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mainId` int(11) DEFAULT NULL COMMENT '主表ID',
  `extensionId` int(11) DEFAULT NULL COMMENT '字段ID',
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`valueId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of test_value_int
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userName` varchar(10) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'ziv', '1234567');
INSERT INTO `user` VALUES ('2', 'qing', '1234567');
INSERT INTO `user` VALUES ('3', 'redis', '80088');

-- ----------------------------
-- Table structure for user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `user_role_rel`;
CREATE TABLE `user_role_rel` (
  `userId` int(11) NOT NULL COMMENT '用户主键',
  `roleId` int(11) NOT NULL COMMENT '角色主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role_rel
-- ----------------------------
INSERT INTO `user_role_rel` VALUES ('1', '1');
INSERT INTO `user_role_rel` VALUES ('2', '2');

-- ----------------------------
-- Procedure structure for process_getValue
-- ----------------------------
DROP PROCEDURE IF EXISTS `process_getValue`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `process_getValue`()
BEGIN
  # 用于判断循环结束
  DECLARE done INT DEFAULT 0;
  DECLARE attributeName VARCHAR(100);
  DECLARE dataType INT;
  # 定义游标
	DECLARE extensions CURSOR FOR SELECT a.attributeName, a.dataType FROM test_extension a;
  # 定义循环结束标识修改的逻辑
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  # 打开游标
	OPEN extensions;
  # 开始循环
	ex_loop : LOOP
		FETCH extensions INTO attributeName,dataType;
		IF done THEN
			LEAVE ex_loop;
		END IF;
		SELECT attributeName, dataType;
	# 关闭游标
	END LOOP;
	CLOSE extensions;
END
;;
DELIMITER ;
