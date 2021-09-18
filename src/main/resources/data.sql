DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  release DATE NOT NULL,
  genre VARCHAR(250) NOT NULL,
  overview VARCHAR(5000) NOT NULL,
  cover BLOB,
  rating INT NOT NULL
);

INSERT INTO movies (id, title, release, genre, overview, cover, rating) VALUES
  (1, 'The Lord of the Rings', PARSEDATETIME('1 Jan 2002, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'), 'Fantasy/Adventure', 'A young hobbit, Frodo, who has found the One Ring that belongs to the Dark Lord Sauron, begins his journey with eight companions to Mount Doom, the only place where it can be destroyed.', null, 5),
  (2, 'Fight Club', PARSEDATETIME('29 Oct 1999, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'), 'Thriller/Drama', 'Discontented with his capitalistic lifestyle, a white-collared insomniac forms an underground fight club with Tyler, a careless soap salesman. The project soon spirals down into something sinister.', null, 4),
  (3, 'Django Unchained', PARSEDATETIME('18 Jan 2013, 05:15:58 AM','dd MMM yyyy, hh:mm:ss a','en'), 'Western/Action', 'When Django, a slave, is freed, he joins forces with a bounty hunter to rescue his wife, who has been enslaved by Calvin, a hard-hearted plantation owner.', null, 5);



DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  email VARCHAR(250) NOT NULL,
  senha VARCHAR(250) NOT NULL,
  perfil VARCHAR(250) NOT NULL
);

INSERT INTO usuario (id, email, senha, perfil) VALUES
  (1, 'admin@movies.com', '123456', 'ROLE_ADMIN'),
  (2, 'user@movies.com', '123456', 'ROLE_USUARIO');
