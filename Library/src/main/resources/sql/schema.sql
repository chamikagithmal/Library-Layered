CREATE DATABASE library;

USE library;

CREATE TABLE User(
                     name varchar(15),
                     email varchar(15),
                     user_name varchar(15) PRIMARY KEY,
                     password varchar(10) NOT NULL
);

CREATE TABLE Member(
                       mem_id varchar(10) PRIMARY KEY,
                       mem_name varchar(15) NOT NULL,
                       address varchar(10) NOT NULL,
                       tel int(10) NOT NULL,
                       user_name varchar(10) ,
                       CONSTRAINT FOREIGN KEY (user_name) REFERENCES User(user_name) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE Income(
                       ic_id varchar(10) PRIMARY KEY,
                       amount int(15) NOT NULL,
                       date date ,
                       mem_id varchar(10),
                       CONSTRAINT FOREIGN KEY (mem_id) REFERENCES Member(mem_id) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE Employe(
                        emp_id varchar(10) PRIMARY KEY,
                        emp_name varchar(15) NOT NULL,
                        address varchar(25) NOT NULL,
                        tel int(10) NOT NULL,
                        dob date NOT NULL,
                        user_name varchar(10) NOT NULL,
                        CONSTRAINT FOREIGN KEY (user_name) REFERENCES User(user_name)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
);

CREATE TABLE Salary (
                        s_id varchar(10) PRIMARY KEY,
                        amount int NOT NULL,
                        month varchar(20) NOT NULL,
                        ot_bonuses varchar(10) NOT NULL,
                        emp_id varchar(10),
                        CONSTRAINT FOREIGN KEY (emp_id) REFERENCES Employe(emp_id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Supplier(
                         s_id varchar(10) PRIMARY KEY,
                         s_name varchar(15) NOT NULL,
                         address varchar(10) NOT NULL,
                         tel int(10) NOT NULL
);

CREATE TABLE Donation(
                         d_id varchar(10) PRIMARY KEY,
                         d_name varchar(15) NOT NULL,
                         address varchar(10) NOT NULL,
                         tel int(10) NOT NULL,
                         monetary_amount int(10) NOT NULL
);


CREATE TABLE Book(
                     book_id varchar(10) PRIMARY KEY,
                     book_name varchar(15) NOT NULL,
                     qty int,
                     author varchar(10) NOT NULL,
                     category varchar(10) ,
                     language varchar(10) ,
                     d_id varchar(10) NOT NULL,
                     s_id varchar(10) NOT NULL,
                     CONSTRAINT FOREIGN KEY (d_id) REFERENCES Donation(d_id) ON DELETE CASCADE ON UPDATE CASCADE,
                     CONSTRAINT FOREIGN KEY (s_id) REFERENCES Supplier(s_id) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE book_order (
                            mem_id varchar(10),
                            book_id varchar(10),
                            qty int,
                            date date,
                            return_date date,
                            CONSTRAINT FOREIGN KEY (mem_id) REFERENCES Member(mem_id) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT FOREIGN KEY (book_id) REFERENCES Book(book_id) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE Payment (
                         p_id varchar(10) PRIMARY KEY,
                         amount int NOT NULL,
                         date date NOT NULL,
                         s_id varchar(10),
                         CONSTRAINT FOREIGN KEY (s_id) REFERENCES Supplier(s_id) ON DELETE CASCADE ON UPDATE CASCADE
);
drop table book_order;

CREATE TABLE book_order (
                            mem_id varchar(10),
                            book_id varchar(10),
                            qty int,
                            date date,
                            return_date date,
                            CONSTRAINT FOREIGN KEY (mem_id) REFERENCES Member(mem_id) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT FOREIGN KEY (book_id) REFERENCES Book(book_id) ON DELETE CASCADE ON UPDATE CASCADE
);