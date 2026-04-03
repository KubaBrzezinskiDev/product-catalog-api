CREATE TABLE producers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    producer_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,

    CONSTRAINT fk_products_producer
        FOREIGN KEY (producer_id)
        REFERENCES producers(id)
        ON DELETE CASCADE
);

CREATE TABLE attributes (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL CHECK (type IN ('STRING', 'NUMBER', 'BOOLEAN'))
);

CREATE TABLE product_attributes (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    attribute_id BIGINT NOT NULL,
    value TEXT NOT NULL,

    CONSTRAINT fk_pa_product
        FOREIGN KEY (product_id)
        REFERENCES products(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_pa_attribute
        FOREIGN KEY (attribute_id)
        REFERENCES attributes(id)
        ON DELETE CASCADE,

    CONSTRAINT uq_product_attribute
        UNIQUE (product_id, attribute_id)
);