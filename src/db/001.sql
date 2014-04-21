CREATE SCHEMA `losantos` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

CREATE  TABLE `losantos`.`businesses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(200) NOT NULL ,
  `slug` VARCHAR(255) NOT NULL ,
  `address` VARCHAR(255) NOT NULL ,
  `email` VARCHAR(100) NOT NULL ,
  `phone_number` VARCHAR(200) NULL ,
PRIMARY KEY (`id`) ,
UNIQUE INDEX `id_UNIQUE` (`id` ASC) );

