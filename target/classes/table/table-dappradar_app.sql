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

 Date: 11/03/2021 16:42:09 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dappradar_app`
-- ----------------------------
DROP TABLE IF EXISTS `dappradar_app`;
CREATE TABLE `dappradar_app` (
  `dappradar_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `protocal` varchar(255) DEFAULT NULL,
  `spider_date` varchar(255) DEFAULT NULL,
  `site_url` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deeplink` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
