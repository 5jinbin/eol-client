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

 Date: 31/08/2023 11:12:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eol_listening
-- ----------------------------
DROP TABLE IF EXISTS `eol_listening`;
CREATE TABLE `eol_listening`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '听力编号',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `audio_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '音频路径',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '听力内容',
  `difficulty` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '难度等级',
  `favorite_count` int UNSIGNED NULL DEFAULT 0 COMMENT '收藏次数',
  `study_count` int UNSIGNED NULL DEFAULT 0 COMMENT '学习次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1002 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '听力训练表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
