# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.21)
# Database: dashboard
# Generation Time: 2014-11-04 15:43:03 +0000
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
  `powerTime` datetime DEFAULT NULL,
  `power` double DEFAULT NULL,
  `saveC` double DEFAULT NULL,
  `saveCO2` double DEFAULT NULL,
  `income` double DEFAULT NULL,
  `saveIncome` double DEFAULT NULL,
  `restIncome` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `power_data_tbl` WRITE;
/*!40000 ALTER TABLE `power_data_tbl` DISABLE KEYS */;

INSERT INTO `power_data_tbl` (`id`, `powerTime`, `power`, `saveC`, `saveCO2`, `income`, `saveIncome`, `restIncome`)
VALUES
	(1,'2014-11-04 23:42:00',12,13,14,15,16,17);

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



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
