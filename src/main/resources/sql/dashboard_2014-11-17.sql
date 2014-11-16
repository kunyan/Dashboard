# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.21)
# Database: dashboard
# Generation Time: 2014-11-17 04:05:28 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table power_data_tbl
# ------------------------------------------------------------

DROP TABLE IF EXISTS `power_data_tbl`;

CREATE TABLE `power_data_tbl` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `createDateTime` datetime DEFAULT NULL,
  `power` double DEFAULT '0',
  `used` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `power_data_tbl` WRITE;
/*!40000 ALTER TABLE `power_data_tbl` DISABLE KEYS */;

INSERT INTO `power_data_tbl` (`id`, `createDateTime`, `power`, `used`)
VALUES
	(1,'2014-11-04 23:42:00',12,13),
	(2,'2014-10-09 14:31:00',15,13),
	(3,'2014-11-09 18:00:00',12,13),
	(4,'2014-11-09 19:00:00',12,13),
	(5,'2014-11-09 20:00:00',12,13),
	(6,'2014-11-09 21:00:00',12,13),
	(7,'2014-11-17 12:01:31',8,0),
	(8,'2014-11-17 12:01:32',8,0),
	(9,'2014-11-17 12:01:33',8,0),
	(10,'2014-11-17 12:01:34',8,0),
	(11,'2014-11-17 12:01:35',8,0),
	(12,'2014-11-17 12:01:36',8,0),
	(13,'2014-11-17 12:01:37',8,0),
	(14,'2014-11-17 12:01:38',8,0),
	(15,'2014-11-17 12:01:39',8,0),
	(16,'2014-11-17 12:01:40',8,0),
	(17,'2014-11-17 12:01:41',8,0);

/*!40000 ALTER TABLE `power_data_tbl` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table setting_power_tbl
# ------------------------------------------------------------

DROP TABLE IF EXISTS `setting_power_tbl`;

CREATE TABLE `setting_power_tbl` (
  `hour` int(11) unsigned NOT NULL,
  `sunHeight` int(11) DEFAULT NULL,
  `power` double DEFAULT NULL,
  PRIMARY KEY (`hour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `setting_power_tbl` WRITE;
/*!40000 ALTER TABLE `setting_power_tbl` DISABLE KEYS */;

INSERT INTO `setting_power_tbl` (`hour`, `sunHeight`, `power`)
VALUES
	(0,0,0),
	(1,0,0),
	(2,0,0),
	(3,0,0),
	(4,5,0.01),
	(5,68,0.12),
	(6,219,2.12),
	(7,440,4.21),
	(8,580,5.03),
	(9,696,6.05),
	(10,820,7.12),
	(11,893,7.75),
	(12,922,8),
	(13,915,7.95),
	(14,868,7.52),
	(15,774,6.72),
	(16,664,5.12),
	(17,312,3.12),
	(18,201,2.12),
	(19,90,0.12),
	(20,0,0),
	(21,0,0),
	(22,0,0),
	(23,0,0);

/*!40000 ALTER TABLE `setting_power_tbl` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table setting_weather_tbl
# ------------------------------------------------------------

DROP TABLE IF EXISTS `setting_weather_tbl`;

CREATE TABLE `setting_weather_tbl` (
  `id` varchar(2) NOT NULL DEFAULT '',
  `chinese` varchar(50) DEFAULT '',
  `rate` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `setting_weather_tbl` WRITE;
/*!40000 ALTER TABLE `setting_weather_tbl` DISABLE KEYS */;

INSERT INTO `setting_weather_tbl` (`id`, `chinese`, `rate`)
VALUES
	('00','晴',100),
	('01','多云',90),
	('02','阴',70),
	('03','阵雨',0),
	('04','雷阵雨',30),
	('05','雷阵雨伴有冰雹',0),
	('06','雨夹雪',0),
	('07','小雨',10),
	('08','中雨',10),
	('09','大雨',0),
	('10','暴雨',0),
	('11','大暴雨',0),
	('12','特大暴雨',0),
	('13','阵雪',50),
	('14','小雪',50),
	('15','中雪',50),
	('16','大雪',50),
	('17','暴雪',30),
	('18','雾',50),
	('19','冻雨',0),
	('20','沙尘暴',10),
	('21','小到中雨',0),
	('22','中到大雨',0),
	('23','大到暴雨',0),
	('24','暴雨到大暴雨',0),
	('25','大暴雨到特大暴雨',0),
	('26','小到中雪',50),
	('27','中到大雪',50),
	('28','大到暴雪',50),
	('29','浮尘',70),
	('30','扬沙',70),
	('31','强沙尘暴',30),
	('53','霾',70),
	('99','无',100);

/*!40000 ALTER TABLE `setting_weather_tbl` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
