CREATE DATABASE WebLHK;
USE WebLHK;

Drop table Member;

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
	Id int PRIMARY KEY,
    Title nvarchar(50),
    Brief nvarchar(50),
    Content nvarchar(50),
    CreateDate nvarchar(50),
    UpdateTime nvarchar(50),
    AuthorId nvarchar(50)
);

INSERT INTO Member VALUES(1, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email', 'descrip', 'date', 'time');
INSERT INTO Content VALUES(1, 'Title' 'Brief', 'Content', 'Date', 'Time', 1);
INSERT INTO Content VALUES(2, 'Title2' 'Brief2', 'Content2', 'Date2', 'Time2', 1);