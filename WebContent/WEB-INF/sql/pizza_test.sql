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

SELECT * FROM customercenter;
SELECT COUNT(*) FROM customercenter;

SELECT id, category, customerId, title, content, date, status 
FROM customercenter 
ORDER BY id DESC 
LIMIT 0, 5;-- (0, 5), ()