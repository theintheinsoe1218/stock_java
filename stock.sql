SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS stockMovement;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS Item;
DROP TABLE IF EXISTS Unit;
DROP TABLE IF EXISTS userAccount;




/* Create Tables */

CREATE TABLE department
(
	departmentId int NOT NULL AUTO_INCREMENT,
	departmentName varchar(255) NOT NULL,
	created_at datetime NOT NULL,
	updated_at datetime NOT NULL,
	PRIMARY KEY (departmentId)
);


CREATE TABLE Item
(
	itemId int NOT NULL AUTO_INCREMENT,
	itemName varchar(255) NOT NULL,
	itemCode varchar(255),
	unitId int NOT NULL,
	reorderLevel int DEFAULT 0 NOT NULL,
	remark varchar(255),
	created_at datetime NOT NULL,
	updated_at datetime NOT NULL,
	PRIMARY KEY (itemId)
);


CREATE TABLE stockMovement
(
	stockMovementId int NOT NULL AUTO_INCREMENT,
	itemId int NOT NULL,
	userAccountId int NOT NULL,
	movementType enum('OPENING', 'IN', 'OUT', 'ADJUST_IN', 'ADJUST_OUT','WASTE') NOT NULL,
	fromDepartmentId int,
	toDepartmentId int NOT NULL,
	qty int DEFAULT 0 NOT NULL,
	remark varchar(255),
	movementDate date NOT NULL,
	created_at datetime NOT NULL,
	updated_at datetime NOT NULL,
	PRIMARY KEY (stockMovementId)
);


CREATE TABLE Unit
(
	unitId int NOT NULL AUTO_INCREMENT,
	unitName varchar(255) NOT NULL,
	created_at datetime NOT NULL,
	updated_at datetime NOT NULL,
	PRIMARY KEY (unitId)
);


CREATE TABLE userAccount
(
	userAccountId int NOT NULL AUTO_INCREMENT,
	profileName varchar(100) NOT NULL,
	userName varchar(200) NOT NULL,
	password varchar(255) NOT NULL,
	encryptPassword varchar(100) NOT NULL,
	phone varchar(100),
	address varchar(255),
	remark varchar(200),
	date datetime NOT NULL,
	modifiedDate datetime NOT NULL,
	userType enum('ADMIN','STAFF') NOT NULL,
	-- 0 is inactive,
	-- 1 is active.
	status boolean DEFAULT '1' NOT NULL COMMENT '0 is inactive,
1 is active.',
	PRIMARY KEY (userAccountId)
);



/* Create Foreign Keys */

ALTER TABLE stockMovement
	ADD FOREIGN KEY (fromDepartmentId)
	REFERENCES department (departmentId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE stockMovement
	ADD FOREIGN KEY (toDepartmentId)
	REFERENCES department (departmentId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE stockMovement
	ADD FOREIGN KEY (itemId)
	REFERENCES Item (itemId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Item
	ADD FOREIGN KEY (unitId)
	REFERENCES Unit (unitId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE stockMovement
	ADD FOREIGN KEY (userAccountId)
	REFERENCES userAccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



