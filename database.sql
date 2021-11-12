CREATE DATABASE WebLHK;
USE WebLHK;

Drop table Member;
Drop table Content;

CREATE TABLE Member(
	Id int PRIMARY KEY,
    Firstname nvarchar(50),
    Lastname nvarchar(50),
    Username nvarchar(50),
    Password nvarchar(50),
    Phone int,
    Email nvarchar(50),
    Description nvarchar(50),
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
INSERT INTO Member VALUES(1, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email', 'descrip', 'date', 'time');
INSERT INTO Member VALUES(2, 'Nguyen2', 'lmao2', 'user2',  'pass2', 123456782, 'email2', 'descrip2', 'date2', 'time2');