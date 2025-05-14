CREATE SCHEMA `racemanagerdb` ;
CREATE TABLE `racemanagerdb`.`race` (
  `idrace` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `startAndFinishLocal` VARCHAR(45) NOT NULL,
  `distance` VARCHAR(45) NOT NULL,
  `date` VARCHAR(45) NOT NULL,
  `limitRaceTime` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idrace`));
