CREATE DATABASE 'nequi';
CREATE TABLE 'franquicias' (
  'id' BIGINT AUTO_INCREMENT,
  'nombre' VARCHAR NOT NULL,
  PRIMARY KEY ('id')
);
-- DROP TABLE sucursales;

CREATE TABLE 'sucursales' (
  'id' BIGINT AUTO_INCREMENT,
  'nombre' VARCHAR NOT NULL,
  'id_franquicia' BIGINT NOT NULL,
  PRIMARY KEY ('id')
);
-- sucursales foreign keys
ALTER TABLE 'sucursales'
  ADD CONSTRAINT 'fk_franquicias' FOREIGN KEY ('id_franquicia') REFERENCES 'franquicias(id)' ON DELETE CASCADE;-- DROP TABLE products;
CREATE TABLE 'productos' (
  'id' BIGINT AUTO_INCREMENT,
  'nombre' VARCHAR NOT NULL,
  'id_sucursal' BIGINT NOT NULL,
  'stock' BIGINT NOT NULL,
  PRIMARY KEY ('id')
);
-- productos foreign keys
ALTER TABLE 'productos'
  ADD CONSTRAINT 'fk_sucursal' FOREIGN KEY ('id_sucursal') REFERENCES 'sucursales(id)' ON DELETE CASCADE;