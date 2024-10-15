CREATE TABLE countries
(
    id SERIAL PRIMARY KEY,
    country_name VARCHAR(25)
);

CREATE TABLE cities
(
    id SERIAL PRIMARY KEY,
    country_id SERIAL references countries(id),
    city_name VARCHAR(25)
);

CREATE TABLE citizens
(
    id SERIAL PRIMARY KEY,
    city_id SERIAL references cities(id),
    citizen_name VARCHAR(25)
);