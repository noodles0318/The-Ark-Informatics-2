USE `DISEASE`;
CREATE TABLE `DISEASE_CUSTOM_FIELDS` (
	`ID` int(11) NOT NULL AUTO_INCREMENT,
	`CUSTOM_FIELD_ID` int(11) NOT NULL,
	`DISEASE_ID` int(11) NOT NULL,
	PRIMARY KEY (`ID`),
	KEY `FK_CUSTOM_F_ID` (`CUSTOM_FIELD_ID`),
	KEY `FK_DISEASE_D_ID` (`DISEASE_ID`),
	CONSTRAINT `FK_CUSTOM_F_ID` FOREIGN KEY (`CUSTOM_FIELD_ID`) REFERENCES `STUDY`.`CUSTOM_FIELD` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `FK_DISEASE_D_ID` FOREIGN KEY (`DISEASE_ID`) REFERENCES `DISEASE` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB CHARSET=latin1;