CREATE TABLE products_categories
(
    categories_id BIGINT NOT NULL,
    product_id    BIGINT NOT NULL,
    CONSTRAINT pk_products_categories PRIMARY KEY (categories_id, product_id)
);

ALTER TABLE products_categories
    ADD CONSTRAINT fk_procat_on_category FOREIGN KEY (categories_id) REFERENCES categories (id);

ALTER TABLE products_categories
    ADD CONSTRAINT fk_procat_on_product FOREIGN KEY (product_id) REFERENCES products (id);