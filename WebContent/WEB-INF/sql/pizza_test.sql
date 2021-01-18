use pizza;

INSERT INTO customer (userId, name, password, phone)
VALUES ('joajoajoa8', 'kimjoa', 'joajoajoa8', '01022223333');
INSERT INTO customer (userId, name, password, phone, ismanager)
VALUES ('EJs Pizza Store', 'manager', 'manager010', '07027215678', 1);
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
SELECT COUNT(*) FROM customercenter WHERE customerId = 'joajoajoa8';

SELECT id, category, customerId, title, content, date, status 
FROM customercenter 
WHERE customerId = 'joajoajoa8'
ORDER BY id DESC 
LIMIT 5, 10;-- (0, 5), (5, 10)
-- 1: ( (pageNum - 1) / 5 + 1 ) * 5 - size
-- 2: ( ( pageNum - 1) / 5 + 1 ) * 5 
-- 1-(0, 5) 2-(6, 10)

commit;
DELETE FROM `pizza`.`comment`;
SELECT * FROM `pizza`.`comment`;

SELECT * FROM comment
WHERE inquiryId = 8;

INSERT INTO comment (inquiryId, customerId, comments, date )
VALUES (8, "zozina", "i have same question", now());

SELECT * FROM cart
WHERE customerId = 1;
DELETE FROM cart;

SELECT * FROM cartDetail;

UPDATE customer
SET point = 1000
WHERE userId='joajoajoa8';
SELECT * FROM customer WHERE userId='joajoajoa8';