ALTER TABLE categories_products
DROP
CONSTRAINT fk_catpro_on_category;

ALTER TABLE categories_products
DROP
CONSTRAINT fk_catpro_on_product;

ALTER TABLE products_categories
DROP
CONSTRAINT fk_procat_on_category;

ALTER TABLE products_categories
DROP
CONSTRAINT fk_procat_on_product;

DROP TABLE categories_products CASCADE;

DROP TABLE products_categories CASCADE;