ALTER TABLE `losantos`.`businesses`
	ADD COLUMN `images` INT UNSIGNED NOT NULL DEFAULT 0  AFTER `phone_number` ,
	ADD COLUMN `created_at` DATETIME NOT NULL  AFTER `images` ,
	ADD COLUMN `updated_at` DATETIME NOT NULL  AFTER `created_at` ;
