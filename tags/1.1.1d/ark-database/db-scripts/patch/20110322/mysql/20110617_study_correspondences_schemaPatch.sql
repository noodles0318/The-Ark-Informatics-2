use study;

ALTER TABLE `study`.`correspondences` ADD COLUMN `ATTACHMENT_FILENAME` VARCHAR(255) NULL DEFAULT NULL  AFTER `COMMENTS` , ADD COLUMN `ATTACHMENT_PAYLOAD` LONGBLOB NULL DEFAULT NULL  AFTER `ATTACHMENT_FILENAME` ;

