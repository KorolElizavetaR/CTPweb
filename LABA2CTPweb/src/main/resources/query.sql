SELECT * FROM authorisbn;
DROP TABLE authorisbn;

INSERT INTO authorisbn VALUES ((SELECT "authorID" FROM authors WHERE "firstName" = 'Fyodor' AND "lastName" = 'Dostoevsky'), '1341175996');

SELECT * FROM authors;
DROP TABLE authors;

SELECT * FROM titles;
DROP TABLE titles;

INSERT INTO titles(isbn, title, "editionNumber", year, "publisherID", price) VALUES
	('1341175996', 'Crime and Punishment', 1, 1888, (select "publisherID" from publishers where "publisherName" = 'Anchor'), 8.82);

SELECT * FROM publishers;
DROP TABLE publishers;