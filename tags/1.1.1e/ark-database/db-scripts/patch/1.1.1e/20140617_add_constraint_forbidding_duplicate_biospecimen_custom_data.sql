ALTER TABLE `lims`.`biospecimen_custom_field_data` 
DROP INDEX `FK_BIOSPECFDATA_BIOSPECIMEN_ID` 
, ADD UNIQUE INDEX `FK_BIOSPECFDATA_BIOSPECIMENID_CFDID` (`BIOSPECIMEN_ID` ASC, `CUSTOM_FIELD_DISPLAY_ID` ASC) ;

