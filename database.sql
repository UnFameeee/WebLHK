CREATE DATABASE training;


CREATE DATABASE WebLHK;
USE WebLHK;

Drop table Member;
Drop table Content;

CREATE TABLE Member(
	Id int PRIMARY KEY AUTO_INCREMENT,
    Firstname nvarchar(50),
    Lastname nvarchar(50),
    Username nvarchar(50),
    Password nvarchar(50),
    Phone nvarchar(20),
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
INSERT INTO Member VALUES(3, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');
INSERT INTO Member VALUES(4, 'Nguyenwa', 'lmaowa', 'user',  'password123', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');

INSERT INTO Member VALUES(5, 'Nguyen2', 'lmao2', 'user2',  'pass2', 123456782, 'email2', 'descrip2', 'date2', 'time2');
INSERT INTO Member VALUES(6, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');
INSERT INTO Member VALUES(7, 'Nguyenwa', 'lmaowa', 'user',  'password123', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');

INSERT INTO Member VALUES(8, 'Nguyen2', 'lmao2', 'user2',  'pass2', 123456782, 'email2', 'descrip2', 'date2', 'time2');
INSERT INTO Member VALUES(9, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');
INSERT INTO Member VALUES(10, 'Nguyenwa', 'lmaowa', 'user',  'password123', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');

INSERT INTO Member VALUES(11, 'Nguyen2', 'lmao2', 'user2',  'pass2', 123456782, 'email2', 'descrip2', 'date2', 'time2');
INSERT INTO Member VALUES(12, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');
INSERT INTO Member VALUES(13, 'Nguyenwa', 'lmaowa', 'user',  'password123', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');

INSERT INTO Member VALUES(14, 'Nguyen2', 'lmao2', 'user2',  'pass2', 123456782, 'email2', 'descrip2', 'date2', 'time2');
INSERT INTO Member VALUES(15, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');
INSERT INTO Member VALUES(16, 'Nguyenwa', 'lmaowa', 'user',  'password123', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');

INSERT INTO Member VALUES(17, 'Nguyen2', 'lmao2', 'user2',  'pass2', 123456782, 'email2', 'descrip2', 'date2', 'time2');
INSERT INTO Member VALUES(18, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');
INSERT INTO Member VALUES(19, 'Nguyenwa', 'lmaowa', 'user',  'password123', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');

INSERT INTO Member VALUES(20, 'Nguyen2', 'lmao2', 'user2',  'pass2', 123456782, 'email2', 'descrip2', 'date2', 'time2');
INSERT INTO Member VALUES(21, 'Nguyen', 'lmao', 'user',  'pass', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');
INSERT INTO Member VALUES(22, 'Nguyenwa', 'lmaowa', 'user',  'password123', 12345678, 'email@gmail.com', 'descrip', 'date', 'time');