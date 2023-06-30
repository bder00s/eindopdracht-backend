INSERT INTO users (enabled, username, password, email, fullname, address)
VALUES (true, 'bderoos', 'peer', 'bonne@bonne.nl', 'Bonne de Roos', 'Ergens');

INSERT INTO users (enabled, username, password, email, fullname, address)
VALUES (true, 'ollie123', 'dollen', 'ollie@hotmail.com', 'Ollie de Roos', 'zijn mandje');




INSERT INTO authorities(username, authority)
VALUES ('bderoos', 'ROLE_STAFF');

INSERT INTO authorities(username, authority)
VALUES('ollie123', 'ROLE_MEMBER');


INSERT INTO books (isbn, author, title, year, available, genre)
VALUES
    (1, 'Sally Rooney', 'Normal People', '2018', false, 'NOVEL'),
    (2, 'J.K. Rowling', 'Harry Potter and the Half-Blood Prince', '2005', true,  'FANTASY');
