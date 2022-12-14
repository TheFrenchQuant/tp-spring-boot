-- H2 2.1.214;
;             
CREATE USER IF NOT EXISTS "SA" SALT '2e15c3f8140f3826' HASH 'd11bcf31d097c70631e44727e1b7ac94a0ac138739b8787c5d0a85c54250a4e9' ADMIN;         
CREATE MEMORY TABLE "PUBLIC"."PRODUCT"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 6) NOT NULL,
    "DESCRIPTION" CHARACTER VARYING(255),
    "NAME" CHARACTER VARYING(255),
    "PRICE" FLOAT,
    "QUANTITY" BIGINT
);
ALTER TABLE "PUBLIC"."PRODUCT" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_1" PRIMARY KEY("ID");      
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.PRODUCT; 
INSERT INTO "PUBLIC"."PRODUCT" VALUES
(1, 'Good', 'Apple', 1000.0, 10),
(2, 'OK', 'Samsung', 900.0, 20),
(3, 'Nice', 'Google', 800.0, 30),
(4, 'Cool', 'Xiaomi', 700.0, 40),
(5, 'Fun', 'Sony', 600.0, 50);              
