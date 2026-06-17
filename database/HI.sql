CREATE TABLE users(
id SERIAL PRIMARY KEY ,
username VARCHAR(255) UNIQUE NOT NULL,
age INTEGER,
email VARCHAR(100),
password  VARCHAR(255) NOT NULL
);
CREATE TABLE books(
book_id  VARCHAR(50) PRIMARY KEY,
title  VARCHAR(255) NOT NULL,
author VARCHAR(255)NOT NULL,
available BOOLEAN DEFAULT TRUE
);
CREATE TABLE members(
    member_id VARCHAR(50) PRIMARY KEY,
    member_name VARCHAR(100) NOT NULL
);
CREATE TABLE borrow_records(
    id SERIAL PRIMARY KEY,
    book_id VARCHAR(50),
	REFERENCES books(book_id),
    member_id VARCHAR(50),
	REFERENCES members(member_id),
    issued_by VARCHAR(50),
    borrow_date TIMESTAMP,
    return_date TIMESTAMP
);
