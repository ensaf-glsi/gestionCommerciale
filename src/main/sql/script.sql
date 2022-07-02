
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for produit
-- ----------------------------
DROP TABLE IF EXISTS `produit`;
CREATE TABLE `produit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `qte_stock` float DEFAULT NULL,
  `pu` decimal(10,2) DEFAULT NULL,
  `unit` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
);


-- ----------------------------
-- Table structure for produit
-- ----------------------------
DROP TABLE IF EXISTS `familles`;
CREATE TABLE `familles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
);

SET FOREIGN_KEY_CHECKS = 1;
