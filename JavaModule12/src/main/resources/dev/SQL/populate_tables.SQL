INSERT INTO category (name)
VALUES ('steaks');

INSERT INTO parameter (name, category_id, parameter_type)
VALUES
    ('doneness', 1, 'STRING'),
    ('fed', 1, 'STRING'),
    ('cow_age', 1, 'STRING'),
    ('cow_breed', 1, 'STRING');

INSERT INTO product (name, price, quantity, category_id)
VALUES
    ('Ribeye Steak', 29.99, 10, 1),
    ('T-bone Steak', 29.99, 10, 1),
    ('Wagyu Steak', 99.99, 5, 1);

-- Параметры для Ribeye Steak
INSERT INTO product_parameter (product_id, parameter_id, value)
VALUES
    (1, 1, 'Medium Rare'),
    (1, 2, 'Grass-fed'),
    (1, 3, 'Adult'),
    (1, 4, 'Hereford');


-- Параметры для T-bone Steak
INSERT INTO product_parameter (product_id, parameter_id, value)
VALUES
    (2, 1, 'Well Done'),
    (2, 2, 'Grain-fed'),
    (2, 3, 'Young'),
    (2, 4, 'Hereford');

-- Параметры для Wagyu Steak Steak
INSERT INTO product_parameter (product_id, parameter_id, value)
VALUES
    (3, 1, 'Medium Rare'),
    (3, 2, 'Grain-fed'),
    (3, 3, 'Semi adult'),
    (3, 4, 'Japanese Black');