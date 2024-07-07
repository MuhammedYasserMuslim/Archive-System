update deputation set department_accept = 0 where department_accept is null and id > 0;
update deputation set faculty_accept = 0 where faculty_accept is null and id > 0;
update deputation set university_accept = 0 where university_accept is null and id > 0;



ALTER TABLE `archive`.`deputation`
    CHANGE COLUMN `department_accept` `department_accept` TINYINT NOT NULL ,
    CHANGE COLUMN `faculty_accept` `faculty_accept` TINYINT NOT NULL ,
    CHANGE COLUMN `university_accept` `university_accept` TINYINT NOT NULL ;

ALTER TABLE `archive`.`deputation`
    CHANGE COLUMN `university_accept` `university_accept` TINYINT NOT NULL DEFAULT 0 ;

ALTER TABLE `archive`.`deputation`
    CHANGE COLUMN `department_accept` `department_accept` TINYINT NOT NULL DEFAULT 0 ;

ALTER TABLE `archive`.`deputation`
    CHANGE COLUMN `faculty_accept` `faculty_accept` TINYINT NOT NULL DEFAULT 0 ;

