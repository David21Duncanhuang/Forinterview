-- schema.sql

CREATE TABLE IF NOT EXISTS Currency (
    currency_code VARCHAR(5) PRIMARY KEY,
    chinese_name VARCHAR(50)
);

INSERT INTO Currency (currency_code, chinese_name) VALUES ('TWD', '新台幣');
INSERT INTO Currency (currency_code, chinese_name) VALUES ('USD', '美元');
INSERT INTO Currency (currency_code, chinese_name) VALUES ('EUR', '歐元');
-- You can add more SQL statements if needed, such as sample data inserts.
