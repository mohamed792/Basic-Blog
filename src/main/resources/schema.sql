create table posts (
                       id bigint not null auto_increment,
                       content varchar(255),
                       description varchar(255),
                       title varchar(255) unique ,
                       primary key (id)
) engine=InnoDB;


CREATE TABLE `blog`.`user` (
                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                               `firstName` VARCHAR(50) NULL DEFAULT NULL,
                               `lastName` VARCHAR(50) NULL DEFAULT NULL,
                               `mobile` VARCHAR(15) NULL,
                               `email` VARCHAR(50) NULL,
                               `passwordHash` VARCHAR(55) NOT NULL,
                               `registeredAt` DATETIME NOT NULL,
                               `lastLogin` DATETIME NULL DEFAULT NULL,
                               `intro` TINYTEXT NULL DEFAULT NULL,
                               `profile` TEXT NULL DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               UNIQUE INDEX `uq_mobile` (`mobile` ASC),
                               UNIQUE INDEX `uq_email` (`email` ASC) );
