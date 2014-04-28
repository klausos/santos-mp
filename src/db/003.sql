CREATE  TABLE `losantos`.`states` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `short_code` VARCHAR(2) NOT NULL ,
  `name` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `short_code_UNIQUE` (`short_code` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) );

INSERT INTO states (short_code, name) VALUES ('AC','Acre'),('AL','Alagoas'),('AM','Amazonas'),('AP','Amapá'),('BA','Bahia'),('CE','Ceará'),('DF','Distrito Federal'),('ES','Espírito Santo'),('GO','Goiás'),('MA','Maranhão'),('MG','Minas Gerais'),('MS','Mato Grosso do Sul'),('MT','Mato Grosso'),('PA','Pará'),('PB','Paraibá'),('PE','Pernambuco'),('PI','Piauí'),('PR','Paraná'),('RJ','Rio de Janeiro'),('RO','Rondônia'),('RR','Roraima'),('RN','Rio Grande do Norte'),('RS','Rio Grande do Sul'),('SC','Santa Catarina'),('SE','Sergipe'),('SP','São Paulo'),('TO','Tocantins');