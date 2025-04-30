-- Таблица категорий
CREATE TABLE category (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

-- Таблица блюд
-- Добавить описание с необграниченным текстовым полем
CREATE TABLE product (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        quantity INT NOT NULL,
                        category_id INT NOT NULL,
                        CONSTRAINT fk_product_category
                            FOREIGN KEY (category_id)
                                REFERENCES category(id)
                                ON DELETE RESTRICT
);

-- Таблица параметров
CREATE TABLE parameter (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            category_id INT NOT NULL,
                            parameter_type VARCHAR(50) NOT NULL,
                            CONSTRAINT fk_parameter_category
                                FOREIGN KEY (category_id)
                                    REFERENCES category(id)
                                    ON DELETE RESTRICT
);

-- Таблица связи блюда-параметры
CREATE TABLE product_parameter (
                                   id SERIAL PRIMARY KEY,
                                   product_id INT NOT NULL,
                                   parameter_id INT NOT NULL,
                                   value VARCHAR(255) NOT NULL,
                                   CONSTRAINT fk_product_parameter_product
                                       FOREIGN KEY (product_id)
                                           REFERENCES product(id)
                                           ON DELETE CASCADE,
                                   CONSTRAINT fk_product_parameter_parameter
                                       FOREIGN KEY (parameter_id)
                                           REFERENCES parameter(id)
                                           ON DELETE CASCADE
);