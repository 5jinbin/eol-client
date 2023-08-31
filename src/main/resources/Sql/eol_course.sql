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

 Date: 31/08/2023 11:13:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eol_course
-- ----------------------------
DROP TABLE IF EXISTS `eol_course`;
CREATE TABLE `eol_course`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '封面图片路径',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程视频路径',
  `favorite_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏次数',
  `study_count` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '学习次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1004 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '在线课程表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
