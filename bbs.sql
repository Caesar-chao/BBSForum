-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2020-06-28 19:48:35
-- 服务器版本： 8.0.12
-- PHP 版本： 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `bbs`
--

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `nickname` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',
  `status` int(11) NOT NULL COMMENT '权限0/1',
  `tel` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '电话',
  `email` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `nickname`, `status`, `tel`, `email`) VALUES
(1, '111', '111', '111', 1, '1', '1');

-- --------------------------------------------------------

--
-- 表的结构 `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL COMMENT '主键id',
  `title` text COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `summary` text COLLATE utf8_unicode_ci NOT NULL COMMENT '简介',
  `content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '文章内容',
  `create_time` date NOT NULL COMMENT '发布时间',
  `goodNum` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `commentNum` int(11) DEFAULT '0' COMMENT '评论数',
  `isTop` int(11) NOT NULL DEFAULT '0' COMMENT '是否置顶 0/1',
  `grade` int(11) NOT NULL COMMENT '文章状态 0：普通，1：vip 2：svip专享',
  `refinement` int(11) NOT NULL DEFAULT '0' COMMENT '0：不加精 1：加精',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `category_id` int(11) NOT NULL COMMENT '类别id'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `article`
--

INSERT INTO `article` (`id`, `title`, `summary`, `content`, `create_time`, `goodNum`, `commentNum`, `isTop`, `grade`, `refinement`, `user_id`, `category_id`) VALUES
(1, '111111111111111111111', '22222222222222222222222222222', '', '2020-06-10', 0, 2, 1, 2, 0, 2, 1),
(2, '我好帅', '我真帅', '我真特么帅', '2020-06-02', 0, 0, 0, 2, 0, 3, 1),
(6, 'MyBatis 简介', 'MyBatis 是一款优秀的持久层框架', '## 1、简介\n\n### 1.1、什么是Mybatis\n\n- MyBatis 是一款优秀的**持久层框架**\n- 它支持定制化 SQL、存储过程以及高级映射。\n- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。\n- MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。\n- MyBatis 本是[apache](https://baike.baidu.com/item/apache/6265)的一个开源项目[iBatis](https://baike.baidu.com/item/iBatis), 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。\n- 2013年11月迁移到Github。\n\n\n\n如何获得Mybatis？\n\n- maven仓库：\n\n  ```xml\n  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->\n  <dependency>\n      <groupId>org.mybatis</groupId>\n      <artifactId>mybatis</artifactId>\n      <version>3.5.2</version>\n  </dependency>\n  ```\n\n- Github ： https://github.com/mybatis/mybatis-3/releases\n\n- 中文文档：https://mybatis.org/mybatis-3/zh/index.html\n\n\n\n### 1.2、持久化\n\n数据持久化\n\n- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程\n- 内存：**断电即失**\n- 数据库(Jdbc)，io文件持久化。\n- 生活：冷藏. 罐头。\n\n**为什么需要需要持久化？**\n\n- 有一些对象，不能让他丢掉。\n\n- 内存太贵了\n\n\n\n### 1.3、持久层\n\nDao层，Service层，Controller层….\n\n- 完成持久化工作的代码块\n- 层界限十分明显。\n\n\n\n### 1.4 为什么需要Mybatis？\n\n- 帮助程序猿将数据存入到数据库中。\n- 方便\n- 传统的JDBC代码太复杂了。简化。框架。自动化。\n- 不用Mybatis也可以。更容易上手。 **技术没有高低之分**\n- 优点：\n  - 简单易学\n  - 灵活\n  - sql和代码的分离，提高了可维护性。\n  - 提供映射标签，支持对象与数据库的orm字段关系映射\n  - 提供对象关系映射标签，支持对象关系组建维护\n  - 提供xml标签，支持编写动态sql。\n\n\n\n**最重要的一点：使用的人多！**\n\nSpring   SpringMVC    SpringBoot\n\n', '2020-06-26', 0, 1, 0, 0, 0, 1, 1),
(3, 'PHP', 'ThinkPHP6.X', '..................', '2020-06-26', 0, 1, 0, 0, 0, 1, 2),
(4, '西科东艾北卡南麦', '西科东艾北卡南麦', '东艾变相强\n西科得分狂\n南麦秀天赋\n北卡虐篮筐\n终是曼巴在天堂\n一技后仰一代皇\n终是答案不再晃\n亦是乔丹也难防\n终是卡特造辉煌\n挂臂滑翔当国王\n终是麦迪也疯狂\n三十五秒十三枪\n西科东艾北卡南麦 终了 ', '2020-06-25', 0, 1, 1, 0, 1, 1, 3);

-- --------------------------------------------------------

--
-- 表的结构 `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL COMMENT '主键 类别id',
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名',
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '描述'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `category`
--

INSERT INTO `category` (`id`, `name`, `description`) VALUES
(1, 'JAVA', '222'),
(2, 'PHP', 'PHP是世界上最好的语言'),
(3, '篮球', 'NBA');

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL COMMENT '主键 评论id',
  `user_id` int(11) NOT NULL COMMENT '评论者id',
  `article_id` int(11) NOT NULL COMMENT '被评论的文章id',
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '评论内容',
  `comment_time` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`id`, `user_id`, `article_id`, `comment_content`, `comment_time`) VALUES
(1, 2, 1, '斯人若彩虹遇上方知有', '2020-06-17'),
(2, 1, 3, 'NBA', '2020-06-24'),
(3, 1, 4, '北京时间6月25日，43岁老将文斯-卡特在参加某档节目时宣布退役，结束自己长达22年的NBA球员生涯。\n“我正式结束自己的篮球职业生涯。”', '2020-06-26'),
(6, 2, 4, '西科东艾北卡南麦', '2020-06-26'),
(7, 1, 3, '..........', '2020-06-26'),
(8, 2, 6, 'mybatis', '2020-06-28');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT '主键 id',
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `nickname` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',
  `signature` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'signature 个性签名',
  `sculpture` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
  `tel` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号',
  `email` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮箱',
  `integral` int(11) NOT NULL DEFAULT '0' COMMENT '积分数',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0：表示普通用户1：表示vip用户 2：表示svip用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `nickname`, `signature`, `sculpture`, `tel`, `email`, `integral`, `status`) VALUES
(1, 'root', '123456', 'root', '洛阳', '我真特么帅', '15970627134', '224@qq.com', 20, 2),
(2, 'yujian', '123456', '遇见', '西科东艾北卡南麦', NULL, '15720996591', '2248736974@qq.com', 0, 0),
(3, 'xukai', '000000', '徐', '个性签名', '头像', '15720996591', '2248736974@qq.com', 20, 1);

--
-- 转储表的索引
--

--
-- 表的索引 `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id', AUTO_INCREMENT=8;

--
-- 使用表AUTO_INCREMENT `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 类别id', AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 评论id', AUTO_INCREMENT=9;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 id', AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
