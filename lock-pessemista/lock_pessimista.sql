-- CREATE TABLE TB_PRODUTO (
--     COD_PRODUTO INTEGER AUTO_INCREMENT,
--     DES_PRODUTO VARCHAR(255) NOT NULL,
--     VLR_PRODUTO NUMERIC,
--     QTD_ESTOQUE INTEGER,
--     PRIMARY KEY (COD_PRODUTO)
-- );
--    
-- CREATE TABLE TB_ITEM_PEDIDO (
--     COD_ITEM INTEGER AUTO_INCREMENT,
--     QTD_ITEM INTEGER,
--     COD_PRODUTO INTEGER,
--     PRIMARY KEY (COD_ITEM),
--     FOREIGN KEY (COD_PRODUTO)
--         REFERENCES TB_PRODUTO (COD_PRODUTO)
-- );

drop database lock_pessimista;

create database lock_pessimista;

use lock_pessimista;

select * from pedido;

select * from produto;

insert into produto(descricao_produto, quantidade_estoque, valor_produto)
value ('produto a', 1000, 10.00);