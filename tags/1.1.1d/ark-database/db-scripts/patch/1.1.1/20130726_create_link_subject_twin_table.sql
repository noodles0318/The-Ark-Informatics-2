CREATE  TABLE `study`.`link_subject_twin` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `FIRST_SUBJECT` INT NOT NULL ,
  `SECOND_SUBJECT` INT NOT NULL ,
  `RELATIONSHIP_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

