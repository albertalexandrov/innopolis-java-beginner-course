SELECT * FROM customers WHERE first_name = 'Иван';
UPDATE customers SET last_name = 'УДАЛИТЬ' WHERE first_name = 'Иван';
DELETE FROM customers WHERE last_name = 'УДАЛИТЬ';

SELECT * FROM products WHERE price BETWEEN 300 AND 500;
UPDATE products SET amount = 0 where amount <= 5;
DELETE FROM products WHERE amount = 0;

SELECT * FROM orders WHERE amount < 3;
UPDATE orders SET amount = 0 FROM products WHERE product_id = products.id and description = 'Товар 1';
DELETE FROM orders USING customers WHERE orders.customer_id = customers.id and customers.first_name ilike 'е%';