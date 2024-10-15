CREATE TABLE countries
(
    country_id NUMERIC PRIMARY KEY,
    country_name VARCHAR(25)
);

CREATE TABLE cities
(
    city_id NUMERIC PRIMARY KEY,
    country_id NUMERIC NOT NULL references countries(country_id),
    city_name VARCHAR(25)
);

CREATE TABLE citizens
(
    citizen_id NUMERIC PRIMARY KEY,
    city_id NUMERIC NOT NULL references cities(city_id),
    city_name VARCHAR(25)
);