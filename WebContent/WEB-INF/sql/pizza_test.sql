use pizza;

INSERT INTO customer (userId, name, password, phone, address)
VALUES ('ee1', 'eunji', '123', '010-123-4567', 'doyojiro');
commit;
SELECT * FROM customer;
DELETE FROM customer
WHERE userId = 'ee1';
UPDATE customer
SET password='1234', address='yojiro'
WHERE userId = 'ee1';
desc customer;
desc menu;