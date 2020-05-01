SET NAMES utf8mb4 ;

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
`user_id` int(11) NOT NULL AUTO_INCREMENT,
`user_name` varchar(225) NOT NULL,
`image_path` varchar(225),
`is_delete` tinyint(1) NOT NULL DEFAULT '1',
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
PRIMARY KEY (`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `auth` (
`auth_id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` int(11) NOT NULL,
`login_id` varchar(225) NOT NULL,
`password` varchar(223) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `friend_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `friend_status` (
`friend_status_id` int(11) NOT NULL AUTO_INCREMENT,
`follow_user_id` int(11) NOT NULL,
`follower_user_id` int(11) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (`follow_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
FOREIGN KEY (`follower_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
PRIMARY KEY (`friend_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `grandparent_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `grandparent_category` (
`grandparent_category_id` int(11) NOT NULL AUTO_INCREMENT,
`grandparent_category_name` varchar(225) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
PRIMARY KEY (`grandparent_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `parent_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `parent_category` (
`parent_category_id` int(11) NOT NULL AUTO_INCREMENT,
`grandparent_category_id` int(11) NOT NULL,
`parent_category_name` varchar(225) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (`grandparent_category_id`) REFERENCES `grandparent_category` (`grandparent_category_id`) ON DELETE CASCADE,
PRIMARY KEY (`parent_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
`category_id` int(11) NOT NULL AUTO_INCREMENT,
`parent_category_id` int(11) NOT NULL,
`category_name` varchar(225) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (`parent_category_id`) REFERENCES `parent_category` (`parent_category_id`) ON DELETE CASCADE,
PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `image` (
`image_id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` int(11) NOT NULL,
`image_path` varchar(225) unique NOT NULL,
`explanation` varchar(1000) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `tag` (
`tag_id` int(11) NOT NULL AUTO_INCREMENT,
`tag` varchar(225) unique  NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `image_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `image_tag` (
`image_tag_id` int(11) NOT NULL AUTO_INCREMENT,
`image_id` int(11) NOT NULL,
`tag_id` int(11) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`) ON DELETE CASCADE,
FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE,
PRIMARY KEY (`image_tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
`comment_id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` int(11) NOT NULL,
`image_id` int(11) NOT NULL,
`comment` varchar(1000) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`) ON DELETE CASCADE,
PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `favorite_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
SET character_set_client = utf8mb4 ;
CREATE TABLE `favorite_history` (
`favorite_history_id` int(11) NOT NULL AUTO_INCREMENT,
`image_id` int(11) NOT NULL,
`user_id` int(11) NOT NULL,
`create_at` timestamp  default current_timestamp,
`update_at` timestamp default current_timestamp on update current_timestamp,
FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`) ON DELETE CASCADE,
FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
PRIMARY KEY (`favorite_history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

