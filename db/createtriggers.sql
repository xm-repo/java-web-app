-- -----------------------------------------------------
-- Trigger `PropertyStructure`.`OnLegalEntityUpdate`
-- -----------------------------------------------------	

DELIMITER $$
DROP TRIGGER IF EXISTS `PropertyStructure`.`OnLegalEntityUpdate`$$

CREATE TRIGGER `PropertyStructure`.`OnLegalEntityUpdate` BEFORE UPDATE ON `PropertyStructure`.`LegalEntity`
FOR EACH ROW
BEGIN
	IF (NEW.BusinessName != OLD.BusinessName) THEN
	    INSERT INTO `PropertyStructure`.`History` (`ChangeDate`, `OldBusinessName`, `Miscellaneous`, `LeId`) VALUES
	        (CURRENT_DATE(), OLD.BusinessName, "BusinessName changed", NEW.id);
	END IF;
END
$$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger `PropertyStructure`.`OnOwnersUpdate`
-- -----------------------------------------------------	

DELIMITER $$
DROP TRIGGER IF EXISTS `PropertyStructure`.`OnOwnersUpdate`$$

CREATE TRIGGER `PropertyStructure`.`OnOwnersUpdate` BEFORE INSERT ON `PropertyStructure`.`Owner`
FOR EACH ROW
BEGIN
	IF (NEW.LeOwnerId AND (NEW.Property = 100)) THEN
	    INSERT INTO `PropertyStructure`.`Business` (`SubjectLeId`, `ObjectLeId`, `PurchaseDate`, `Miscellaneous`) VALUES
	        (NEW.LeOwnerId, NEW.LeId, CURRENT_DATE(), "");
	    UPDATE `PropertyStructure`.`LegalEntity` SET `LegalEntity`.`IsLe` = 0 WHERE `LegalEntity`.`id` = NEW.LeId;
	END IF;
END
$$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger `PropertyStructure`.`OnOwnerUpdate`
-- -----------------------------------------------------	

DELIMITER $$
DROP TRIGGER IF EXISTS `PropertyStructure`.`OnOwnerUpdate`$$

CREATE TRIGGER `PropertyStructure`.`OnOwnerUpdate` BEFORE UPDATE ON `PropertyStructure`.`Owner`
FOR EACH ROW
BEGIN
	IF ((SELECT SUM(`PropertyStructure`.`Owner`.`Property`) FROM `PropertyStructure`.`Owner` WHERE `PropertyStructure`.`Owner`.`LeId` = NEW.LeId) + NEW.Property > 100) THEN
	    SET NEW.Property = OLD.property;                
	END IF;
END
$$
DELIMITER ;

-- -----------------------------------------------------
-- Trigger `PropertyStructure`.`OnOwnerInsert`
-- -----------------------------------------------------	

DELIMITER $$
DROP TRIGGER IF EXISTS `PropertyStructure`.`OnOwnerInsert`$$

CREATE TRIGGER `PropertyStructure`.`OnOwnerInsert` BEFORE INSERT ON `PropertyStructure`.`Owner`
FOR EACH ROW
BEGIN
	IF ((SELECT SUM(`PropertyStructure`.`Owner`.`Property`) FROM `PropertyStructure`.`Owner` WHERE `PropertyStructure`.`Owner`.`LeId` = NEW.LeId) + NEW.Property > 100) THEN
	    SET NEW.Property = -1;                
	END IF;
END
$$
DELIMITER ;