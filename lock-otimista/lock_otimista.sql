-- CREATE TABLE produto (
--     COD_PRODUTO INTEGER AUTO_INCREMENT,
--     DES_PRODUTO VARCHAR(255) NOT NULL,
--     VLR_PRODUTO NUMERIC,
--     QTD_ESTOQUE INTEGER,
--     COD_VRS INTEGER,
--     PRIMARY KEY (COD_PRODUTO)
-- );
--    
-- CREATE TABLE pedido (
--     COD_ITEM INTEGER AUTO_INCREMENT,
--     QTD_ITEM INTEGER,
--     COD_PRODUTO INTEGER,
--     PRIMARY KEY (COD_ITEM),
--     FOREIGN KEY (COD_PRODUTO)
--     REFERENCES TB_PRODUTO (COD_PRODUTO)
-- ); 

drop database lock_otimista; 

create database lock_otimista;

use lock_otimista;

select * from pedido;

select * from produto;

insert into produto(codigo_versionamento, descricao_produto, quantidade_estoque, valor_produto)
value (1,'produto a', 1000, 10.00);

				