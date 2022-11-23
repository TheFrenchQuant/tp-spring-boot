-- H2 2.1.214;
;             
CREATE USER IF NOT EXISTS "SA" SALT '9786af1acbb52830' HASH '944b8a54ca92a729c35f6c7589b5401019d401de15fc3f084c78eaee0901cbca' ADMIN;         
CREATE MEMORY TABLE "PUBLIC"."INVOICE"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1 RESTART WITH 6) NOT NULL,
    "CLIENT" CHARACTER VARYING(255)
);     
ALTER TABLE "PUBLIC"."INVOICE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("ID");      
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.INVOICE; 
INSERT INTO "PUBLIC"."INVOICE" VALUES
(1, 'franck'),
(2, 'theo'),
(3, 'jean'),
(4, 'paul'),
(5, 'pierre');               
CREATE MEMORY TABLE "PUBLIC"."PRODUCTS_ORDER"(
    "PRODUCTS_ORDER_ID" BIGINT NOT NULL,
    "QUANTITY" BIGINT,
    "PRODUCT_ID" BIGINT NOT NULL
);        
ALTER TABLE "PUBLIC"."PRODUCTS_ORDER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_B" PRIMARY KEY("PRODUCTS_ORDER_ID", "PRODUCT_ID");  
-- 25 +/- SELECT COUNT(*) FROM PUBLIC.PRODUCTS_ORDER;         
INSERT INTO "PUBLIC"."PRODUCTS_ORDER" VALUES
(1, 1, 1),
(1, 1, 2),
(1, 1, 3),
(1, 1, 4),
(1, 1, 5),
(2, 2, 1),
(2, 2, 2),
(2, 2, 3),
(2, 2, 4),
(2, 2, 5),
(3, 3, 1),
(3, 3, 2),
(3, 3, 3),
(3, 3, 4),
(3, 3, 5),
(4, 4, 1),
(4, 4, 2),
(4, 4, 3),
(4, 4, 4),
(4, 4, 5),
(5, 5, 1),
(5, 5, 2),
(5, 5, 3),
(5, 5, 4),
(5, 5, 5);      
ALTER TABLE "PUBLIC"."PRODUCTS_ORDER" ADD CONSTRAINT "PUBLIC"."FKN6MF4AYK6HHRC40AFBHEDJJNS" FOREIGN KEY("PRODUCTS_ORDER_ID") REFERENCES "PUBLIC"."INVOICE"("ID") NOCHECK;     