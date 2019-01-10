CREATE DATABASE  IF NOT EXISTS `order_purchase` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `order_purchase`;
--
-- Database: order_purchase
-- 
/*In this case, MySQL Server parses and executes the code within the comment as it would any other SQL statement, but other SQL servers will ignore the extensions.*/

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

-- Table structure for 'customer`

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `customer_id` int(5) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `phone_num` varchar(16) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table 'customer'

LOCK TABLES `customer` WRITE;

INSERT INTO `customer` VALUES 
(1,'Mary','Public','253-606-4645','1136 Horizon Circle Tacoma, WA 98408','mary@yahoo.com'),
(2,'John','Doe','770-745-7493','3348 Pine Garden Lane Austell, GA 30001','john@gmail.com'),
(3,'Ajay','Rao','785-664-4467','2118 Dog Hill Lane Lenora, KS 67645','ajay@gmail.com'),
(4,'Bill','Neely','808-989-2908','434 Don Jackson Lane Honolulu, HI 96814','bill@gmail.com'),
(5,'Adam','Tiedeman','601-664-7500','380 Walnut Street Jackson, MS 39208','AdamWTiedeman@teleworm.us'),
(6,'Maxwell','Bryd','337-214-6993','2317 Sherwood Circle Lafayette, LA 70501','max@yahoo.com'),
(7,'Cecilia ','Dixon','574-285-6137','991 Norma Lane Saline, LA 71070','GeorgeAVandenberg@rhyta.com'),
(8,'Jerry','Croce','201-963-9916','2679 Locust View Drive San Francisco, CA 94115','AgnesRPape@dayrep.com'),
(9,'Mitchel','Adrian','413-289-7101','2329 Leverton Cove Road Palmer, MA 01069','MitchelPAdrian@gmail.com'),
(10,'Madeline','Baca','413-225-0718','168 Frank Avenue Springfield, MA 01103','MadelineMBaca@armyspy.com'),
(11,'George','Sorrell','850-414-9367','995 Star Trek Drive Tallahassee, FL 32301','GeorgeCSorrell@teleworm.us'),
(12,'Latoya','Propes','803-363-7701','2001 Wexford Way Augusta, SC 30901','LatoyaRPropes@rhyta.com'),
(13,'Kendall','Sykes','330-901-5998','13 Board st, OH 44221','Kendall@gmail.com'),
(14,'Iris','Phan','803-363-7733','2001 Wexford Way Augusta, SC 30901','LatoyaRPropes@rhyta.com'),
(15,'Christopher','Beland','419-879-6222','3176 Upland Avenue Lima, OH 45801','ChristopherJBeland@rhyta.com');

UNLOCK TABLES;

-- Dump into table 'customer' completed 

-- Table structure for'productCategory`

DROP TABLE IF EXISTS `productCategory`;

CREATE TABLE `productCategory` (
  `category_id` int(10) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`category_id`)

) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table 'productCategory'

LOCK TABLES `productCategory` WRITE;

INSERT INTO `productCategory` VALUES 
(1,'Appliances'),
(2,'Baby'),
(3,'Bed & Bath'),
(4,'Beauty & Personal Care'),
(5,'Electronics'),
(6,'Garden'),
(7,'Home Decor'),
(8,'Pet Supplies'),
(9,'Video Games'),
(10,'Toys');

UNLOCK TABLES;

-- Dump into table 'productCategory' completed 

-- Table structure for'product`

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `product_name` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `product_price` decimal(6,2) DEFAULT NULL,
  `category_id` int(10),
   CONSTRAINT FK_categoryID FOREIGN KEY (category_id)
    REFERENCES productCategory(category_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table 'product'

LOCK TABLES `product` WRITE;

INSERT INTO `product` VALUES 
(1,
'American Lawn Mower',
'Deluxe hand-reel mower ideal for most turf grasses. It provides a clean, precise, scissor-like cut.Rear grass deflection',
'49.99',
'6'),

(2,
'Single Door & Double Door Folding Metal Dog Crate',
'Two slide-bolt latches on each door for increased safety and security. Sturdy metal construction; folds flat for easy storage/portability',
'71.99',
'8'),

(3,
'Large Dirty Dog Doormat',
'Our mats have a GSM abosrption rate of 3000 that is higher than our competitors with a GSM from 900 to 1500',
'23.99',
'8'),

(4,'Stainless Steel Pet Id Tags',
'WITH 2 SIDED ENGRAVING & UP TO 8 LINES OF PERSONALIZED TEXT, there is plenty of room for all of your pet important information.',
'7.99',
'8'),

(5,'Samsung UN55NU8000FXZA Flat 55" 4K UHD 8 Series Smart LED TV (2018)',
'Brand: Samsung, Item Weight: 41.7 pounds, Product Dimensions 48.3 x 12.7 x 31 inches, Batteries	2 AAA batteries required.',
'847.99 ',
'5'),

(6,'Echo Dot (2nd Generation)',
'Echo Dot is a voice-controlled speaker that uses Alexa to play music, control smart home devices, make calls, answer questions, set timers and alarms, and more.',
'39.99 ',
'5'),

(7,'HP Envy 5055 Wireless All-in-One Photo Printer',
'BRAND NEW ALL IN ONE WIRELESS PRINTER UPGRADE FOR 2018 - Replaces the HP ENVY 4520. Now with Bluetooth Smart, improved Wi-Fi connectivity, faster print speeds & more',
'99.99',
'5'),

(8,'CYBERPOWERPC Gamer Xtreme VR GXiVR8060A5 Gaming PC',
'System: Intel Core i5-8400 2.8GHz 6-Core | Intel B360 Express Chipset | 8GB DDR4 | 120GB SSD | 1TB HDD | Genuine Windows 10 Home 64-bit',
'888.35',
'5'),

(9,'Multi-Use Programmable Pressure Cooker',
'Healthy, stainless steel (18/8) inner cooking pot made from food grade 304, no chemical coating, 3-ply bottom for even heat distribution, fully sealed environment traps the flavors, nutrients and aromas within the food',
'139.99',
'1'),

(10,'Cuisinart GR-4N 5-in-1 Griddler',
'5-in-1 countertop unit works as a contact grill, panini press, full grill, full griddle and half grill/half griddle. Dimensions: 13.50 L x 11.50 W x 7.12 H inches',
'187.00',
'1');

UNLOCK TABLES;
-- Dump into table 'product' completed 

