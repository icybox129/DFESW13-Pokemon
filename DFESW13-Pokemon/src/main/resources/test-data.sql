DROP TABLE IF EXISTS `pokemon`;

CREATE TABLE pokemon(
id long AUTO_INCREMENT,
ndex VARCHAR(10) NOT NULL,
name VARCHAR(100) NOT NULL,
type VARCHAR(100) NOT NULL,
gender VARCHAR(100) NOT NULL,
height INT NOT NULL,
weight INT NOT NULL,
bst INT NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO `pokemon` (`ndex`, `name`, `type`, `gender`, `height`, `weight`, `bst`,) VALUES ('001', 'Bulbasaur', 'Grass/Poison', 'Male', 0.7, 6.9, 318);
INSERT INTO `pokemon` (`ndex`, `name`, `type`, `gender`, `height`, `weight`, `bst`,) VALUES ('004', 'Charmander', 'Fire', 'Male', 0.6, 8.5, 309);
INSERT INTO `pokemon` (`ndex`, `name`, `type`, `gender`, `height`, `weight`, `bst`,) VALUES ('007', 'Squirtle', 'Water', 'Male', 0.5, 9.0, 314);
