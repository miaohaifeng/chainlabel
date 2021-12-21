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

 Date: 11/03/2021 16:41:52 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `debank_support_protocal`
-- ----------------------------
DROP TABLE IF EXISTS `debank_support_protocal`;
CREATE TABLE `debank_support_protocal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `chain` varchar(255) DEFAULT NULL,
  `debank_id` varchar(255) DEFAULT NULL,
  `name_en` varchar(255) DEFAULT NULL,
  `name_ch` varchar(255) DEFAULT NULL,
  `priority` int DEFAULT NULL,
  `site_url` varchar(255) DEFAULT NULL,
  `platform_token_id` varchar(255) DEFAULT NULL,
  `spider_date` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6509 DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
