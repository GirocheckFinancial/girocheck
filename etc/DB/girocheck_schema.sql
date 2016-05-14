-- MySQL dump 10.13  Distrib 5.6.22, for Win64 (x86_64)
--
-- Host: localhost    Database: girocheck
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ach_card`
--

DROP TABLE IF EXISTS `ach_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ach_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_merchant` int(11) NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `ach_form` blob,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `card_number` (`card_number`) USING BTREE,
  KEY `id_merchant` (`id_merchant`) USING BTREE,
  CONSTRAINT `ach_card_fk1` FOREIGN KEY (`id_merchant`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=12743;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `country` int(10) DEFAULT NULL,
  `state` int(10) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKAddress687498` (`country`) USING BTREE,
  KEY `FKAddress927795` (`state`) USING BTREE,
  CONSTRAINT `FKAddress687498` FOREIGN KEY (`country`) REFERENCES `country` (`id`),
  CONSTRAINT `FKAddress927795` FOREIGN KEY (`state`) REFERENCES `state` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=550 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=188;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agrupation`
--

DROP TABLE IF EXISTS `agrupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agrupation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=4096;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agrupation_parameter`
--

DROP TABLE IF EXISTS `agrupation_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agrupation_parameter` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `read_only` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `data_type` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agrupation_parameter_value`
--

DROP TABLE IF EXISTS `agrupation_parameter_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agrupation_parameter_value` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `agrupation` int(10) NOT NULL,
  `parameter` int(10) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKAgrupation884085` (`agrupation`) USING BTREE,
  KEY `FKAgrupation494668` (`parameter`) USING BTREE,
  CONSTRAINT `FKAgrupation494668` FOREIGN KEY (`parameter`) REFERENCES `agrupation_parameter` (`id`),
  CONSTRAINT `FKAgrupation884085` FOREIGN KEY (`agrupation`) REFERENCES `agrupation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `application_parameter`
--

DROP TABLE IF EXISTS `application_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_parameter` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `data_type` int(10) DEFAULT NULL,
  `read_only` bit(1) DEFAULT NULL,
  `application` int(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1638;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `binnumb_host`
--

DROP TABLE IF EXISTS `binnumb_host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `binnumb_host` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bin_number` varchar(20) DEFAULT NULL,
  `host_name` varchar(255) DEFAULT NULL,
  `default` bit(1) DEFAULT NULL,
  `host_code` varchar(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `host_code` (`host_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `program` int(10) DEFAULT NULL,
  `data_s` varchar(255) DEFAULT NULL,
  `data_h` varchar(255) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `card_type` int(10) DEFAULT NULL,
  `client` int(10) DEFAULT NULL,
  `merchant` int(10) DEFAULT NULL,
  `card_status` int(10) DEFAULT NULL COMMENT '0 = UNNACTIVE, 1 = ACTIVE',
  `mask_card_number` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKCard341335` (`program`) USING BTREE,
  KEY `FKCard512092` (`card_type`) USING BTREE,
  KEY `FKCard506458` (`client`) USING BTREE,
  KEY `FKCard853492` (`merchant`) USING BTREE,
  CONSTRAINT `FKCard341335` FOREIGN KEY (`program`) REFERENCES `cardprogram` (`id`),
  CONSTRAINT `FKCard506458` FOREIGN KEY (`client`) REFERENCES `client` (`id`),
  CONSTRAINT `FKCard512092` FOREIGN KEY (`card_type`) REFERENCES `cardtype` (`id`),
  CONSTRAINT `FKCard853492` FOREIGN KEY (`merchant`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=553 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=348;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cardprogram`
--

DROP TABLE IF EXISTS `cardprogram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardprogram` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cardtype`
--

DROP TABLE IF EXISTS `cardtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cardtype` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `check`
--

DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `client` int(10) NOT NULL,
  `transaction` int(10) NOT NULL,
  `data_s` varchar(255) DEFAULT NULL,
  `check_back` longblob,
  `check_front` longblob,
  `micr` varchar(255) DEFAULT NULL,
  `paymentCheck` varchar(255) DEFAULT NULL,
  `crc` varchar(255) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `makerName` varchar(255) DEFAULT NULL,
  `makerAddress` varchar(255) DEFAULT NULL,
  `makerCity` varchar(255) DEFAULT NULL,
  `makerState` varchar(255) DEFAULT NULL,
  `makerZip` varchar(255) DEFAULT NULL,
  `makerPhone` varchar(255) DEFAULT NULL,
  `locationId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKCheck488081` (`client`) USING BTREE,
  KEY `FKCheck473175` (`transaction`) USING BTREE,
  CONSTRAINT `FKCheck473175` FOREIGN KEY (`transaction`) REFERENCES `transaction` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKCheck488081` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1723 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1431;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `address` int(10) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `address_form` longblob,
  `born_date` date DEFAULT NULL,
  `ssn` varchar(255) DEFAULT NULL,
  `id_beneficiary` varchar(255) DEFAULT NULL,
  `testing` int(11) DEFAULT NULL,
  `hash_ssn` varchar(255) DEFAULT NULL,
  `mask_ssn` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKClient395964` (`address`) USING BTREE,
  KEY `i_hash_ssn` (`hash_ssn`(4))
) ENGINE=InnoDB AUTO_INCREMENT=624 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=2588;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configs`
--

DROP TABLE IF EXISTS `configs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configs` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `mode` varchar(30) NOT NULL,
  `sessions` varchar(30) NOT NULL,
  `reference` varchar(50) NOT NULL,
  `types` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `abbreviation` varchar(3) DEFAULT NULL,
  `code` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `id_group` int(9) DEFAULT NULL,
  `vt_number` varchar(25) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  `activation_date` date DEFAULT NULL,
  `deactivation_date` date DEFAULT NULL,
  `date_established` date DEFAULT NULL,
  `GG_Create_by_DT` datetime DEFAULT NULL,
  `GG_Last_Update_By_DT` datetime DEFAULT NULL,
  `GG_Create_by` varchar(30) DEFAULT NULL,
  `GG_Last_Update_by` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id_group` (`id_group`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `id_terminal` int(9) DEFAULT NULL,
  `id_product_type` int(9) DEFAULT NULL,
  `serial_number` varchar(50) DEFAULT NULL,
  `last_transaction_id` varchar(60) DEFAULT NULL,
  `scpe` varchar(50) DEFAULT NULL,
  `akpe` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `prodType` int(9) DEFAULT NULL,
  `GG_Last_Update_by` varchar(30) DEFAULT NULL,
  `GG_Last_Update_By_DT` datetime DEFAULT NULL,
  `GG_Create_by` varchar(30) DEFAULT NULL,
  `GG_Create_by_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `serial_number` (`serial_number`) USING BTREE,
  KEY `id_terminal` (`id_terminal`) USING BTREE,
  KEY `id_product_type` (`id_product_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hsm`
--

DROP TABLE IF EXISTS `hsm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hsm` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `host` varchar(100) NOT NULL,
  `port` varchar(10) NOT NULL,
  `timeout` int(10) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `identification`
--

DROP TABLE IF EXISTS `identification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `identification` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `client` int(10) NOT NULL,
  `id_type` int(10) DEFAULT NULL,
  `state` int(10) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `id_front` longblob,
  `id_back` longblob,
  `data_s` varchar(255) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKIdentifica282023` (`client`) USING BTREE,
  KEY `FKIdentifica165542` (`state`) USING BTREE,
  CONSTRAINT `FKIdentifica165542` FOREIGN KEY (`state`) REFERENCES `state` (`id`),
  CONSTRAINT `FKIdentifica282023` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1765 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1997;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `address` int(10) DEFAULT NULL,
  `agrupation` int(10) DEFAULT NULL,
  `card_program` int(10) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `monitor` bit(1) DEFAULT NULL,
  `activation_date` date DEFAULT NULL,
  `deactivation_date` date DEFAULT NULL,
  `sic` varchar(255) DEFAULT NULL,
  `alive_session_time` int(10) DEFAULT NULL,
  `logo` blob,
  `phone` varchar(255) DEFAULT NULL,
  `agent_name` varchar(255) DEFAULT NULL,
  `distributor` int(10) DEFAULT NULL,
  `card_inventory` int(10) DEFAULT NULL,
  `training` bit(1) DEFAULT NULL,
  `document_approved` bit(1) DEFAULT NULL,
  `document_notes` varchar(255) DEFAULT NULL,
  `distribution_chanel` int(10) DEFAULT NULL,
  `merchant_type` int(10) DEFAULT NULL,
  `risk` int(10) DEFAULT NULL,
  `independent_owner` bit(1) DEFAULT NULL,
  `money_transmission` bit(1) DEFAULT NULL,
  `bill_payment` bit(1) DEFAULT NULL,
  `check_cashing` bit(1) DEFAULT NULL,
  `atm` bit(1) DEFAULT NULL,
  `other_financial_provider` bit(1) DEFAULT NULL,
  `data_sa` varchar(255) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `id_tecnicard_check` varchar(20) DEFAULT NULL,
  `id_tecnicard_cash` varchar(20) DEFAULT NULL,
  `id_istream_fuze_cash` varchar(20) DEFAULT NULL,
  `id_istream_fuze_check` varchar(20) DEFAULT NULL,
  `id_istream_tecnicard_cash` varchar(20) DEFAULT NULL,
  `id_istream_tecnicard_check` varchar(20) DEFAULT NULL,
  `id_teller_orderexp` varchar(20) DEFAULT NULL,
  `id_teller_pago_orderexp` varchar(255) DEFAULT NULL,
  `id_pos_orderexp` varchar(255) DEFAULT NULL,
  `istream_user` varchar(20) DEFAULT NULL,
  `istream_password` varchar(20) DEFAULT NULL,
  `routing_bank_number` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `auth_feep` double DEFAULT NULL,
  `oe_agent_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKMerchant945568` (`agrupation`) USING BTREE,
  KEY `FKMerchant6876` (`card_program`) USING BTREE,
  KEY `FKMerchant380836` (`address`) USING BTREE,
  CONSTRAINT `FKMerchant380836` FOREIGN KEY (`address`) REFERENCES `address` (`id`),
  CONSTRAINT `FKMerchant6876` FOREIGN KEY (`card_program`) REFERENCES `cardprogram` (`id`),
  CONSTRAINT `FKMerchant945568` FOREIGN KEY (`agrupation`) REFERENCES `agrupation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=8192;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `merchant_parameter`
--

DROP TABLE IF EXISTS `merchant_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant_parameter` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `read_only` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `data_type` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `merchant_parameter_value`
--

DROP TABLE IF EXISTS `merchant_parameter_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant_parameter_value` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parameter` int(10) NOT NULL,
  `merchant` int(10) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKMerchant_P343010` (`parameter`) USING BTREE,
  KEY `FKMerchant_P624997` (`merchant`) USING BTREE,
  CONSTRAINT `FKMerchant_P343010` FOREIGN KEY (`parameter`) REFERENCES `merchant_parameter` (`id`),
  CONSTRAINT `FKMerchant_P624997` FOREIGN KEY (`merchant`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privilege` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=744;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `report_filters`
--

DROP TABLE IF EXISTS `report_filters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_filters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_range_date` varchar(255) DEFAULT NULL,
  `end_range_date` varchar(255) DEFAULT NULL,
  `amount` varchar(20) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `entity_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1414 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1024;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=2730;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_privilege` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role` int(10) NOT NULL,
  `privilege` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKRole_Privi799729` (`role`) USING BTREE,
  KEY `FKRole_Privi302662` (`privilege`) USING BTREE,
  CONSTRAINT `FKRole_Privi302662` FOREIGN KEY (`privilege`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FKRole_Privi799729` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=409;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `abbreviation` varchar(3) DEFAULT NULL,
  `code` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=309;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subtransaction`
--

DROP TABLE IF EXISTS `subtransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subtransaction` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `transaction` int(10) NOT NULL,
  `type` int(10) DEFAULT NULL,
  `result_code` int(10) DEFAULT NULL,
  `result_message` varchar(255) DEFAULT NULL,
  `error_code` varchar(255) DEFAULT NULL,
  `host` int(10) DEFAULT NULL,
  `order` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKSubTransac644727` (`transaction`) USING BTREE,
  CONSTRAINT `FKSubTransac644727` FOREIGN KEY (`transaction`) REFERENCES `transaction` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13282 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=85;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `terminal`
--

DROP TABLE IF EXISTS `terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terminal` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `merchant` int(10) NOT NULL,
  `serial_number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `activation_date` date DEFAULT NULL,
  `deactivation_date` int(10) DEFAULT NULL,
  `girocheck_user` varchar(255) DEFAULT NULL,
  `girocheck_password` varchar(255) DEFAULT NULL,
  `id_pos_orderexp` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `serial_number` (`serial_number`) USING BTREE,
  KEY `FKTerminal552231` (`merchant`) USING BTREE,
  CONSTRAINT `FKTerminal552231` FOREIGN KEY (`merchant`) REFERENCES `merchant` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1365;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `terminal_parameter`
--

DROP TABLE IF EXISTS `terminal_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terminal_parameter` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `read_only` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `data_type` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `terminal_parameter_value`
--

DROP TABLE IF EXISTS `terminal_parameter_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `terminal_parameter_value` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `terminal` int(10) NOT NULL,
  `parameter` int(10) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKTerminal_P561573` (`parameter`) USING BTREE,
  KEY `FKTerminal_P620624` (`terminal`) USING BTREE,
  CONSTRAINT `FKTerminal_P561573` FOREIGN KEY (`parameter`) REFERENCES `terminal_parameter` (`id`),
  CONSTRAINT `FKTerminal_P620624` FOREIGN KEY (`terminal`) REFERENCES `terminal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `terminal` int(10) DEFAULT NULL,
  `client` int(10) DEFAULT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `result_code` int(10) DEFAULT NULL,
  `result_message` varchar(255) DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `creditcard` int(10) DEFAULT NULL,
  `transaction_type` int(10) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `data_sa` varchar(255) DEFAULT NULL,
  `request_id` varchar(255) NOT NULL,
  `istream_id` varchar(255) DEFAULT NULL,
  `single` bit(1) DEFAULT NULL,
  `errorCode` varchar(255) DEFAULT NULL,
  `order_express_id` varchar(255) DEFAULT NULL,
  `ammount` double DEFAULT NULL,
  `fee_ammount` double DEFAULT NULL,
  `payout_ammount` double DEFAULT NULL,
  `card_number` varchar(16) DEFAULT NULL,
  `truncated_check` blob,
  `ach_form` blob,
  `cancelated` bit(1) DEFAULT NULL,
  `cancelable` bit(1) DEFAULT NULL,
  `transaction_finished` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKTransactio266008` (`terminal`) USING BTREE,
  KEY `FKTransactio560640` (`client`) USING BTREE,
  KEY `FKTransactio628677` (`creditcard`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4973 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=345;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` int(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKUser708634` (`role`) USING BTREE,
  CONSTRAINT `FKUser708634` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1170;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  `GG_Last_Update_by` varchar(30) DEFAULT NULL,
  `GG_Last_Update_By_DT` datetime DEFAULT NULL,
  `GG_Create_by` varchar(30) DEFAULT NULL,
  `GG_Create_by_DT` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vtsession`
--

DROP TABLE IF EXISTS `vtsession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vtsession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `token` varchar(50) NOT NULL,
  `last_update` datetime DEFAULT '0000-00-00 00:00:00',
  `session_info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `id_user` (`id_user`) USING BTREE,
  CONSTRAINT `vtsession_fk1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=519 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=1260;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'girocheck'
--

--
-- Dumping routines for database 'girocheck'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-25  9:23:12
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              