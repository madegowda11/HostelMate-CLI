CREATE DATABASE hostelmate;
USE hostelmate;
CREATE TABLE students(
student_id INT PRIMARY KEY,
name VARCHAR(100),
course VARCHAR(100),
year INT,
contact VARCHAR(15)
);

CREATE TABLE rooms(
room_no INT PRIMARY KEY,
type VARCHAR(50),
capacity INT,
occupied INT DEFAULT 0,
rent DOUBLE
);

CREATE TABLE allocations(
id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
room_no INT,
checkin_date DATE,
checkout_date DATE,
status VARCHAR(20)
);

CREATE TABLE payments(
id INT AUTO_INCREMENT PRIMARY KEY,
student_id INT,
month INT,
year INT,
amount DOUBLE,
status VARCHAR(20)
);
show tables;
