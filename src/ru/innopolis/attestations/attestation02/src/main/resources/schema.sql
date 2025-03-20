CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    price NUMERIC NOT NULL,
    amount INT NOT NULL
);
COMMENT ON TABLE products is 'Товары';

CREATE TABLE IF NOT EXISTS customers (
    id BIGSERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL
);
COMMENT ON TABLE customers is 'Покупатели';

CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL REFERENCES products (id) ON DELETE CASCADE,
    customer_id BIGINT NOT NULL REFERENCES customers (id) ON DELETE CASCADE,
    created_at timestamptz NOT NULL DEFAULT now(),
    amount INT NOT NULL
);
COMMENT ON TABLE products is 'Заказы';

INSERT INTO products (description, price, amount) VALUES ('Товар 1', 100, 15);
INSERT INTO products (description, price, amount) VALUES ('Товар 2', 200, 10);
INSERT INTO products (description, price, amount) VALUES ('Товар 3', 300, 5);
INSERT INTO products (description, price, amount) VALUES ('Товар 4', 400, 20);
INSERT INTO products (description, price, amount) VALUES ('Товар 5', 500, 8);
INSERT INTO products (description, price, amount) VALUES ('Товар 6', 600, 12);
INSERT INTO products (description, price, amount) VALUES ('Товар 7', 700, 7);
INSERT INTO products (description, price, amount) VALUES ('Товар 8', 800, 14);
INSERT INTO products (description, price, amount) VALUES ('Товар 9', 900, 9);
INSERT INTO products (description, price, amount) VALUES ('Товар 10', 1000, 6);

INSERT INTO customers (first_name, last_name) VALUES ('Иван', 'Иванов');
INSERT INTO customers (first_name, last_name) VALUES ('Пётр', 'Петров');
INSERT INTO customers (first_name, last_name) VALUES ('Сергей', 'Сергеев');
INSERT INTO customers (first_name, last_name) VALUES ('Дмитрий', 'Смирнов');
INSERT INTO customers (first_name, last_name) VALUES ('Андрей', 'Андреев');
INSERT INTO customers (first_name, last_name) VALUES ('Александр', 'Александров');
INSERT INTO customers (first_name, last_name) VALUES ('Николай', 'Николаев');
INSERT INTO customers (first_name, last_name) VALUES ('Евгений', 'Евгеньев');
INSERT INTO customers (first_name, last_name) VALUES ('Виктор', 'Викторов');
INSERT INTO customers (first_name, last_name) VALUES ('Михаил', 'Михайлов');

INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 3);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 7);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 1);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 3);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 9);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 5);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 1);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 4);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 3);
INSERT INTO orders (product_id, customer_id, amount)
VALUES ((SELECT id FROM products ORDER BY random() LIMIT 1), (SELECT id FROM customers ORDER BY random() LIMIT 1), 7);
