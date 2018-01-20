/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.20 : Database - hotel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hotel` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hotel`;

/*Table structure for table `dinnertable` */

DROP TABLE IF EXISTS `dinnertable`;

CREATE TABLE `dinnertable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tableName` varchar(20) DEFAULT NULL,
  `tableStatus` int(11) DEFAULT '0',
  `orderDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `dinnertable` */

insert  into `dinnertable`(`id`,`tableName`,`tableStatus`,`orderDate`) values (1,'纽约',1,'2017-12-27 17:30:05'),(2,'伦敦',1,'2017-12-27 17:48:28'),(3,'天上人间',0,NULL),(4,'空谷幽兰',0,NULL),(6,'新马泰',0,NULL),(7,'天上凤台',0,NULL);

/*Table structure for table `food` */

DROP TABLE IF EXISTS `food`;

CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foodName` varchar(20) DEFAULT NULL,
  `foodType_id` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `mprice` double DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_food_foodType_id` (`foodType_id`),
  CONSTRAINT `fk_food_foodType_id` FOREIGN KEY (`foodType_id`) REFERENCES `foodtype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `food` */

insert  into `food`(`id`,`foodName`,`foodType_id`,`price`,`mprice`,`remark`,`img`) values (17,'白斩鸡',1,45,35,'放假时间卡死了','3c47af68-ba57-4125-9937-8c032ac6a543_baizhuoxia.jpg'),(18,'麻辣豆腐',2,34,26,'电饭锅电饭锅是','58231776-5bee-4dfe-96d7-4b8c5b8efcb5_huotuibaicai.jpg'),(19,'干煸豆角',6,34,24,'帝国时代','b0fb854b-dc27-419d-a4bd-897478bad780_huotuibaicai.jpg'),(20,'黑斩鸡',3,45,35,'test data','no img'),(21,'黑斩鸡',3,45,35,'test data','no img'),(22,'黑斩鸡',3,45,35,'test data','no img'),(23,'黑斩鸡',3,45,35,'test data','no img'),(24,'黑斩鸡',3,45,35,'test data','no img'),(25,'黑斩鸡',3,45,35,'test data','no img'),(26,'黑斩鸡',3,45,35,'test data','no img'),(27,'黑斩鸡',3,45,35,'test data','no img'),(28,'黑斩鸡',3,45,35,'test data','no img'),(29,'黑斩鸡',3,45,35,'test data','no img'),(30,'黑斩鸡',3,45,35,'test data','no img'),(31,'黑斩鸡',3,45,35,'test data','no img'),(32,'黑斩鸡',3,45,35,'test data','no img'),(33,'黑斩鸡',3,45,35,'test data','no img'),(34,'黑斩鸡',3,45,35,'test data','no img'),(35,'黑斩鸡',3,45,35,'test data','no img');

/*Table structure for table `foodtype` */

DROP TABLE IF EXISTS `foodtype`;

CREATE TABLE `foodtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `foodtype` */

insert  into `foodtype`(`id`,`typeName`) values (1,'粤菜'),(2,'川菜'),(3,'湘菜'),(4,'东北菜'),(6,'陕西菜');

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `foodCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderDetail_order_id` (`orderId`),
  KEY `orderDetail_food_id` (`food_id`),
  CONSTRAINT `orderDetail_food_id` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`),
  CONSTRAINT `orderDetail_order_id` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderdetail` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_id` int(11) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  `orderStatus` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order_table_id` (`table_id`),
  CONSTRAINT `order_table_id` FOREIGN KEY (`table_id`) REFERENCES `dinnertable` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
