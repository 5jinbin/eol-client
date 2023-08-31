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

 Date: 31/08/2023 11:12:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eol_word_study_record
-- ----------------------------
DROP TABLE IF EXISTS `eol_word_study_record`;
CREATE TABLE `eol_word_study_record`  (
  `user_id` int UNSIGNED NOT NULL COMMENT '用户ID',
  `word_id` int UNSIGNED NOT NULL COMMENT '单词ID',
  `study_date` date NOT NULL COMMENT '记录日期',
  PRIMARY KEY (`user_id`, `word_id`, `study_date`) USING BTREE,
  INDEX `fk_word_id`(`word_id` ASC) USING BTREE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `eol_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_word_id` FOREIGN KEY (`word_id`) REFERENCES `eol_word` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单词学习记录表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
