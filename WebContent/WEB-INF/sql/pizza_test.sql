use pizza;

INSERT INTO customer (userId, name, password, phone, address)
VALUES ('ee1', 'eunji', '123', '010-123-4567', 'doyojiro');
commit;
SELECT * FROM customer;
DELETE FROM customer;
desc customer;
desc menu;