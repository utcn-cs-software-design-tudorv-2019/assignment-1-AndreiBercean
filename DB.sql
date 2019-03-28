-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `age` INT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Student` (
  `UserID` INT NOT NULL,
  `group` INT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `studentTOuser`
    FOREIGN KEY (`UserID`)
    REFERENCES `mydb`.`User` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Teacher` (
  `UserID` INT NOT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `teacherTOuser`
    FOREIGN KEY (`UserID`)
    REFERENCES `mydb`.`User` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Subject` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TeacherID` INT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `subjectTOteacher`
    FOREIGN KEY (`TeacherID`)
    REFERENCES `mydb`.`Teacher` (`UserID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Enrolment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Enrolment` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `StudentID` INT NULL,
  `SubjectID` INT NULL,
  `grade` FLOAT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `enrolmentTOstudent`
    FOREIGN KEY (`StudentID`)
    REFERENCES `mydb`.`Student` (`UserID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `enrolmentTOsubject`
    FOREIGN KEY (`SubjectID`)
    REFERENCES `mydb`.`Subject` (`ID`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
