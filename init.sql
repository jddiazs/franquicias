CREATE DATABASE nequi;
USE nequi;
CREATE TABLE nequi.franquicias (
  id BIGINT auto_increment NOT NULL,
  nombre VARCHAR(200) NOT NULL,
  CONSTRAINT franquicias_PK PRIMARY KEY (id)
);
-- DROP TABLE sucursales;

CREATE TABLE nequi.sucursales (
  id BIGINT auto_increment,
  nombre VARCHAR(200) NOT NULL,
  id_franquicia BIGINT NOT NULL,
  CONSTRAINT sucursales_PK PRIMARY KEY (id)
);
-- sucursales foreign keys
ALTER TABLE sucursales
  ADD CONSTRAINT fk_franquicias FOREIGN KEY (id_franquicia) REFERENCES franquicias(id) ON DELETE CASCADE;-- DROP TABLE products;
CREATE TABLE nequi.productos (
  id BIGINT auto_increment,
  nombre VARCHAR(200) NOT NULL,
  id_sucursal BIGINT NOT NULL,
  stock BIGINT NOT NULL,
  CONSTRAINT productos_PK PRIMARY KEY (id)
);
-- productos foreign keys
ALTER TABLE productos
  ADD CONSTRAINT fk_sucursal FOREIGN KEY (id_sucursal) REFERENCES sucursales(id) ON DELETE CASCADE;