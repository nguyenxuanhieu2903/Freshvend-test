-- Password values are intentionally unusable placeholders. Replace through the
-- application authentication flow when password hashing is implemented.
INSERT INTO users (id, username, password_hash, full_name, email, role, is_active, created_at) VALUES
    (1, 'admin.demo', '!seed-only-no-login!', 'FreshVend Demo Admin', 'admin.demo@freshvend.local', 'ADMIN', TRUE, CURRENT_TIMESTAMP),
    (2, 'operator.demo', '!seed-only-no-login!', 'FreshVend Demo Operator', 'operator.demo@freshvend.local', 'OPERATOR', TRUE, CURRENT_TIMESTAMP),
    (3, 'customer.demo', '!seed-only-no-login!', 'FreshVend Demo Customer', 'customer.demo@freshvend.local', 'CUSTOMER', TRUE, CURRENT_TIMESTAMP);

INSERT INTO product (id, name, description, price, stock_quantity, low_stock_threshold, status, created_at) VALUES
    (1, 'Fresh orange juice', 'Chilled, freshly squeezed orange juice (300 ml).', 35000.00, 18, 5, 'ACTIVE', CURRENT_TIMESTAMP),
    (2, 'Greek yogurt cup', 'Plain Greek yogurt (150 g).', 28000.00, 2, 5, 'ACTIVE', CURRENT_TIMESTAMP),
    (3, 'Chicken salad', 'Ready-to-eat chicken salad (250 g).', 52000.00, 9, 3, 'ACTIVE', CURRENT_TIMESTAMP),
    (4, 'Seasonal fruit box', 'A small box of seasonal cut fruit.', 45000.00, 0, 4, 'DISCONTINUED', CURRENT_TIMESTAMP);

INSERT INTO iot_device (id, device_code, device_name, device_type, status, last_heartbeat, created_at) VALUES
    (1, 'FV-HCM-001', 'FreshVend Campus - Building A', 'SMART_VENDING_MACHINE', 'ONLINE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'FV-HCM-002', 'FreshVend Campus - Library', 'SMART_VENDING_MACHINE', 'OFFLINE', CURRENT_TIMESTAMP - INTERVAL '2 hours', CURRENT_TIMESTAMP);

INSERT INTO restock_history (id, product_id, operator_id, quantity, remaining_quantity, expiry_date, restocked_at) VALUES
    (1, 1, 2, 24, 18, CURRENT_DATE + 5, CURRENT_TIMESTAMP - INTERVAL '1 day'),
    (2, 2, 2, 12, 2, CURRENT_DATE + 2, CURRENT_TIMESTAMP - INTERVAL '1 day'),
    (3, 3, 2, 12, 9, CURRENT_DATE + 4, CURRENT_TIMESTAMP - INTERVAL '1 day');

INSERT INTO orders (id, order_code, customer_id, total_amount, order_status, payment_status, created_at, updated_at) VALUES
    (1, 'FV-DEMO-0001', 3, 63000.00, 'COMPLETED', 'SUCCESS', CURRENT_TIMESTAMP - INTERVAL '30 minutes', CURRENT_TIMESTAMP - INTERVAL '29 minutes'),
    (2, 'FV-DEMO-0002', NULL, 52000.00, 'PENDING_PAYMENT', 'NOT_STARTED', CURRENT_TIMESTAMP - INTERVAL '5 minutes', CURRENT_TIMESTAMP - INTERVAL '5 minutes');

INSERT INTO order_item (id, order_id, product_id, quantity, unit_price, subtotal) VALUES
    (1, 1, 1, 1, 35000.00, 35000.00),
    (2, 1, 2, 1, 28000.00, 28000.00),
    (3, 2, 3, 1, 52000.00, 52000.00);

INSERT INTO payment (id, order_id, transaction_code, amount, status, payment_method, processed_at) VALUES
    (1, 1, 'TX-DEMO-0001', 63000.00, 'SUCCESS', 'SIMULATED_QR', CURRENT_TIMESTAMP - INTERVAL '30 minutes');

INSERT INTO stock_reservation (id, order_item_id, restock_history_id, quantity, status, created_at, updated_at) VALUES
    (1, 1, 1, 1, 'CONSUMED', CURRENT_TIMESTAMP - INTERVAL '30 minutes', CURRENT_TIMESTAMP - INTERVAL '29 minutes'),
    (2, 2, 2, 1, 'CONSUMED', CURRENT_TIMESTAMP - INTERVAL '30 minutes', CURRENT_TIMESTAMP - INTERVAL '29 minutes'),
    (3, 3, 3, 1, 'HELD', CURRENT_TIMESTAMP - INTERVAL '5 minutes', CURRENT_TIMESTAMP - INTERVAL '5 minutes');

INSERT INTO dispensing_log (id, order_id, attempt_number, result, attempted_at) VALUES
    (1, 1, 1, 'SUCCESS', CURRENT_TIMESTAMP - INTERVAL '29 minutes');

INSERT INTO sensor_data (id, device_id, temperature, humidity, door_status, data_source, recorded_at, received_at) VALUES
    (1, 1, 4.20, 48.00, 'CLOSED', 'SIMULATED', CURRENT_TIMESTAMP - INTERVAL '1 minute', CURRENT_TIMESTAMP - INTERVAL '1 minute'),
    (2, 2, 6.80, 55.50, 'UNKNOWN', 'SIMULATED', CURRENT_TIMESTAMP - INTERVAL '2 hours', CURRENT_TIMESTAMP - INTERVAL '2 hours');

INSERT INTO alert (id, device_id, product_id, alert_type, message, severity, is_acknowledged, created_at) VALUES
    (1, 1, 2, 'LOW_STOCK', 'Greek yogurt stock is at or below its configured threshold.', 'WARNING', FALSE, CURRENT_TIMESTAMP - INTERVAL '10 minutes'),
    (2, 2, NULL, 'DEVICE_OFFLINE', 'No heartbeat received from FV-HCM-002 for two hours.', 'CRITICAL', FALSE, CURRENT_TIMESTAMP - INTERVAL '15 minutes');

INSERT INTO incident (id, reported_by, alert_id, title, description, status, created_at, updated_at) VALUES
    (1, 2, 2, 'Library machine is offline', 'Check network connectivity and power for device FV-HCM-002.', 'OPEN', CURRENT_TIMESTAMP - INTERVAL '14 minutes', CURRENT_TIMESTAMP - INTERVAL '14 minutes');

INSERT INTO system_config (id, config_key, config_value, description, updated_by, updated_at) VALUES
    (1, 'inventory.low-stock-default', '5', 'Default low-stock threshold for newly configured products.', 1, CURRENT_TIMESTAMP),
    (2, 'iot.temperature-min-celsius', '2.0', 'Lower bound for the chilled storage temperature alert.', 1, CURRENT_TIMESTAMP),
    (3, 'iot.temperature-max-celsius', '8.0', 'Upper bound for the chilled storage temperature alert.', 1, CURRENT_TIMESTAMP);

SELECT setval(pg_get_serial_sequence('users', 'id'), (SELECT MAX(id) FROM users));
SELECT setval(pg_get_serial_sequence('product', 'id'), (SELECT MAX(id) FROM product));
SELECT setval(pg_get_serial_sequence('iot_device', 'id'), (SELECT MAX(id) FROM iot_device));
SELECT setval(pg_get_serial_sequence('orders', 'id'), (SELECT MAX(id) FROM orders));
SELECT setval(pg_get_serial_sequence('order_item', 'id'), (SELECT MAX(id) FROM order_item));
SELECT setval(pg_get_serial_sequence('payment', 'id'), (SELECT MAX(id) FROM payment));
SELECT setval(pg_get_serial_sequence('restock_history', 'id'), (SELECT MAX(id) FROM restock_history));
SELECT setval(pg_get_serial_sequence('stock_reservation', 'id'), (SELECT MAX(id) FROM stock_reservation));
SELECT setval(pg_get_serial_sequence('dispensing_log', 'id'), (SELECT MAX(id) FROM dispensing_log));
SELECT setval(pg_get_serial_sequence('sensor_data', 'id'), (SELECT MAX(id) FROM sensor_data));
SELECT setval(pg_get_serial_sequence('alert', 'id'), (SELECT MAX(id) FROM alert));
SELECT setval(pg_get_serial_sequence('incident', 'id'), (SELECT MAX(id) FROM incident));
SELECT setval(pg_get_serial_sequence('system_config', 'id'), (SELECT MAX(id) FROM system_config));
