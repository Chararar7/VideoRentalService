DROP DATABASE IF EXISTS movies_db;

-- Create the movies_db database
CREATE DATABASE movies_db;

-- Select the movies_db database
USE movies_db;

-- Create the genre table
CREATE TABLE genre (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Create the actors table
CREATE TABLE actors (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Create the directors table
CREATE TABLE directors (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Create the movies table
CREATE TABLE movies (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  director_id INT,
  summary TEXT,
  availability BOOLEAN,
  theme VARCHAR(255),
  rented BOOLEAN,
  damaged BOOLEAN,
  PRIMARY KEY (id),
  FOREIGN KEY (director_id) REFERENCES directors(id)
);

-- Create the movie_actor table
CREATE TABLE movie_actor (
  id INT NOT NULL AUTO_INCREMENT,
  movie_id INT,
  actor_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (movie_id) REFERENCES movies(id),
  FOREIGN KEY (actor_id) REFERENCES actors(id)
);
-- Create the users table
CREATE TABLE users (
id INT NOT NULL AUTO_INCREMENT,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
credit_card_number VARCHAR(16) NOT NULL,
type INT NOT NULL,
PRIMARY KEY (id)
);

-- Create the transactions table
CREATE TABLE transactions (
id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
movie_id INT NOT NULL,
rental_date DATE NOT NULL,
return_date DATE,




PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES users(id)

);





-- Insert genres
INSERT INTO genre (name) VALUES ('Action');
INSERT INTO genre (name) VALUES ('Comedy');
INSERT INTO genre (name) VALUES ('Drama');
INSERT INTO genre (name) VALUES ('Horror');
INSERT INTO genre (name) VALUES ('Sci-Fi');

-- Insert actors
INSERT INTO actors (name) VALUES ('Tom Hanks');
INSERT INTO actors (name) VALUES ('Scarlett Johansson');
INSERT INTO actors (name) VALUES ('Denzel Washington');
INSERT INTO actors (name) VALUES ('Robert De Niro');
INSERT INTO actors (name) VALUES ('Meryl Streep');

-- Insert directors
INSERT INTO directors (name) VALUES ('Steven Spielberg');
INSERT INTO directors (name) VALUES ('Martin Scorsese');
INSERT INTO directors (name) VALUES ('Quentin Tarantino');
INSERT INTO directors (name) VALUES ('Christopher Nolan');
INSERT INTO directors (name) VALUES ('Sofia Coppola');

-- Insert movies
INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('Saving Private Ryan', 1, 'Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.', true, 'Action', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('Lost in Translation', 5, 'A faded movie star and a neglected young woman form an unlikely bond after crossing paths in Tokyo.', true, 'Drama', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('The Godfather', 2, 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', true, 'Drama', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('Pulp Fiction', 3, 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', true, 'Crime', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('The Dark Knight', 4, 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', true, 'Action', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('The Shawshank Redemption', 1, 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', true, 'Drama', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('Forrest Gump', 1, 'The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate, and other history unfold through the perspective of an Alabama man with an IQ of 75.', true, 'Drama', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('The Silence of the Lambs', 2, 'A young FBI cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.', true, 'Horror', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('The Matrix', 4, 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.', true, 'Sci-Fi', false, false);

INSERT INTO movies (title, director_id, summary, availability, theme, rented, damaged)
VALUES ('Bridesmaids', 3, 'Competition between the maid of honor and a bridesmaid, over who is the bride''s best friend, threatens to upend the life of an out-of-work pastry chef.', true, 'Comedy', false, false);

-- Insert movie_actor relationships
INSERT INTO movie_actor (movie_id, actor_id) VALUES (1, 1);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (1, 2);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (3, 4);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (3, 5);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (4, 3);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (4, 4);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (4, 5);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (7, 1);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (7, 2);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (8, 4);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (9, 1);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (9, 2);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (10, 2);
INSERT INTO movie_actor (movie_id, actor_id) VALUES (10, 3);


INSERT INTO users (username, password, email, credit_card_number,type)
VALUES ('jane_smith', 'password456', 'janesmith@example.com', '2345678901234567',1);

INSERT INTO users (username, password, email, credit_card_number,type)
VALUES ('bob_johnson', 'password789', 'bobjohnson@example.com', '3456789012345678',1);
INSERT INTO users (username, password, email, credit_card_number,type)
VALUES ('sarah_brown', 'password123', 'sarahbrown@example.com', '4567890123456789',2);

INSERT INTO users (username, password, email, credit_card_number,type)
VALUES ('tom_wilson', 'password456', 'tomwilson@example.com', '5678901234567890',2);

INSERT INTO users (username, password, email, credit_card_number,type)
VALUES ('tom_wilson', 'password456', 'tomwilson@example.com', '5678901234567800',2);

INSERT INTO users (username, password, email, credit_card_number,type)
VALUES ('amy_jones', 'password789', 'amyjones@example.com', '6789012345678901',2);