SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dbexhibition
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbexhibition` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `dbexhibition` ;

-- -----------------------------------------------------
-- Table `dbexhibition`.`accesses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbexhibition`.`accesses` (
                                                         `id` INT NOT NULL,
                                                         `name` ENUM('admin', 'user', 'guest') CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
                                                         PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `dbexhibition`.`exposition`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbexhibition`.`exposition` (
                                                           `id` INT NOT NULL AUTO_INCREMENT,
                                                           `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
                                                           `price` INT NOT NULL,
                                                           `date_time` DATETIME NOT NULL,
                                                           `period` DATE NOT NULL,
                                                           `rooms` VARCHAR(45) NOT NULL,
                                                           PRIMARY KEY (`id`),
                                                           UNIQUE INDEX `id_room_UNIQUE` (`id` ASC) INVISIBLE,
                                                           INDEX `rooms_UNIQUE` (`rooms` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 7
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `dbexhibition`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbexhibition`.`order_status` (
                                                             `id` INT NOT NULL,
                                                             `status` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
                                                             PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `dbexhibition`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbexhibition`.`user` (
                                                     `id` INT NOT NULL AUTO_INCREMENT,
                                                     `login` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
                                                     `email` VARCHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
                                                     `password` VARCHAR(32) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
                                                     `name` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
                                                     `access_id` INT NOT NULL DEFAULT '2',
                                                     PRIMARY KEY (`id`),
                                                     UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                     UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
                                                     UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
                                                     INDEX `fk_user_accesses_idx_idx` (`access_id` ASC) VISIBLE,
                                                     CONSTRAINT `fk_user_accesses_idx`
                                                         FOREIGN KEY (`access_id`)
                                                             REFERENCES `dbexhibition`.`accesses` (`id`)
                                                             ON DELETE CASCADE
                                                             ON UPDATE CASCADE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 15
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `dbexhibition`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbexhibition`.`orders` (
                                                       `id` INT NOT NULL AUTO_INCREMENT,
                                                       `billing` INT NOT NULL,
                                                       `user-id` INT NOT NULL,
                                                       `status-id` INT NOT NULL,
                                                       `exposition_id` INT NOT NULL,
                                                       PRIMARY KEY (`id`, `status-id`, `user-id`, `exposition_id`),
                                                       UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                       INDEX `orders-status-id` (`status-id` ASC) INVISIBLE,
                                                       INDEX `orders_exposition_id` (`exposition_id` ASC) INVISIBLE,
                                                       INDEX `fk_orders_user_id_idx` (`user-id` ASC) VISIBLE,
                                                       CONSTRAINT `status-id`
                                                           FOREIGN KEY (`status-id`)
                                                               REFERENCES `dbexhibition`.`order_status` (`id`),
                                                       CONSTRAINT `fk_orders_exposition`
                                                           FOREIGN KEY (`exposition_id`)
                                                               REFERENCES `dbexhibition`.`exposition` (`id`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION,
                                                       CONSTRAINT `fk_orders_user_id`
                                                           FOREIGN KEY (`user-id`)
                                                               REFERENCES `dbexhibition`.`user` (`id`)
                                                               ON DELETE NO ACTION
                                                               ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `dbexhibition`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbexhibition`.`rooms` (
                                                      `id_room` INT NOT NULL,
                                                      `room` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL,
                                                      PRIMARY KEY (`id_room`),
                                                      UNIQUE INDEX `room_UNIQUE` (`room` ASC) INVISIBLE,
                                                      UNIQUE INDEX `id_room_UNIQUE` (`id_room` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
