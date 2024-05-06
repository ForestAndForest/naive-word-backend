-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: naive_word
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `user_uid` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time` mediumtext,
  `fa_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_notes_id_fk` (`article_id`),
  KEY `comment_comment_id_fk` (`fa_id`),
  KEY `comment_user_uid_fk` (`user_uid`),
  CONSTRAINT `comment_comment_id_fk` FOREIGN KEY (`fa_id`) REFERENCES `comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_notes_id_fk` FOREIGN KEY (`article_id`) REFERENCES `notes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_user_uid_fk` FOREIGN KEY (`user_uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (20,1,'29257c48-6133-476d-b88e-a48fbbd660b6','Docker 容器管理很方便，但是我在使用 `docker exec` 命令时遇到了一些权限问题，有没有人遇到过类似的情况？','1712665184514',NULL),(21,1,'b4e6a4e1-4c20-469d-b4e3-09033e88510a','是的，我之前也遇到过类似的问题。你可以尝试给 `docker exec` 添加 `-u` 参数来指定执行用户的权限。','1712665184514',20),(22,1,'d22b5a01-c9be-4f32-93d5-27502b9d4733','Docker 镜像的构建过程中，有哪些常见的最佳实践需要注意？','1712665184514',NULL),(23,1,'bd25ad18-fd92-4628-af51-97b09da352f9','关于 Docker 镜像构建，我建议先使用多阶段构建，避免镜像过大和减少安全风险。','1712665184514',22),(24,1,'29257c48-6133-476d-b88e-a48fbbd660b6','Docker 中如何进行持久化存储？','1712665184514',NULL),(25,1,'b4e6a4e1-4c20-469d-b4e3-09033e88510a','在 Docker 中可以使用数据卷来实现持久化存储，也可以考虑使用外部存储插件。','1712665184514',24),(26,1,'d22b5a01-c9be-4f32-93d5-27502b9d4733','我想在 Docker 中部署一个 Redis 集群，有没有成熟的方案或工具？','1712665184514',NULL),(27,1,'bd25ad18-fd92-4628-af51-97b09da352f9','可以考虑使用 Docker Compose 来编排 Redis 集群，或者使用专门的 Redis 集群管理工具。','1712665184514',26),(28,1,'29257c48-6133-476d-b88e-a48fbbd660b6','Docker 容器管理很方便，但是我在使用 `docker exec` 命令时遇到了一些权限问题，有没有人遇到过类似的情况？','1712665184514',NULL),(29,1,'b4e6a4e1-4c20-469d-b4e3-09033e88510a','是的，我之前也遇到过类似的问题。你可以尝试给 `docker exec` 添加 `-u` 参数来指定执行用户的权限。','1712665184514',28),(30,1,'d22b5a01-c9be-4f32-93d5-27502b9d4733','Docker 镜像的构建过程中，有哪些常见的最佳实践需要注意？','1712665184514',NULL),(31,1,'bd25ad18-fd92-4628-af51-97b09da352f9','关于 Docker 镜像构建，我建议先使用多阶段构建，避免镜像过大和减少安全风险。','1712665184514',30),(32,1,'29257c48-6133-476d-b88e-a48fbbd660b6','Docker 中如何进行持久化存储？','1712665184514',NULL),(33,1,'b4e6a4e1-4c20-469d-b4e3-09033e88510a','在 Docker 中可以使用数据卷来实现持久化存储，也可以考虑使用外部存储插件。','1712665184514',32),(34,1,'d22b5a01-c9be-4f32-93d5-27502b9d4733','我想在 Docker 中部署一个 Redis 集群，有没有成熟的方案或工具？','1712665184514',NULL),(35,1,'bd25ad18-fd92-4628-af51-97b09da352f9','可以考虑使用 Docker Compose 来编排 Redis 集群，或者使用专门的 Redis 集群管理工具。','1712665184514',34),(36,1,'29257c48-6133-476d-b88e-a48fbbd660b6','Docker Desktop 是否支持 Linux 容器？','1712665184514',NULL),(37,1,'b4e6a4e1-4c20-469d-b4e3-09033e88510a','Docker 中如何监控容器的运行状态？有没有推荐的监控工具？','1712665184514',36),(38,1,'d22b5a01-c9be-4f32-93d5-27502b9d4733','Docker 如何进行版本管理？','1712665184514',NULL),(39,1,'bd25ad18-fd92-4628-af51-97b09da352f9','如何使用 Docker 构建一个简单的 Web 应用？','1712665184514',38),(79,1,'29257c48-6133-476d-b88e-a48fbbd660b6','Docker 容器管理很方便，但是我在使用 `docker exec` 命令时遇到了一些权限问题，有没有人遇到过类似的情况？','1712665184514',NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menus` (
  `id` bigint NOT NULL,
  `label` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `menus_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `menus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (1,'前端',NULL,NULL),(2,'前端+后端',NULL,NULL),(3,'Java小游戏',NULL,NULL),(4,'Html+css',1,'group'),(5,'Html+css+原生js',1,'group'),(6,'JQuery',1,'group'),(7,'Vue2',1,'group'),(8,'Vue3',1,'group'),(9,'Vue2+SpringBoot',2,'group'),(10,'Vue3+SpringBoot',2,'group'),(11,'Jsp+Php',2,'group'),(12,'2048小游戏',3,NULL),(13,'蛋糕商城',4,NULL),(14,'吉瑞外卖',5,NULL);
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `directory` json DEFAULT NULL,
  `like` int DEFAULT '0',
  `read` int DEFAULT '0',
  `author_uid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `summary` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` mediumtext COLLATE utf8mb4_unicode_ci,
  `update_time` mediumtext COLLATE utf8mb4_unicode_ci,
  `favorites` int DEFAULT '0',
  `cover` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` VALUES (1,'介绍 Docker','\n> 来自Naive Word\n\n## 什么是 Docker？\n\nDocker 是一个用于开发、交付和运行应用程序的开放平台。它允许您将应用程序与基础架构分开，从而可以快速交付软件。使用 Docker，您可以以与管理应用程序相同的方式管理基础架构。\n\n## 关键概念\n\n- **容器：** 一个轻量级、独立的、可执行的软件包，包含运行软件所需的所有内容，包括代码、运行时、库和依赖项。\n- **镜像：** 一个只读模板，包含用于创建容器的一组指令。它是容器的蓝图。\n- **Dockerfile：** 一个文本文件，包含用于构建 Docker 镜像的指令。它定义了容器内部的环境。\n- **注册表：** 用于存储和分发 Docker 镜像的存储和分发系统。Docker Hub 是一个公共注册表，您可以在其中找到和共享 Docker 镜像。\n\n## 入门指南\n\n要开始使用 Docker，您需要在计算机上安装 Docker Desktop 应用程序。安装完成后，您可以使用 Docker CLI 构建、运行和管理容器。\n\n## Docker 的优势\n\n- 快速部署：Docker 容器可以快速轻松地部署。\n- 一致的环境：Docker 确保您的应用程序在任何环境中都以相同的方式运行。\n- 隔离：Docker 容器相互隔离，提供了运行应用程序的安全环境。\n- 资源利用率：Docker 容器共享主机系统的内核和资源，从而实现更好的资源利用率。\n\n## 示例命令\n\n```bash\ndocker run -it ubuntu:latest /bin/bash\n','[\"什么是 Docker？\", \"关键概念\", \"入门指南\", \"Docker 的优势\", \"示例命令\"]',23,100,'29257c48-6133-476d-b88e-a48fbbd660b6','什么是 Docker？\nDocker 是一个用于开发、交付和运行应用程序的开放平台。它允许您将应用程序与基础架构分开，从而可以快速交付软件。使用 Docker，您可以以与管理应用程序相同的方式管理基础架构。','1712223838000','1712223838000',11,'http://127.0.0.1:8080/img/9f521533-d832-4f25-9ca3-aba1c6f298a3.png'),(37,'Naive Word开发日记','## 24-03-31\n### 创建Vite+ts项目,使用依赖如下 \n```json\n//package.json\n{\n  \"name\": \"miya-vue\",\n  \"private\": true,\n  \"version\": \"0.0.0\",\n  \"type\": \"module\",\n  \"scripts\": {\n    \"dev\": \"vite --host\",\n    \"build\": \"vue-tsc && vite build\",\n    \"preview\": \"vite preview\"\n  },\n  \"dependencies\": {\n    \"axios\": \"^1.6.8\",\n    \"crypto-js\": \"^4.2.0\",\n    \"md-editor-v3\": \"^4.12.3\",\n    \"naive-ui\": \"^2.38.1\",\n    \"pinia\": \"^2.1.7\",\n    \"pinia-plugin-persistedstate\": \"^3.2.1\",\n    \"vfonts\": \"^0.0.3\",\n    \"vue\": \"^3.4.21\",\n    \"vue-axios\": \"^3.5.2\",\n    \"vue-cropper\": \"^0.6.4\",\n    \"vue-i18n\": \"^9.9.0\",\n    \"vue-router\": \"^4.3.0\"\n  },\n  \"devDependencies\": {\n    \"@types/crypto-js\": \"^4.2.2\",\n    \"@types/prismjs\": \"^1.26.3\",\n    \"@vicons/antd\": \"^0.12.0\",\n    \"@vicons/carbon\": \"^0.12.0\",\n    \"@vicons/fa\": \"^0.12.0\",\n    \"@vicons/fluent\": \"^0.12.0\",\n    \"@vicons/ionicons4\": \"^0.12.0\",\n    \"@vicons/ionicons5\": \"^0.12.0\",\n    \"@vicons/material\": \"^0.12.0\",\n    \"@vicons/tabler\": \"^0.12.0\",\n    \"@vitejs/plugin-vue\": \"^5.0.4\",\n    \"typescript\": \"^5.2.2\",\n    \"vite\": \"^5.2.0\",\n    \"vue-tsc\": \"^2.0.6\"\n  }\n}\n```\n### 创建SpringBoot+JPA项目\n\n## 24-04-01\n### md-editor-v3\n使用em-editor-v3构建编辑与展示文章页面\n### 后端略\n\n## 24-04-02 -- 24-04-08\n略(忘了)\n\n## 24-04-09\n完成文章上传图片和上传裁剪封面\n> 后端上传图片代码\n```java\n    public String uploadImage(MultipartFile multipartFile, HttpServletRequest request) throws IOException {\n\n        File dir = new File(uploadPath);\n        if (!dir.exists()) {\n            dir.mkdirs();\n        }\n        String originalFileName = Objects.requireNonNull(multipartFile.getOriginalFilename()).split(\"\\\\.\")[1];\n        String newFileName = UUID.randomUUID() + \".\" + originalFileName;\n\n        File newFile = new File(uploadPath + \"/\" + newFileName);\n\n        multipartFile.transferTo(newFile);\n        return request.getScheme() + \"://\" + request.getServerName() + \":\" + request.getServerPort() + \"/img/\" + newFileName;\n    }\n```\n>获取图片代码\n```java\n    public Object loadImage(String fileName, HttpServletRequest request) throws IOException {\n        String suffix = fileName.split(\"\\\\.\")[1];\n        File file = new File(uploadPath + \"/\" + fileName);\n        HttpHeaders headers = new HttpHeaders();\n        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));\n        headers.setContentType(getMediaType(suffix));\n        return new ResponseEntity<>(resource, headers, 200);\n    }\n\n    private MediaType getMediaType(String suffix) {\n        return switch (suffix) {\n            case \"jpg\", \"jpeg\" -> MediaType.IMAGE_JPEG;\n            case \"png\" -> MediaType.IMAGE_PNG;\n            case \"gif\" -> MediaType.IMAGE_GIF;\n            default -> MediaType.APPLICATION_OCTET_STREAM;\n        };\n    }\n```','[\"24-03-31\", \"创建Vite+ts项目,使用依赖如下\", \"创建SpringBoot+JPA项目\", \"24-04-01\", \"md-editor-v3\", \"后端略\", \"24-04-02 -- 24-04-08\", \"24-04-09\"]',0,0,'29257c48-6133-476d-b88e-a48fbbd660b6','博客开发日记,使用了Vue3+Ts,SpringBoot3+JPA构建,开始于2024年3月31日,预计2024年4月底完成[用户模块],[文章模块],[首页模块]','1712593610659','1712593610659',9999,'http://127.0.0.1:8080/img/fefc2a34-af6d-433b-90a5-dbc55ceeecf1.png'),(38,'火星探测器','','[]',0,0,'29257c48-6133-476d-b88e-a48fbbd660b6','','1712666443909','1712666443909',0,'noCover'),(39,'12','','[]',0,0,'29257c48-6133-476d-b88e-a48fbbd660b6','','1712710363343','1712710363343',0,'http://127.0.0.1:8080/img/defaultCover.jpg');
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_enum`
--

DROP TABLE IF EXISTS `tag_enum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_enum` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_enum`
--

LOCK TABLES `tag_enum` WRITE;
/*!40000 ALTER TABLE `tag_enum` DISABLE KEYS */;
INSERT INTO `tag_enum` VALUES (0,'测试'),(1,'前端'),(2,'后端'),(3,'Vue'),(4,'Vue3'),(5,'Html'),(6,'Css'),(7,'JavaScript'),(8,'Java'),(9,'Python'),(10,'Jquery'),(11,'Mysql'),(12,'Docker'),(13,'TypeScript'),(14,'SpringBoot'),(15,'Node');
/*!40000 ALTER TABLE `tag_enum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `note_id` int NOT NULL,
  `tag_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `note_type_list_notes_id_fk` (`note_id`),
  KEY `note_type_list_types_id_fk` (`tag_id`),
  CONSTRAINT `note_type_list_notes_id_fk` FOREIGN KEY (`note_id`) REFERENCES `notes` (`id`),
  CONSTRAINT `note_type_list_types_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `tag_enum` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,12,1),(1,1,2),(1,2,3);
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` json DEFAULT NULL,
  `create_time` mediumtext NOT NULL,
  `update_time` mediumtext NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '1',
  `uid` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT 'http://localhost:8080/img/ciu7.jpg',
  PRIMARY KEY (`id`),
  KEY `idx_user_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (20,'ciu72','1','d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35','null','1712665184514','1713239263949',1,'29257c48-6133-476d-b88e-a48fbbd660b6','http://127.0.0.1:8080/img/ae45ccc9-c9b0-455e-b717-3a9c7fc83024.png'),(22,'用户2','2','d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35','null','1713010134482','1713239320760',1,'b4e6a4e1-4c20-469d-b4e3-09033e88510a','http://localhost:8080/img/mm.jpg'),(23,'新用户d22b5a01','3','4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce','null','1713010780105','1713010780105',1,'d22b5a01-c9be-4f32-93d5-27502b9d4733',NULL),(24,'新用户bd25ad18','4','4b227777d4dd1fc61c6f884f48641d02b4d121d3fd328cb08b5531fcacdabf8a','null','1713010787217','1713010787217',1,'bd25ad18-fd92-4628-af51-97b09da352f9',NULL),(28,'admin','admin','admin',NULL,'1713010787217','1713010787217',0,'d22b5a01-c9be-4f32-93d5-27502b9d4734','http://localhost:8080/img/mm.jpg'),(100,'测试用户',NULL,'test',NULL,'1713322344324','1713322344324',0,'null',NULL),(102,'新用户09bb89b7','3212124156@qq.com','6df6988a22af10f2caf6edbf6da9a2a305907db79d67a2dd8ec77f2b23f8ca53','null','1714385212020','1714385212020',1,'09bb89b7-763e-4f01-b3c4-5c99cfb65a06',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-01 16:33:42
