CREATE TABLE `User` (
    `User_Id` INT NOT NULL AUTO_INCREMENT,
    `Phone_Number` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    `User_Name` VARCHAR(255) NOT NULL,
    `Registration_Time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `Last_Login_Time` TIMESTAMP,
    PRIMARY KEY (`User_Id`)
);

CREATE TABLE `Inventory` (
    `Inventory_Id` INT NOT NULL AUTO_INCREMENT,
    `ISBN` VARCHAR(255) NOT NULL,
    `Store_Time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `Status` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`Inventory_Id`)
);

CREATE TABLE `Book` (
    `ISBN` VARCHAR(255) NOT NULL,
    `Name` VARCHAR(255) NOT NULL,
    `Author` VARCHAR(255) NOT NULL,
    `Introduction` TEXT,
    PRIMARY KEY (`ISBN`)
);

CREATE TABLE `Borrowing_Record` (
	`Borrowing_Id` INT NOT NULL,
    `User_Id` INT NOT NULL,
    `Inventory_Id` INT NOT NULL,
    `Borrowing_Time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `Return_Time` TIMESTAMP,
    INDEX `user_idx` (`User_Id`),
    INDEX `inventory_idx` (`Inventory_Id`),
    FOREIGN KEY (`User_Id`) REFERENCES `User` (`User_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`Inventory_Id`) REFERENCES `Inventory` (`Inventory_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (`Borrowing_Id`)
);
