# Library Management System

A console-based Library Management System built in Java. The project started as a file-based application and was later migrated to PostgreSQL using JDBC and the DAO pattern.

## Features

### User Management

* Register User
* Login
* Logout
* Search User
* Display Users
* Update User
* Change Password
* Delete User

### Member Management

* Add Member
* Search Member
* Display Members

### Book Management

* Add Book
* Search Book
* Display Books
* Borrow Book
* Return Book
* Display Borrowed Books
* Display Available Books

### Borrow Records

* Track borrowed books
* Record borrower information
* Record issuer information
* Store borrow and return history

## Technologies Used

* Java
* PostgreSQL
* JDBC
* DAO Pattern
* Git & GitHub

## Database Tables

* users
* members
* books
* borrow_records

## Project Structure

```text
database/
├── DatabaseConnection.java
├── UserDAO.java
├── MemberDAO.java
└── BookDAO.java

manager/
├── UserManager.java
├── MemberManager.java
└── BookManager.java

model/
├── User.java
├── Member.java
└── Book.java
```

## What I Learned

* Object-Oriented Programming
* JDBC
* PostgreSQL Integration
* SQL CRUD Operations
* DAO Architecture
* Git and GitHub Workflow
* Database Design
* Input Validation and Error Handling

## Future Improvements

* Role-Based Access Control
* Fine Management System
* Due Date Tracking
* GUI Version (JavaFX)
* Spring Boot REST API Version

## Author
Nikunj Garg

Nikunj Garg
