CREATE USER 'spring5'@'localhost' IDENTIFIED BY 'spring5';
CREATE DATABASE spring5fs character set=utf8;
GRANT ALL PRIVILEGES ON spring5fs.* to 'spring5'@'localhost';
CREATE TABLE spring5fs.MEMBER (
    ID int auto_increment primary key ,
    EMAIL varchar(255),
    PASSWORD varchar(100),
    NAME varchar(100),
    REGDATE datetime,
    unique key (EMAIL)
) engine = InnoDB character set = utf8;
