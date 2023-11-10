

CREATE TABLE IF NOT EXISTS usuarios (
  id bigint NOT NULL AUTO_INCREMENT,
  apellido varchar(255),
  email varchar(255),
  enabled boolean NOT NULL,
  nombre varchar(255),
  password varchar(255),
  perfil varchar(255),
  telefono varchar(255),
  username varchar(255),
  PRIMARY KEY (id)
);



CREATE TABLE IF NOT EXISTS FEES (
    PRICE_LIST bigint AUTO_INCREMENT PRIMARY KEY,
    FEE_VALUE DECIMAL(10, 2) NOT NULL
);

-- Creación de la tabla "PRICES"
CREATE TABLE IF NOT EXISTS PRICES (
    ID bigint AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID INT NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_LIST INT NOT NULL,
    PRODUCT_ID INT NOT NULL,
    PRIORITY INT NOT NULL,
    PRICE DECIMAL(10, 2) NOT NULL,
    CURR VARCHAR(10) NOT NULL
);

