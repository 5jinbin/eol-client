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

 Date: 31/08/2023 11:12:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for eol_user
-- ----------------------------
DROP TABLE IF EXISTS `eol_user`;
CREATE TABLE `eol_user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` enum('male','female') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'male' COMMENT '性别',
  `age` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '年龄',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `education` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学历',
  `introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '自我介绍',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1012 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for eol_article
-- ----------------------------
DROP TABLE IF EXISTS `eol_article`;
CREATE TABLE `eol_article`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '文章编号',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作者',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '简述',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `favorite_count` int UNSIGNED NULL DEFAULT 0 COMMENT '收藏次数',
  `study_count` int UNSIGNED NULL DEFAULT 0 COMMENT '学习次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1001 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章阅读表' ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for eol_announcement
-- ----------------------------
DROP TABLE IF EXISTS `eol_announcement`;
CREATE TABLE `eol_announcement`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '公告编号',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
