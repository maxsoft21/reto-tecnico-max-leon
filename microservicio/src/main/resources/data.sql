
INSERT INTO usuarios (apellido,email,enabled,nombre,password,perfil,telefono,username) VALUES ('Leon','samir_leon_11@hotmail.com',1,'Max','$2a$10$48BEnBXXN17FchjgdTFbcOYKa8Du63raYSfnKtdn.2bhfChchDKWq','foto.png','123456789','max');

INSERT INTO FEES (PRICE_LIST, FEE_VALUE) VALUES (1, 9.0);
INSERT INTO FEES (PRICE_LIST, FEE_VALUE) VALUES (2, 15.0);
INSERT INTO FEES (PRICE_LIST, FEE_VALUE) VALUES (3, 26.0);
INSERT INTO FEES (PRICE_LIST, FEE_VALUE) VALUES (4, 35.0);

INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (1, '2020-06-14 00.00.00', '2020-12-31 23.59.59', 1, 35455, 0, 35.50, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (1, '2020-06-14 15.00.00', '2020-06-14 18.30.00', 2, 35455, 1, 25.45, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (1, '2020-06-15 00.00.00', '2020-06-15 11.00.00', 3, 35455, 1, 30.50, 'EUR');
INSERT INTO PRICES (BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES (1, '2020-06-15 16.00.00', '2020-12-31 23.59.59', 4, 35455, 1, 38.95, 'EUR');