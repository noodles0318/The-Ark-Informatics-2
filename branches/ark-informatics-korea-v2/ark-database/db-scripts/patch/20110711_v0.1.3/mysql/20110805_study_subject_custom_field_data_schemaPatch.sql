use study;
ALTER TABLE `study`.`subject_custom_field_data` ADD COLUMN `DATE_DATA_VALUE` DATE NULL  AFTER `DATA_VALUE` ;

ALTER TABLE `study`.`subject_custom_field_data` CHANGE COLUMN `DATE_DATA_VALUE` `DATE_DATA_VALUE` TIMESTAMP NULL DEFAULT NULL  ;

ALTER TABLE `study`.`subject_custom_field_data` CHANGE COLUMN `DATE_DATA_VALUE` `DATE_DATA_VALUE` DATETIME NULL DEFAULT NULL  ;



