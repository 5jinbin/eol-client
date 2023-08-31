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

 Date: 31/08/2023 11:13:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eol_word
-- ----------------------------
DROP TABLE IF EXISTS `eol_word`;
CREATE TABLE `eol_word`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '单词编号',
  `word` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '单词',
  `uk_pron` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '英式发音',
  `uk_pron_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '英式发音音频路径',
  `us_pron` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '美式发音',
  `us_pron_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '美式发音音频路径',
  `definition` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '释义',
  `example_sentence` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '例句',
  `example_translation` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '例句翻译',
  `difficulty` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '难度等级',
  `favorite_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏次数',
  `study_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '学习次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单词表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
