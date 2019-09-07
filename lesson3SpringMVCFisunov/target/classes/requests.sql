/*for Windows*/
\! chcp 1251

/*for Linux*/
-- sudo -i -u postgres
-- psql
/*new password for user postgres*/
-- ALTER USER postgres PASSWORD 'admin';

\dn

DROP SCHEMA IF EXISTS lesson3 CASCADE;
CREATE SCHEMA lesson3;

\dn

SET search_path TO lesson3;

DROP TABLE IF EXISTS clients CASCADE;
CREATE TABLE clients (id bigserial, name varchar(255), PRIMARY KEY (id));
\d clients;
INSERT INTO clients (name) VALUES
('Bob'),
('Sem'),
('Karl'),
('Kenny'),
('Kitty');
SELECT * FROM clients;

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
('sold', 12);
SELECT * FROM products;

DROP TABLE IF EXISTS clients_products CASCADE;
CREATE TABLE clients_products (client_id bigint, product_id bigint, FOREIGN KEY (client_id) REFERENCES clients (id), FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO clients_products (client_id, product_id) VALUES
(1, 1),
(2, 1),
(3, 3),
(4, 1),
(5, 4),
(1, 5),
(2, 1),
(3, 6),
(3, 7),
(4, 7),
(4, 2),
(1, 2);
SELECT * FROM clients_products;