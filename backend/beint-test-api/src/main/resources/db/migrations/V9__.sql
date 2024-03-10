ALTER TABLE products_categories
DROP
CONSTRAINT fk_procat_on_category;

ALTER TABLE products_categories
DROP
CONSTRAINT fk_procat_on_product;

DROP TABLE products_categories CASCADE;