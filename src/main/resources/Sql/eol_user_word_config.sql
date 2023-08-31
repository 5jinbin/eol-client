/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : eol

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 31/08/2023 11:12:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eol_user_word_config
-- ----------------------------
DROP TABLE IF EXISTS `eol_user_word_config`;
CREATE TABLE `eol_user_word_config`  (
  `user_id` int UNSIGNED NOT NULL COMMENT '用户ID',
  `default_difficulty` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '默认单词书难度',
  `daily_task_count` tinyint UNSIGNED NOT NULL DEFAULT 10 COMMENT '每日单词任务数量',
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `fk_user_word_config_user_id` FOREIGN KEY (`user_id`) REFERENCES `eol_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户单词配置表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
