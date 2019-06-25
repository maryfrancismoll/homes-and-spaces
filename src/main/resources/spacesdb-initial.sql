DROP DATABASE IF EXISTS `spacesdb`;

CREATE DATABASE `spacesdb`;

USE `spacesdb`;

CREATE TABLE `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_name_UNIQUE` (`name` ASC));
  
CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_username_UNIQUE` (`username` ASC));

CREATE TABLE `user_information` (
  `user_id` INT NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email_address` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_information_email_address_UNIQUE` (`email_address` ASC),
  UNIQUE INDEX `user_information_user_id_UNIQUE` (`user_id` ASC));

CREATE TABLE `user_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`));
  
ALTER TABLE `user_information` 
ADD CONSTRAINT `user_information_user_FK`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `user_role` 
ADD INDEX `user_role_role_FK_idx` (`role_id` ASC);

ALTER TABLE `user_role` 
ADD CONSTRAINT `user_role_user_FK`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `user_role_role_FK`
  FOREIGN KEY (`role_id`)
  REFERENCES `role` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
INSERT INTO `role` (`name`)
VALUES ('Admin'), ('User');

