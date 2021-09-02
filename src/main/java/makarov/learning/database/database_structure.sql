CREATE SCHEMA library;

USE library;

CREATE TABLE books(
id INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
title VARCHAR(255) NOT NULL,
isbn VARCHAR(13) NOT NULL UNIQUE,
copies_total INT
);

CREATE TABLE authors (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE author_books (
    id INT NOT NULL AUTO_INCREMENT,
    book_id INT NOT NULL,
    author_id INT NOT NULL,
     FOREIGN KEY (book_id) REFERENCES books(id),
     FOREIGN KEY (author_id) REFERENCES authors(id),
     PRIMARY KEY(id)
);
CREATE TABLE members (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);
CREATE TABLE checked_out_books (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    checked_out_on DATE NOT NULL,
    due_back_on DATE NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (user_id) REFERENCES members(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);