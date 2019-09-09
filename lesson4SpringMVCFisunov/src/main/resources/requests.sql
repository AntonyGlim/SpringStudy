/*for Windows*/
-- \! chcp 1251

/*for Linux*/
-- sudo -i -u postgres
-- psql
/*new password for user postgres*/
-- ALTER USER postgres PASSWORD 'admin';

\dn

DROP SCHEMA IF EXISTS lesson4 CASCADE;
CREATE SCHEMA lesson4;

\dn

SET search_path TO lesson4;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial, title varchar(255), cost integer, PRIMARY KEY (id));
\d products;
INSERT INTO products (title, cost) VALUES
('milk', 32),
('bread', 18),
('cheese', 99),
('tomato', 47),
('lime', 32),
('butter', 87),
('apple', 98),
('asparagus', 18),
('eggs', 60),
('banana', 44),
('beef', 55),
('beet', 78),
('berry', 200),
('biscuits', 120),
('bream', 150),
('cabbage', 60),
('cake', 35),
('carrot', 78),
('cherry', 250),
('sold', 12);
SELECT * FROM products;