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
SELECT COUNT(*) FROM customercenter WHERE customerId = 'joajoajoa8';

SELECT id, category, customerId, title, content, date, status 
FROM customercenter 
WHERE customerId = 'joajoajoa8'
ORDER BY id DESC 
LIMIT 5, 10;-- (0, 5), (5, 10)
-- 1: ( (pageNum - 1) / 5 + 1 ) * 5 - size
-- 2: ( ( pageNum - 1) / 5 + 1 ) * 5 
-- 1-(0, 5) 2-(6, 10)


CREATE TABLE IF NOT EXISTS `pizza`.`CUSTOMERCENTER2` AS
SELECT * FROM `pizza`.`CUSTOMERCENTER`;
SELECT * FROM customercenter;
INSERT INTO `pizza`.`CUSTOMERCENTER` (category, customerId, title, content)
VALUES ('etc', 'joa', 'title', 'content');
DELETE FROM `pizza`.`CUSTOMERCENTER`;
DROP TABLE `pizza`.`CUSTOMERCENTER2`;
INSERT INTO `pizza`.`CUSTOMERCENTER` SELECT * FROM `pizza`.`CUSTOMERCENTER2`;