use study;
ALTER TABLE `study`.`custom_field_display` 
  ADD CONSTRAINT `FK_CUSTOM_FIELD_GROUP_CUSTOM_FIELD_ID`
  FOREIGN KEY (`CUSTOM_FIELD_ID` )
  REFERENCES `study`.`custom_field` (`ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `FK_CUSTOM_FIELD_GROUP_CUSTOM_FIELD_ID` (`CUSTOM_FIELD_ID` ASC) ;


