DROP TABLE Pedido IF EXISTS;

CREATE TABLE Pedido  (
    numero_pedido BIGINT IDENTITY NOT NULL PRIMARY KEY,
    numero_mesa INTEGER(2),
    nome_prato VARCHAR(20),
	quantidade INTEGER(2),
	cupom BOOLEAN,
	valor NUMERIC(5,2)
);