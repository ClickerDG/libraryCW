CREATE TABLE publishing
(
    publish_id SERIAL PRIMARY KEY,
    full_name VARCHAR(256) NOT NULL
);

CREATE TABLE edition_type
(
    type_id SERIAL PRIMARY KEY,
    name_type VARCHAR(256) NOT NULL
);

CREATE TABLE genre
(
    genre_id SERIAL PRIMARY KEY,
    genre_name VARCHAR(256) NOT NULL,
    genre_description text
);

CREATE TABLE role
(
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(35) NOT NULL
);

CREATE TABLE author
(
    auth_id SERIAL PRIMARY KEY,
    full_name VARCHAR(256) NOT NULL
);


CREATE TABLE edition
(
    isbn VARCHAR(25) PRIMARY KEY,
    edition_name VARCHAR(256) NOT NULL,
    edition_year VARCHAR(10) NOT NULL,
    edition_description text NOT NULL,
    image_link VARCHAR(1000),
    publish_id SMALLINT NOT NULL,
    type_id SMALLINT NOT NULL,
    FOREIGN KEY (publish_id) REFERENCES publishing (publish_id),
    FOREIGN KEY (type_id) REFERENCES edition_type (type_id)
);
CREATE TABLE author_edition
(
    isbn VARCHAR(25) NOT NULL,
    author_id SMALLINT NOT NULL,
    FOREIGN KEY (isbn) REFERENCES edition (isbn),
    FOREIGN KEY (author_id) REFERENCES author (auth_id)
);

CREATE TABLE genre_edition
(
    isbn VARCHAR(25) NOT NULL,
    genre_id SMALLINT NOT NULL,
    FOREIGN KEY (isbn) REFERENCES edition (isbn),
    FOREIGN KEY (genre_id) REFERENCES genre (genre_id)
);
CREATE TABLE reader
(
    reader_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR (15),
    login VARCHAR (25) NOT NULL,
    password VARCHAR(25) NOT NULL
);
CREATE TABLE rented_edition
(
    rent_id SERIAL PRIMARY KEY,
    isbn VARCHAR(25) NOT NULL,
    reader_id SMALLINT NOT NULL,
    FOREIGN KEY (isbn) REFERENCES edition (isbn),
    FOREIGN KEY (reader_id) REFERENCES reader (reader_id)
);
CREATE TABLE role_user
(
    reader_id SMALLINT NOT NULL,
    role_id SMALLINT NOT NULL,
    FOREIGN KEY (reader_id) REFERENCES reader (reader_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);
CREATE TABLE review
(
    review_id SERIAL PRIMARY KEY,
    review_content VARCHAR(1000),
    isbn VARCHAR(25) NOT NULL,
    reader_id SMALLINT NOT NULL,
    FOREIGN KEY (isbn) REFERENCES edition (isbn),
    FOREIGN KEY (reader_id) REFERENCES reader (reader_id)
);
CREATE TABLE new_editions
(
    edition_isbn VARCHAR(25) NOT NULL,
    FOREIGN KEY (edition_isbn) REFERENCES edition (isbn)
);

