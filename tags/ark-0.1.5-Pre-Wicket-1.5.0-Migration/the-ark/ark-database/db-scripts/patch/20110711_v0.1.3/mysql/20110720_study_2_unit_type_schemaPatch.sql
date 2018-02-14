use study;
CREATE  TABLE `study`.`unit_type` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `NAME` VARCHAR(45) NOT NULL ,
  `DESCRIPTION` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC) )
ENGINE = InnoDB;

-- Make add an additional column FK to ArkModule (in case units will need to be separated per module)
ALTER TABLE `study`.`unit_type` ADD COLUMN `ARK_MODULE_ID` INT NULL DEFAULT NULL  AFTER `DESCRIPTION` 
, DROP INDEX `NAME_UNIQUE` 
, ADD UNIQUE INDEX `NAME_MODULE_UNIQUE` (`NAME` ASC, `ARK_MODULE_ID` ASC) ;

ALTER TABLE `study`.`unit_type` CHANGE COLUMN `ARK_MODULE_ID` `ARK_MODULE_ID` INT(11) NULL DEFAULT NULL  AFTER `ID` ;

ALTER TABLE `study`.`unit_type` 
  ADD CONSTRAINT `FK_UNIT_TYPE_ARK_MODULE_ID`
  FOREIGN KEY (`ARK_MODULE_ID` )
  REFERENCES `study`.`ark_module` (`ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `FK_UNIT_TYPE_ARK_MODULE_ID` (`ARK_MODULE_ID` ASC) ;