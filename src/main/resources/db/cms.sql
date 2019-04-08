/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost:3306
 Source Schema         : cms

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : 65001

 Date: 08/04/2019 09:53:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bsy_cert
-- ----------------------------
DROP TABLE IF EXISTS `bsy_cert`;
CREATE TABLE `bsy_cert`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `navicert` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆准运证号',
  `approval_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆审批时间',
  `number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `owned` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车辆所属企业',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程名称',
  `project_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程地址',
  `accommodation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消纳场名称',
  `accommodation_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\r\n消纳地点',
  `permit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '准运证有效期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bsy_cert
-- ----------------------------
INSERT INTO `bsy_cert` VALUES (1, 'TZ201809111020', '', '京AFH329', '北京鑫驰通达货物运输有限公司', '北京地铁7号线东延工程土建施工04合同段', '北京市', '通州区漷县建筑垃圾消纳场(延期2016126)(延期2017119)(延期2018116)(延期2019121)', '工地北门-九德路-万盛南街-张采路-103国道-漷县消纳场', '2018-09-11至2019-06-30');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '描述',
  `sort` decimal(10, 0) NULL DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) NULL DEFAULT 0 COMMENT '父级编号',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '0' COMMENT '删除标记',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_dict_value`(`value`) USING BTREE,
  INDEX `sys_dict_label`(`name`) USING BTREE,
  INDEX `sys_dict_del_flag`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` tinyint(255) NULL DEFAULT 0 COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序值',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '', '', 0, 'fa fa-tasks', 1, '2018-12-03 17:43:47');
INSERT INTO `sys_menu` VALUES (2, 1, '系统菜单', '/sys/menu', 'sys:menu:menu', 1, 'fa fa-th-list', 2, '2018-12-03 17:44:31');
INSERT INTO `sys_menu` VALUES (3, 1, '用户管理', '/sys/user', 'sys:user:user', 1, 'fa fa-user', 3, '2018-12-03 17:45:09');
INSERT INTO `sys_menu` VALUES (4, 1, '角色管理', '/sys/role', 'sys:role:role', 1, 'fa fa-paw', 4, '2018-12-03 17:45:46');
INSERT INTO `sys_menu` VALUES (5, 2, '新增', NULL, 'sys:menu:add', 2, NULL, 0, '2018-12-03 17:50:28');
INSERT INTO `sys_menu` VALUES (6, 2, '编辑', NULL, 'sys:menu:edit', 2, NULL, 0, '2018-12-03 17:50:27');
INSERT INTO `sys_menu` VALUES (7, 2, '删除', '', 'sys:menu:remove', 2, NULL, 0, '2018-12-03 17:50:29');
INSERT INTO `sys_menu` VALUES (8, 3, '新增', '', 'sys:user:add', 2, '', 0, '2018-12-03 17:49:08');
INSERT INTO `sys_menu` VALUES (9, 3, '编辑', '', 'sys:user:edit', 2, '', 0, '2018-12-03 17:49:11');
INSERT INTO `sys_menu` VALUES (10, 3, '删除', '', 'sys:user:remove', 2, '', 0, '2018-12-03 17:49:13');
INSERT INTO `sys_menu` VALUES (11, 4, '新增', NULL, 'sys:role:add', 2, NULL, 0, '2018-12-03 17:50:22');
INSERT INTO `sys_menu` VALUES (12, 4, '编辑', NULL, 'sys:role:edit', 2, NULL, 0, '2018-12-03 17:50:24');
INSERT INTO `sys_menu` VALUES (13, 4, '删除', NULL, 'sys:role:remove', 2, NULL, 0, '2018-12-03 17:50:25');
INSERT INTO `sys_menu` VALUES (14, 0, '系统工具', '', '', 0, 'fa fa-cog', 10, NULL);
INSERT INTO `sys_menu` VALUES (15, 14, '代码生成', '/sys/generator', '', 1, 'fa fa-code', 2, NULL);
INSERT INTO `sys_menu` VALUES (17, 14, '数据字典', '/sys/dict', '', 1, 'fa fa-bars', 1, NULL);
INSERT INTO `sys_menu` VALUES (23, 17, '新增', '', 'sys:dict:add', 2, '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (24, 17, '删除', '', 'sys:dict:remove', 2, '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (25, 14, '任务计划', '/sys/job', '', 1, 'fa fa-calendar-o', 3, NULL);
INSERT INTO `sys_menu` VALUES (26, 0, '外联业务', '', '', 0, 'fa fa-briefcase', 2, NULL);
INSERT INTO `sys_menu` VALUES (27, 26, '二维码管理', '/bsy/cert', 'bsy:cert:cert', 1, 'fa fa-file-photo-o', 1, NULL);
INSERT INTO `sys_menu` VALUES (28, 27, '新增', '', 'bsy:cert:add', 2, '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (29, 27, '删除', '', 'bsy:cert:remove', 2, '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (30, 27, '编辑', '', 'bsy:cert:edit', 2, '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (31, 27, '导入', '', 'bsy:cert:import', 2, '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (32, 3, '重置密码', '', 'sys:user:resetPwd', 2, '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_notify
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify`;
CREATE TABLE `sys_notify`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notify
-- ----------------------------
INSERT INTO `sys_notify` VALUES (1, 'dsa', 'sad', '0', 'asdsa', NULL, '2019-04-01 16:16:56');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '拥有最高权限', '2018-12-03 17:42:09');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 2, 25);
INSERT INTO `sys_role_menu` VALUES (2, 2, 15);
INSERT INTO `sys_role_menu` VALUES (3, 2, 17);
INSERT INTO `sys_role_menu` VALUES (4, 2, 24);
INSERT INTO `sys_role_menu` VALUES (5, 2, 23);
INSERT INTO `sys_role_menu` VALUES (6, 2, 14);
INSERT INTO `sys_role_menu` VALUES (7, 2, 5);
INSERT INTO `sys_role_menu` VALUES (8, 2, -1);
INSERT INTO `sys_role_menu` VALUES (9, 2, 1);
INSERT INTO `sys_role_menu` VALUES (10, 2, 2);
INSERT INTO `sys_role_menu` VALUES (131, 1, 31);
INSERT INTO `sys_role_menu` VALUES (132, 1, 30);
INSERT INTO `sys_role_menu` VALUES (133, 1, 29);
INSERT INTO `sys_role_menu` VALUES (134, 1, 28);
INSERT INTO `sys_role_menu` VALUES (135, 1, 25);
INSERT INTO `sys_role_menu` VALUES (136, 1, 24);
INSERT INTO `sys_role_menu` VALUES (137, 1, 23);
INSERT INTO `sys_role_menu` VALUES (138, 1, 15);
INSERT INTO `sys_role_menu` VALUES (139, 1, 13);
INSERT INTO `sys_role_menu` VALUES (140, 1, 12);
INSERT INTO `sys_role_menu` VALUES (141, 1, 11);
INSERT INTO `sys_role_menu` VALUES (142, 1, 10);
INSERT INTO `sys_role_menu` VALUES (143, 1, 9);
INSERT INTO `sys_role_menu` VALUES (144, 1, 8);
INSERT INTO `sys_role_menu` VALUES (145, 1, 7);
INSERT INTO `sys_role_menu` VALUES (146, 1, 6);
INSERT INTO `sys_role_menu` VALUES (147, 1, 5);
INSERT INTO `sys_role_menu` VALUES (148, 1, 27);
INSERT INTO `sys_role_menu` VALUES (149, 1, 26);
INSERT INTO `sys_role_menu` VALUES (150, 1, 17);
INSERT INTO `sys_role_menu` VALUES (151, 1, 14);
INSERT INTO `sys_role_menu` VALUES (152, 1, 4);
INSERT INTO `sys_role_menu` VALUES (153, 1, 2);
INSERT INTO `sys_role_menu` VALUES (154, 1, -1);
INSERT INTO `sys_role_menu` VALUES (155, 1, 32);
INSERT INTO `sys_role_menu` VALUES (156, 1, 3);
INSERT INTO `sys_role_menu` VALUES (157, 1, 1);

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `bean_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `job_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `spring_bean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES (1, '0/10 * * * * ?', 'run1', '1', '111', 'com.yaa.cms.task.WelcomeJob', '1', 'group1', NULL, 'welcomJob', '2019-01-03 17:12:31', '2019-01-03 17:12:28');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '可用 1 不可用0',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '随机字符串作为salt因子',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'yang', 'admin', '982d3b4564f2cf1f3c27c3dcafe5e705', 'yanghbwork@163.com', 1, '238618edccb4395e7a2bcd852ad06b95', '2018-12-03 17:52:09');

-- ----------------------------
-- Table structure for sys_user_notify
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_notify`;
CREATE TABLE `sys_user_notify`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `notify_id` int(255) NULL DEFAULT NULL COMMENT '消息ID',
  `is_read` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0未读 1已读',
  `read_time` datetime(0) NULL DEFAULT NULL COMMENT '阅读时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
