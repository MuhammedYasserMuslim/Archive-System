ALTER TABLE `archive`.`exports`
    CHANGE COLUMN `created_by` `created_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `created_date` `created_date` DATETIME(6) NOT NULL,
    CHANGE COLUMN `last_modified_by` `last_modified_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `last_modified_date` `last_modified_date` DATETIME(6) NOT NULL;

ALTER TABLE `archive`.`imports`
    CHANGE COLUMN `created_by` `created_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `created_date` `created_date` DATETIME(6) NOT NULL,
    CHANGE COLUMN `last_modified_by` `last_modified_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `last_modified_date` `last_modified_date` DATETIME(6) NOT NULL;

ALTER TABLE `archive`.`special`
    CHANGE COLUMN `created_by` `created_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `created_date` `created_date` DATETIME(6) NOT NULL,
    CHANGE COLUMN `last_modified_by` `last_modified_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `last_modified_date` `last_modified_date` DATETIME(6) NOT NULL;


ALTER TABLE `archive`.`dean_decisions`
    CHANGE COLUMN `created_by` `created_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `created_date` `created_date` DATETIME(6) NOT NULL,
    CHANGE COLUMN `last_modified_by` `last_modified_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `last_modified_date` `last_modified_date` DATETIME(6) NOT NULL;


ALTER TABLE `archive`.`incoming_signs`
    CHANGE COLUMN `created_by` `created_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `created_date` `created_date` DATETIME(6) NOT NULL,
    CHANGE COLUMN `last_modified_by` `last_modified_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `last_modified_date` `last_modified_date` DATETIME(6) NOT NULL;

ALTER TABLE `archive`.`subjects`
    CHANGE COLUMN `head` `head` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `num` `num` INT NOT NULL;

ALTER TABLE `archive`.`decisions`
    CHANGE COLUMN `num` `num` INT NOT NULL,
    CHANGE COLUMN `qarar` `qarar` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `summary` `summary` VARCHAR(255) NOT NULL;



update `archive`.`archive_file`
set created_by         = 'admin',
    last_modified_by   = 'admin',
    created_date       = '2024-02-22 01:48:50.157252',
    last_modified_date = '2024-02-22 01:48:50.157252'
where id > 0;


ALTER TABLE `archive`.`archive_file`
    CHANGE COLUMN `created_by` `created_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `created_date` `created_date` DATETIME(6) NOT NULL,
    CHANGE COLUMN `last_modified_by` `last_modified_by` VARCHAR(255) NOT NULL,
    CHANGE COLUMN `last_modified_date` `last_modified_date` DATETIME(6) NOT NULL;


update authorities
set name = 'ROLE_MANAGER'
where id = 2;




