SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS tours (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  difficulty INTEGER,
  rating INTEGER
);

--CREATE TABLE IF NOT EXISTS campsites (
-- id int PRIMARY KEY auto_increment,
-- name VARCHAR,
-- rating INTEGER,
-- cost INTEGER
-- tourId INTEGER
--);
