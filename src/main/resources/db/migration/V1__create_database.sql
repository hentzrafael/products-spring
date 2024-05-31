CREATE TABLE addresses
(
    id_address   UUID NOT NULL,
    street       VARCHAR(255),
    district     VARCHAR(255),
    house_number VARCHAR(255),
    city         VARCHAR(255),
    state        VARCHAR(255),
    country      VARCHAR(255),
    user_id      UUID,
    CONSTRAINT pk_addresses PRIMARY KEY (id_address)
);

CREATE TABLE categories
(
    id_category UUID NOT NULL,
    title       VARCHAR(255),
    CONSTRAINT pk_categories PRIMARY KEY (id_category)
);

CREATE TABLE comments
(
    id_comment  UUID NOT NULL,
    title       VARCHAR(255),
    description VARCHAR(255),
    rating      INTEGER,
    product_id  UUID,
    user_id     UUID,
    CONSTRAINT pk_comments PRIMARY KEY (id_comment)
);

CREATE TABLE order_products
(
    order_id   UUID NOT NULL,
    product_id UUID NOT NULL
);

CREATE TABLE orders
(
    id_order     UUID NOT NULL,
    created_at   VARCHAR(255),
    status       VARCHAR(255),
    total_amount DOUBLE PRECISION,
    user_id      UUID,
    address_id   UUID,
    CONSTRAINT pk_orders PRIMARY KEY (id_order)
);

CREATE TABLE product_category
(
    category_id UUID NOT NULL,
    product_id  UUID NOT NULL
);

CREATE TABLE products
(
    id_product UUID NOT NULL,
    name       VARCHAR(255),
    value      DECIMAL,
    store_id   UUID,
    CONSTRAINT pk_products PRIMARY KEY (id_product)
);

CREATE TABLE stores
(
    id_store      UUID         NOT NULL,
    cnpj          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    name          VARCHAR(255),
    password      VARCHAR(255),
    password_salt VARCHAR(255),
    address_id    UUID,
    CONSTRAINT pk_stores PRIMARY KEY (id_store)
);

CREATE TABLE users
(
    id_user       UUID NOT NULL,
    full_name     VARCHAR(255),
    date_of_birth VARCHAR(255),
    cpf           VARCHAR(255),
    email         VARCHAR(255),
    password      VARCHAR(255),
    password_salt VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id_user)
);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_address UNIQUE (address_id);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_cnpj UNIQUE (cnpj);

ALTER TABLE stores
    ADD CONSTRAINT uc_stores_email UNIQUE (email);

ALTER TABLE addresses
    ADD CONSTRAINT FK_ADDRESSES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id_user);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id_product);

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id_user);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES addresses (id_address);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id_user);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_STORE FOREIGN KEY (store_id) REFERENCES stores (id_store);

ALTER TABLE stores
    ADD CONSTRAINT FK_STORES_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES addresses (id_address);

ALTER TABLE order_products
    ADD CONSTRAINT fk_ordpro_on_order_model FOREIGN KEY (order_id) REFERENCES orders (id_order);

ALTER TABLE order_products
    ADD CONSTRAINT fk_ordpro_on_product_model FOREIGN KEY (product_id) REFERENCES products (id_product);

ALTER TABLE product_category
    ADD CONSTRAINT fk_procat_on_category_model FOREIGN KEY (category_id) REFERENCES categories (id_category);

ALTER TABLE product_category
    ADD CONSTRAINT fk_procat_on_product_model FOREIGN KEY (product_id) REFERENCES products (id_product);