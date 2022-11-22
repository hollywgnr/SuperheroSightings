DROP DATABASE IF EXISTS SuperheroSightingsDB;
CREATE DATABASE SuperheroSightingsDB;

USE SuperheroSightingsDB;

CREATE TABLE superperson(
    superperson_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(25) NOT NULL,
    `description` VARCHAR(50) NOT NULL,
    superpower VARCHAR(25) NOT NULL,
    is_hero BOOLEAN
);

CREATE TABLE `organization`(
    organization_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(25) NOT NULL,
    `description` VARCHAR(50) NOT NULL,
    address VARCHAR(25) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    email VARCHAR(64) NOT NULL
);

CREATE TABLE location(
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(25) NOT NULL,
    `description` VARCHAR(50) NOT NULL,
    address VARCHAR(25) NOT NULL,
    lattitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL
);

CREATE TABLE organization_superperson(
    organization_id INT NOT NULL,
    superperson_id INT NOT NULL,
    PRIMARY KEY(organization_id, superperson_id),
    FOREIGN KEY (organization_id) REFERENCES `organization`(organization_id),
	FOREIGN KEY (superperson_id) REFERENCES superperson(superperson_id)
);

CREATE TABLE sighting(
    sighting_id INT PRIMARY KEY AUTO_INCREMENT,
	location_id INT NOT NULL,
    FOREIGN KEY (location_id) REFERENCES location(location_id),
	superperson_id INT NOT NULL,
    FOREIGN KEY (superperson_id) REFERENCES superperson(superperson_id),
    sighting_time DATETIME
);
