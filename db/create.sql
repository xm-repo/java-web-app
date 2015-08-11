SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `PropertyStructure`;
CREATE SCHEMA IF NOT EXISTS `PropertyStructure` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `PropertyStructure`;

-- -----------------------------------------------------
-- Table `PropertyStructure`.`PhysicalPerson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PropertyStructure`.`Physicalperson`;

CREATE TABLE IF NOT EXISTS `PropertyStructure`.`PhysicalPerson` (

    `id`            INT           NOT NULL AUTO_INCREMENT COMMENT 'С„РёР·РёС‡РµСЃРєРёРµ Р»РёС†Р°',
    `FullName`      VARCHAR(300)  NOT NULL                COMMENT 'Р¤Р�Рћ',
    `Contacts`      VARCHAR(300)  NULL                    COMMENT 'РєРѕРЅС‚Р°РєС‚С‹ (Р°РґСЂРµСЃ/С‚РµР»РµС„РѕРЅ)',
    `Biography`     VARCHAR(1000) NULL                    COMMENT 'Р±РёРѕРіСЂР°С„РёСЏ',

    PRIMARY KEY (`id`)

) ENGINE = InnoDB, AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `PropertyStructure`.`LegalEntity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PropertyStructure`.`LegalEntity`;

CREATE TABLE IF NOT EXISTS `PropertyStructure`.`LegalEntity` (

    `id`                  INT           NOT NULL AUTO_INCREMENT COMMENT 'СЋСЂРёРґРёС‡РµСЃРєРёРµ Р»РёС†Р°',
    `BusinessName`        VARCHAR(300)  NOT NULL                COMMENT 'РЅР°Р·РІР°РЅРёРµ РєРѕРјРїР°РЅРёРё',
    `IsLe`                INTEGER       NOT NULL                COMMENT 'СЃС‚Р°С‚СѓСЃ СЋСЂРёРґРёС‡РµСЃРєРѕРіРѕ Р»РёС†Р°',
    `FoundationDate`      DATE          NOT NULL                COMMENT 'РґР°С‚Р° РѕСЃРЅРѕРІР°РЅРёСЏ',
    `Miscellaneous`       VARCHAR(1000) NULL                    COMMENT 'РґРѕРїРѕР»РЅРёС‚РµР»СЊРЅР°СЏ РёРЅС„РѕСЂРјР°С†РёСЏ',

    PRIMARY KEY (`id`)

) ENGINE = InnoDB, AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `PropertyStructure`.`Owner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PropertyStructure`.`Owner` ;

CREATE TABLE IF NOT EXISTS `PropertyStructure`.`Owner` (

    `id`                 INT NOT NULL AUTO_INCREMENT COMMENT 'РІР»Р°РґРµР»СЊС†С‹ Р°РєС†РёР№',
    `PhPersonOwnerId`    INT NULL                    COMMENT 'РІР»Р°РґРµР»РµС† - С„РёР·РёС‡РµСЃРєРѕРµ Р»РёС†Рѕ',
    `LeOwnerId`          INT NULL                    COMMENT 'РІР»Р°РґРµР»РµС† - СЋСЂРёРґРёС‡РµСЃРєРѕРµ Р»РёС†Рѕ',
    `LeId`               INT NOT NULL                COMMENT 'РєРѕРјРїР°РЅРёСЏ - СЃСѓР±СЉРµРєС‚',
    `Property`           INT NOT NULL                COMMENT 'С‡РµРј РІР»Р°РґРµРµС‚: РїСЂРѕС†РµРЅС‚ Р°РєС†РёР№',
    
    PRIMARY KEY (`id`),
    
    CONSTRAINT `FK_PHYSICALPERSONOWNER` FOREIGN KEY (`PhPersonOwnerId`)
        REFERENCES `PropertyStructure`.`PhysicalPerson` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT `FK_LEGALENTITYOWNER` FOREIGN KEY (`LeOwnerId`)
        REFERENCES `PropertyStructure`.`LegalEntity` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT `FK_LEGALENTITY` FOREIGN KEY (`LeId`)
        REFERENCES `PropertyStructure`.`LegalEntity` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT `CHK_OWNERS` CHECK 
        ((`PhPersonOwnerId` = NULL AND `LeOwnerId` != NULL) OR
		(`PhPersonOwnerId` != NULL AND `LeOwnerId` = NULL)),

    CONSTRAINT `CHK_PROPERTY` CHECK ((`Property` <= 100) AND (`Property` > 0))

) ENGINE = InnoDB, AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `PropertyStructure`.`History`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PropertyStructure`.`History` ;

CREATE TABLE IF NOT EXISTS `PropertyStructure`.`History` (

    `id`                INT           NOT NULL AUTO_INCREMENT  COMMENT 'РёСЃС‚РѕСЂРёСЏ СЃРјРµРЅС‹ РЅР°Р·РІР°РЅРёР№ РєРѕРјРїР°РЅРёРё',
    `ChangeDate`        DATE          NOT NULL                 COMMENT 'РґР°С‚Р°',
    `OldBusinessName`   VARCHAR(300)  NOT NULL                 COMMENT 'РЅРѕРІРѕРµ РЅР°Р·РІР°РЅРёРµ РєРѕРјРїР°РЅРёРё',
    `Miscellaneous`     VARCHAR(1000) NULL                     COMMENT 'РґРѕРїРѕР»РЅРёС‚РµР»СЊРЅР°СЏ РёРЅС„РѕСЂРјР°С†РёСЏ',
    `LeId`              INT           NOT NULL                 COMMENT 'РІ РєР°РєРѕР№ РєРѕРјРїР°РЅРёРё',

    PRIMARY KEY (`id`),
    
    CONSTRAINT `FK_HISTORY` FOREIGN KEY (`LeId`)
        REFERENCES `PropertyStructure`.`LegalEntity` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB, AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `PropertyStructure`.`Business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PropertyStructure`.`Business` ;

CREATE TABLE IF NOT EXISTS `PropertyStructure`.`Business` (

    `id`                INT           NOT NULL AUTO_INCREMENT COMMENT 'РёСЃС‚РѕСЂРёСЏ РїРѕРіР»РѕС‰РµРЅРёР№',
    `SubjectLeId`       INT           NOT NULL                COMMENT 'СЃСѓР±СЉРµРєС‚ (РєС‚Рѕ)',
    `ObjectLeId`        INT           NOT NULL                COMMENT 'РѕР±СЉРµРєС‚ (РєРѕРіРѕ)',
    `PurchaseDate`      DATE          NOT NULL                COMMENT 'РґР°С‚Р°',
    `Miscellaneous`     VARCHAR(1000) NULL                    COMMENT 'РґРѕРїРѕР»РЅРёС‚РµР»СЊРЅР°СЏ РёРЅС„РѕСЂРјР°С†РёСЏ',
    
    PRIMARY KEY (`id`),
    
    CONSTRAINT `FK_SUBJECTLE` FOREIGN KEY (`SubjectLeId`)
        REFERENCES `PropertyStructure`.`LegalEntity` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    
    CONSTRAINT `FK_OBJECTLE` FOREIGN KEY (`ObjectLeId`)
        REFERENCES `PropertyStructure`.`LegalEntity` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT `CHK_OBJSUBJNOTEQ` CHECK (`SubjectLeId` != `ObjectLeId`)

) ENGINE = InnoDB, AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- View `PropertyStructure`.`PhPersonOwner`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `PropertyStructure`.`PhPersonOwner`;

CREATE VIEW `PropertyStructure`.`PhPersonOwner` AS 
    SELECT `id` AS `id`, `PhPersonOwnerId` AS `PhPersonOwnerId`, `LeId` AS `LeId`, `Property` AS `Property`
	FROM `PropertyStructure`.`Owner` WHERE `PhPersonOwnerId` IS NOT NULL;


-- -----------------------------------------------------
-- View `PropertyStructure`.`PhPersonLeOwner`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `PropertyStructure`.`LeOwner`;

CREATE VIEW `PropertyStructure`.`LeOwner` AS 
    SELECT `id` AS `id`, `LeOwnerId` AS `LeOwnerId`, `LeId` AS `LeId`, `Property` AS `Property`
	FROM `PropertyStructure`.`Owner` WHERE `LeOwnerId` IS NOT NULL;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
