CREATE  TABLE `losantos`.`services` (
  `business_id` INT UNSIGNED NOT NULL ,
  `description` VARCHAR(255) NOT NULL ,
  `quantity` INT UNSIGNED NOT NULL DEFAULT 1 ,
  `price` DECIMAL(8,2) UNSIGNED NOT NULL ,
  INDEX `business_index` (`business_id` ASC) );
