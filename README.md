# lightning-training-mission-4-jdbc

the folder's name is lighting-training-mission-3-jdbc LOL

To get started, create mySQL Database and Table in your localhost by


1. Create Database "db_pet" and grant access to user
```
mysql> CREATE DATABASE db_pet;
mysql> CREATE USER 'petservice'@'localhost' IDENTIFIED BY 'password'
mysql> GRANT ALL ON db_pet.* TO 'petservice'@'localhost';
```
2. Create Table "pet"
```
CREATE table PET (
    id serial NOT NULL PRIMARY KEY,
    name varchar(50) NOT NULL,
    description varchar(100),
    is_exotic boolean NOT NULL DEFAULT 0,
    price float NOT NULL DEFAULT 0
);
```
3. extra, put initial data into database
```
INSERT INTO pet (id, name, description, is_exotic, price)
VALUES (1, 'Cat', 'Cute Cat', true, 9999.99), 
       (2, 'Dog', 'Cute Dog', false, 5000);
```
