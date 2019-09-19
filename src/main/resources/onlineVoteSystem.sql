/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.62 : Database - db_votingsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_votingsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_votingsystem`;

/*Table structure for table `tab_applicationright` */

DROP TABLE IF EXISTS `tab_applicationright`;

CREATE TABLE `tab_applicationright` (
  `applicationId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `rights` int(11) DEFAULT NULL,
  PRIMARY KEY (`applicationId`),
  KEY `applicationRight_user` (`userId`),
  CONSTRAINT `applicationRight_user` FOREIGN KEY (`userId`) REFERENCES `tab_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tab_applicationright` */

/*Table structure for table `tab_item` */

DROP TABLE IF EXISTS `tab_item`;

CREATE TABLE `tab_item` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `subjectId` int(11) DEFAULT NULL,
  `optionId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`itemId`),
  KEY `item_subject` (`subjectId`),
  KEY `item_option` (`optionId`),
  KEY `item_user` (`userId`),
  CONSTRAINT `item_option` FOREIGN KEY (`optionId`) REFERENCES `tab_option` (`optionId`),
  CONSTRAINT `item_subject` FOREIGN KEY (`subjectId`) REFERENCES `tab_subject` (`subjectId`),
  CONSTRAINT `item_user` FOREIGN KEY (`userId`) REFERENCES `tab_user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `tab_item` */

insert  into `tab_item`(`itemId`,`subjectId`,`optionId`,`userId`,`time`) values (1,1,2,2,'2019-07-10 22:20:08'),(2,3,11,2,'2019-07-10 22:22:22'),(3,3,13,2,'2019-07-10 22:22:22'),(4,3,16,2,'2019-07-10 22:22:22'),(5,2,6,2,'2019-07-11 08:35:05'),(6,2,7,4,'2019-07-11 14:58:24'),(7,3,12,4,'2019-07-11 21:21:35'),(8,3,14,4,'2019-07-11 21:21:35'),(9,5,24,2,'2019-07-11 21:45:15'),(10,2,6,5,'2019-07-11 21:49:33'),(11,3,11,5,'2019-07-11 21:50:10'),(12,3,13,5,'2019-07-11 21:50:10'),(13,6,29,6,'2019-07-11 21:57:25'),(14,7,33,6,'2019-07-11 22:00:24'),(15,4,17,2,'2019-07-12 14:20:14'),(16,7,32,2,'2019-07-12 14:21:17'),(17,1,3,8,'2019-07-12 21:40:57'),(18,1,2,7,'2019-07-12 21:48:51'),(19,5,22,7,'2019-07-12 21:49:00'),(20,7,32,8,'2019-07-13 08:56:24'),(21,4,17,8,'2019-07-13 09:00:38'),(22,5,24,8,'2019-07-13 09:00:44'),(23,11,46,7,'2019-07-13 09:11:08');

/*Table structure for table `tab_option` */

DROP TABLE IF EXISTS `tab_option`;

CREATE TABLE `tab_option` (
  `optionId` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL,
  `orders` int(11) NOT NULL,
  `subjectId` int(11) NOT NULL,
  `counts` int(11) DEFAULT '0',
  PRIMARY KEY (`optionId`),
  KEY `option_subject` (`subjectId`),
  CONSTRAINT `option_subject` FOREIGN KEY (`subjectId`) REFERENCES `tab_subject` (`subjectId`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `tab_option` */

insert  into `tab_option`(`optionId`,`content`,`orders`,`subjectId`,`counts`) values (2,'全智贤',1,1,1),(3,'朴信惠',2,1,1),(4,'赵丽颖',3,1,0),(5,'Angelababy',4,1,0),(6,'胡歌',1,2,2),(7,'彭于晏',2,2,1),(8,'邓伦',3,2,0),(9,'刘德华',4,2,0),(10,'古天乐',5,2,0),(11,'JavaEE',1,3,2),(12,'Python',2,3,1),(13,'C/C++',3,3,2),(14,'数据结构和算法',4,3,1),(15,'高数',5,3,0),(16,'编译原理',6,3,1),(17,'华为',1,4,2),(18,'小米',2,4,0),(19,'苹果',3,4,0),(20,'vivo',4,4,0),(21,'155',1,5,0),(22,'158',2,5,1),(23,'160',3,5,0),(24,'162',4,5,2),(25,'165',5,5,0),(26,'168',6,5,0),(27,'170',7,5,0),(28,'汉语',1,6,0),(29,'英语',2,6,1),(30,'韩语',3,6,0),(31,'日语',4,6,0),(32,'可以',1,7,2),(33,'不可以',2,7,1),(34,'可以',1,8,0),(35,'不可以',2,8,0),(44,'干垃圾',1,11,0),(45,'湿垃圾',2,11,0),(46,'可回收垃圾',3,11,1),(47,'有害垃圾',4,11,0);

/*Table structure for table `tab_subject` */

DROP TABLE IF EXISTS `tab_subject`;

CREATE TABLE `tab_subject` (
  `subjectId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `discription` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`subjectId`),
  KEY `subject_user` (`userId`),
  CONSTRAINT `subject_user` FOREIGN KEY (`userId`) REFERENCES `tab_user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tab_subject` */

insert  into `tab_subject`(`subjectId`,`title`,`discription`,`type`,`createTime`,`endTime`,`userId`) values (1,'最喜欢的女星','',0,'2019-07-12 08:30:00','2019-07-18 12:12:00',1),(2,'最受欢迎的男演员','',0,'2019-07-10 09:30:00','2019-07-12 09:30:00',1),(3,'喜欢的课程','排名受欢迎的课程',1,'2019-07-10 16:50:00','2019-07-12 07:30:00',1),(4,'性价比高的手机品牌','生活',0,'2019-07-11 14:30:00','2019-07-13 14:30:00',4),(5,'最受欢迎的女生身高','娱乐',0,'2019-07-11 14:59:00','2019-07-14 12:12:00',4),(6,'最受欢迎的语言','',0,'2019-07-11 21:30:00','2019-07-12 12:12:00',6),(7,'空腹可不可以和牛奶','生活细节',0,'2019-07-11 21:59:00','2019-07-13 21:59:00',6),(8,'空腹可不可以吃苹果','生活常识',0,'2019-07-12 12:12:00','2019-07-16 12:12:00',2),(11,'玻璃属于什么垃圾','生活垃圾分类',0,'2019-07-13 09:00:00','2019-07-16 12:12:00',8);

/*Table structure for table `tab_user` */

DROP TABLE IF EXISTS `tab_user`;

CREATE TABLE `tab_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `version` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tab_user` */

insert  into `tab_user`(`userId`,`username`,`password`,`version`,`status`) values (1,'sun','123xyz',1,2),(2,'huge','xixiha',0,2),(3,'xiaohua','huahua',0,1),(4,'xiaoxiao','xixiha',0,2),(5,'baby','123456',0,1),(6,'huahua','huahua',0,2),(7,'linyichen','xixiha',0,1),(8,'xiaomunan','xixiha',0,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
