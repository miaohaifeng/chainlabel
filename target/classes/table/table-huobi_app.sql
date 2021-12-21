/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 127.0.0.1
 Source Database       : contract_compare

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : utf-8

 Date: 11/03/2021 16:42:48 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `huobi_app`
-- ----------------------------
DROP TABLE IF EXISTS `huobi_app`;
CREATE TABLE `huobi_app` (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `desc1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `site_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `chain` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
