TRUNCATE businesses;
ALTER TABLE `losantos`.`businesses` ADD COLUMN `city_id` INT UNSIGNED NOT NULL  AFTER `updated_at` ;
