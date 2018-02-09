use study;
ALTER TABLE `study`.`ark_user_role` ADD COLUMN `ARK_USECASE_ID` INT NOT NULL  AFTER `ARK_MODULE_ID` , 
  ADD CONSTRAINT `FK_ARK_USECASE_ID`
  FOREIGN KEY (`ARK_USECASE_ID` )
  REFERENCES `study`.`ark_usecase` (`ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `FK_ARK_USECASE_ID` (`ARK_USECASE_ID` ASC) ;

