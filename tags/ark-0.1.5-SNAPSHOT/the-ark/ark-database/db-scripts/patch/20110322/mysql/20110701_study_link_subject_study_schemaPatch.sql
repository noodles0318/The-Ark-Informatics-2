USE study;

ALTER TABLE `study`.`link_subject_study` ADD COLUMN `DATE_LAST_KNOWN_ALIVE` DATE NULL  AFTER `OTHER_STATE` , ADD COLUMN `HEARD_ABOUT_STUDY` VARCHAR(500) NULL  AFTER `DATE_LAST_KNOWN_ALIVE` , ADD COLUMN `COMMENTS` VARCHAR(1000) NULL  AFTER `HEARD_ABOUT_STUDY` , ADD COLUMN `CONSENT_DOWNLOADED` INT NULL  AFTER `COMMENTS` ;
