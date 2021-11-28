CREATE DATABASE training;

CREATE DATABASE WebLHK;
USE WebLHK;

Drop table Member;
Drop table Content;

CREATE TABLE Member(
	Id int PRIMARY KEY AUTO_INCREMENT,
    Role nvarchar(50),
    Firstname nvarchar(50),
    Lastname nvarchar(50),
    Username nvarchar(50),
    Password nvarchar(50),
    Phone nvarchar(20),
    Email nvarchar(50),
    Description nvarchar(5000),
    CreatedDate nvarchar(50),
    UpdateTime nvarchar(50)
);

CREATE TABLE Content(
	Id int PRIMARY KEY auto_increment,
    Title nvarchar(200),
    Brief nvarchar(150),
    Content nvarchar(1000),
    CreateDate nvarchar(50),
    UpdateTime nvarchar(50),
    AuthorId int
);

DELETE FROM `weblhk`.`member` WHERE (`Id` = '1');
INSERT INTO Member VALUES(1, 'Admin', 'Nguyen', 'Admin', 'Admin',  'password', 123456789, 'email@gmail.com', 'description', 'date', 'time');
INSERT INTO Member VALUES(2, 'Member','Nguyen2', 'lmao2', 'user2',  'pass2', 123456782, 'email2', 'descrip2', 'date2', 'time2');