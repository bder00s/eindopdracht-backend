


INSERT INTO users (username, password, enabled, apiKey, email, fullname, address)
VALUES ('memberstaff', '$2a$12$6UBCjzHlcTPIj1YpAq4NZeZlPQgwkTHfoy81sFWnm7SExcx861KBe', true, '', 'bonne@bonne.nl', 'Bonne de Roos', 'Ergens');

INSERT INTO users (username, password, enabled, apiKey, email, fullname, address)
VALUES ('member', '$2a$12$FNsOf7jf8aoy7LcWhj8ik.LtOjUb3CJ/IGKm7UG/FqdEzeTlk.B8S', true, '', 'ollie@hotmail.com', 'Ollie de Roos', 'zijn mandje');

INSERT INTO users(username, password, enabled, apiKey, email, fullname, address)
VALUES('staff', '$2a$12$SzIOQ1ZJeethTDIMfUEy4u4WUej9yZUxVYRBj12tF8YmcqdI3cGMq', true, '', 'mj@hotmail.com', 'Marjan Drees', 'Acacialaan');



INSERT INTO authorities(username, authority)
VALUES ('memberstaff', 'ROLE_STAFF'), ('memberstaff', 'ROLE_MEMBER');
--
INSERT INTO authorities(username, authority)
VALUES('member', 'ROLE_MEMBER');

INSERT INTO authorities(username, authority)
VALUES('staff', 'ROLE_STAFF');


INSERT INTO books (isbn, author, title, year, available, genre)
VALUES
    (101, 'Sally Rooney', 'Normal People', '2018', false, 'NOVEL'),
    (102, 'J.K. Rowling', 'Harry Potter and the Half-Blood Prince', '2005', true,  'FANTASY');

INSERT INTO reservations (reservation_id, date_of_reservation, reservation_ready)
VALUES (54, '2023-07-17', false);

UPDATE books SET reservation_id = 54 WHERE isbn = 101;
UPDATE books SET reservation_id = 54 WHERE isbn = 102;


