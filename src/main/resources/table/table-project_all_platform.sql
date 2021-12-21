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

 Date: 11/03/2021 16:43:04 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `project_all_platform`
-- ----------------------------
DROP TABLE IF EXISTS `project_all_platform`;
CREATE TABLE `project_all_platform` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unique_site_url` varchar(500) DEFAULT NULL,
  `debank_id` varchar(500) DEFAULT NULL,
  `debank_name` varchar(500) DEFAULT NULL,
  `debank_site_url` varchar(500) DEFAULT NULL,
  `bsc_id` varchar(500) DEFAULT NULL,
  `bsc_name` varchar(500) DEFAULT NULL,
  `bsc_site_url` varchar(500) DEFAULT NULL,
  `huobi_ib` varchar(500) DEFAULT NULL,
  `huobi_name` varchar(500) DEFAULT NULL,
  `huobi_site_url` varchar(500) DEFAULT NULL,
  `dappar_id` varchar(500) DEFAULT NULL,
  `dappar_name` varchar(500) DEFAULT NULL,
  `dappar_site_url` varchar(500) DEFAULT NULL,
  `chain` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
