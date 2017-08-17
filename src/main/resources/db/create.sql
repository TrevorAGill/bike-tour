SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS tours (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  difficulty INTEGER,
  rating INTEGER,
  season VARCHAR,
  distance INTEGER,
  description VARCHAR
);

CREATE TABLE IF NOT EXISTS campsites (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 rating INTEGER,
 cost INTEGER,
 tourid INTEGER,
 showers VARCHAR,
 bikerepair VARCHAR,
 reservation VARCHAR,
 foodavailable VARCHAR,
 phone VARCHAR
);

CREATE TABLE IF NOT EXISTS reviews (
  id int PRIMARY KEY auto_increment,
  comment VARCHAR,
  rating INTEGER,
  campsiteid INTEGER
);
