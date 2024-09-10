/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : localhost:3306
 Source Schema         : book-v

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 29/04/2024 10:42:58
*/
create database `book-v`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公告标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '公告内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (1, '看来飞机喀什的叫法是空', '的方式东京佛奥收盘价哦怕涉及到佩斯基的的反对发射点发士大夫士大夫', '2024-03-11 17:42:54');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书名称',
  `author` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书作者',
  `img_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书封面',
  `num` int NULL DEFAULT NULL COMMENT '图书数量',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书简介',
  `press` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书出版社',
  `click_num` int NULL DEFAULT NULL COMMENT '用户点击量',
  `theme` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书主题',
  `tag_id` int NULL DEFAULT NULL COMMENT '图书类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '三国演义', '罗贯中', '1三国演义.jpg', 10, '描写了从东汉末年到西晋初年之间近百年的历史风云，以描写战争为主，诉说了东汉末年的群雄割据混战和魏、蜀、吴三国之间的政治和军事斗争，最终司马炎一统三国，建立晋朝的故事', '黑龙江美术出版社:,2012', NULL, '长篇小说 中国 明代', 1, '2024-02-27 09:32:48');
INSERT INTO `book` VALUES (2, '西游记', '吴承恩', '2西游记.jpg', 10, '由明代小说家吴承恩所写的古典神魔小说，讲述了唐僧师徒四人西天取经的艰辛历程。孙悟空等徒弟保护唐僧一路降妖伏魔，经历了九九八十一难，终于到达西天见到如来佛祖，最终五圣成真的故事。该书以“玄奘取经”这一历史事件为蓝本，经作者的艺术加工，深刻地描绘出明代百姓的社会生活状况。', '长春:,吉林大学出版社:,2011', NULL, '古典小说 中国 明代', 1, NULL);
INSERT INTO `book` VALUES (3, '红楼梦', '曹雪芹', '3红楼梦 .jpg', 10, '《红楼梦》是中国古代章回体长篇小说，又名《石头记》等，被列为中国古典四大名著之首，一般认为是清代作家曹雪芹所著。小说以贾、史、王、薛四大家族的兴衰为背景，以贾宝玉、林黛玉、薛宝钗的爱情婚姻故事为主线，描绘了一些闺阁佳人的人生百态，展现了真正的人性美和悲剧美，是一部从各个角度展现女性美以及中国古代社会百态的史诗性著作。', '北京:,大众文艺出版社:,2008', NULL, '章回小说 中国 清代', 1, NULL);
INSERT INTO `book` VALUES (4, '水浒传', '施耐庵,罗贯中', '4水浒传.jpg', 10, '是中国历史上第一部用白话文写成的章回体长篇小说，是中国四大名著之一。全书通过描写梁山好汉反抗欺压、水泊梁山壮大和受宋朝招安，以及受招安后为宋朝征战，最终消亡的宏大故事，艺术地反映了中国历史上宋江起义从发生、发展直至失败的全过程。', '上海:,上海古籍出版社:,1998', NULL, '长篇小说 中国 明代', 1, NULL);
INSERT INTO `book` VALUES (5, '心安即是归处', '周庆生', '5心安即是归处.jpg', 10, '本书以简洁而深刻的文字探讨了内心的安宁与人生的归宿。书中通过哲理与实例的结合，引导读者在纷繁复杂的世界中寻找内心的平静与真正的归宿。对于追求精神富足和内心平静的人来说，此书如同一盏明灯，指引归途。', '合肥:,安徽文艺出版社:,2015', NULL, '散文集 中国 当代', 1, NULL);
INSERT INTO `book` VALUES (6, '看不见的文学', '杨宁', '11看不见的文学.jpg', 10, '本书根据作者2021年在国内知名视频网站B站开设的视频课程系统整理升华而成。分为文学与世界、文学与作者、文学与读者、文学与文本共四编，具体包括文学本质与审美问题、作者中心观及其解构、文学阐释中的意图问题等十六章内容。', '北京:,中国社会科学出版社:,2022', NULL, '文学理论 理论研究', 1, NULL);
INSERT INTO `book` VALUES (7, '从生活到小说', '(美)罗宾·赫姆利(Robin Hemley)', '12从生活到小说.jpg', 10, '本书探讨了如何把真实的生活写成好的故事，提供了很多富有启发性的练习。书中讨论了从如何记录日常生活、激发创意到如何用真实的逸闻趣事、用真实的场景与人物进行有效创作等众多话题。作者自认是从生活中窃取素材的“骗子”“小偷”，却也同时在书中讨论了与写作相关的法律与伦理问题，讨论了应如何对待从亲友和陌生人那里借用生活经验进行创作的问题', '北京:,中国人民大学出版社:,2018', NULL, '小说创作 创作方法', 1, NULL);
INSERT INTO `book` VALUES (8, '日讲易经解义', '(清) 牛钮等', '13日讲易经解义.jpg', 10, '本书分经、传两部分。经包括六十四卦，传包括系辞上传、系辞下传、说卦传、序卦传、杂卦传。', '海口:,海南出版社:,2012', NULL, NULL, 1, NULL);
INSERT INTO `book` VALUES (9, '我是愤怒的青蛙', '尹奇峰', '6我是愤怒的青蛙.jpg', 10, '通过一只愤怒的青蛙的视角，探讨了现代社会的种种问题。作品以幽默讽刺的笔触，揭示了人们对环境、社会不公等议题的忽视和冷漠。书中充满哲思，既娱乐又引人深思，是对当代生活的一次犀利批判。', '北京:,金盾出版社:,2014', 0, '儿童故事 中国 当代', 2, '2024-03-05 11:32:07');
INSERT INTO `book` VALUES (10, '小王子', '圣爱克苏贝里', '7小王子.jpg', 10, '本书讲述了一位小王子离开自己的星球，探访不同星球上的各种人物，最终抵达地球并领悟生命真谛的奇幻旅程。这本书以简单而深刻的语言，探讨了友谊、爱情、责任和人生的意义，是一部充满哲理和温情的童话', '北京:,中国社会科学出版社:,2002.10', 0, '童话-法国-现代', 2, '2024-03-05 11:32:48');
INSERT INTO `book` VALUES (11, '看童话故事学英语', '罗慕谦编译', '8看童话故事学英语.jpg', 10, '童话故事是孩子们学习英语的好帮手，通过生动的故事情节和丰富的想象力，激发孩子们学习英语的兴趣。在童话故事中，孩子们可以接触到常用的英语单词、短语和句子结构，提升他们的语感和表达能力。同时，童话故事中的道德和价值观也有助于孩子们的成长和教育。', '海口:,南海出版社:,2006', 0, '英语 汉语 对照读物', 2, '2024-03-05 11:33:29');
INSERT INTO `book` VALUES (12, '叶圣陶童话故事集', '叶圣陶', '9叶圣陶童话故事集.jpg', 10, '《叶圣陶童话故事集》是叶圣陶先生的经典之作，由叶至善精心编纂。书中收录了一系列富有教育意义的童话故事，通过生动的故事情节和鲜明的人物形象，引导孩子们树立正确的价值观和道德观，是一部既具娱乐性又具启发性的儿童文学佳作。', '银川:,宁夏人民出版社:,1998', 0, '童话 选集 中国 现代', 2, '2024-03-05 11:34:07');
INSERT INTO `book` VALUES (13, '中国童话故事', '唐麟', '10中国童话故事.jpg', 10, '《中国童话故事》是唐麟编著的一部精选中国传统童话故事集。这本书汇集了众多脍炙人口的经典故事，如《白蛇传》、《牛郎织女》等，通过生动的故事情节和丰富的文化内涵，让孩子们更好地了解中国的传统文化和价值观，培养他们的想象力和阅读兴趣', '长春:,时代文艺出版社:,2004', 0, '百科全书 中国', 2, '2024-03-05 11:34:41');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `book_id` int NULL DEFAULT NULL COMMENT '图书编号',
  `num` int NULL DEFAULT NULL COMMENT '借阅(归还)数量',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '类型(1:借阅，2:归还)',
  `user_id` int NULL DEFAULT NULL COMMENT '借阅人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '借阅时间',
  `status` int NULL DEFAULT NULL COMMENT '借阅状态(1:未归还，2:已归还，3逾期归还)',
  `disparity` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '借阅(归还)表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (2, 1, 1, '1', 3, '2024-03-04 10:40:05', 2, NULL);
INSERT INTO `borrow` VALUES (3, 1, 1, '2', 3, '2024-03-07 15:10:32', 2, NULL);
INSERT INTO `borrow` VALUES (4, 1, 1, '1', 3, '2024-03-08 20:54:45', 2, NULL);
INSERT INTO `borrow` VALUES (5, 1, 1, '2', 3, '2024-03-08 21:06:13', 2, NULL);
INSERT INTO `borrow` VALUES (6, 2, 1, '1', 3, '2024-03-09 11:44:30', 2, NULL);
INSERT INTO `borrow` VALUES (7, 4, 1, '1', 3, '2024-03-09 11:45:00', 2, NULL);
INSERT INTO `borrow` VALUES (8, 4, 1, '2', 3, '2024-03-09 11:52:58', 2, NULL);
INSERT INTO `borrow` VALUES (9, 5, 1, '1', 3, '2024-03-09 11:53:34', 2, NULL);
INSERT INTO `borrow` VALUES (10, 5, 1, '2', 3, '2024-03-09 11:53:39', 2, NULL);
INSERT INTO `borrow` VALUES (12, 9, 1, '1', 3, '2024-02-09 11:55:07', 3, -38);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '看来你', '2024-03-05 14:59:48', 3);
INSERT INTO `comment` VALUES (2, '111', '2024-03-07 17:17:52', 3);
INSERT INTO `comment` VALUES (3, '312', '2024-03-07 17:21:18', 3);
INSERT INTO `comment` VALUES (6, '开飞机的考虑是否洪都拉斯开放端口立法会是大海多久啊圣诞节爱上了凯迪拉克撒谎的机阿里山客户打算离开军队拉萨空间的是德拉斯卡洪都拉斯空间的', '2024-03-12 14:39:56', 3);

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员推荐表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES (2, '中国知网', 'https://www.cnki.net/', '2024-03-09 11:37:10');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '小说散文集');
INSERT INTO `tag` VALUES (2, '儿童启蒙类');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reg_time` datetime NULL DEFAULT NULL,
  `status` smallint NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '男', '123456789', '1244436021@qq.com', NULL, 1, 1);
INSERT INTO `user` VALUES (3, 'user1', '123', '男', '18325693126', '15612165@163.com', NULL, 0, 2);
INSERT INTO `user` VALUES (6, 'user3', '123', NULL, NULL, NULL, '2024-03-13 09:42:53', 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
