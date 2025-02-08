CREATE TABLE countries
(
    id SERIAL PRIMARY KEY,
    country_name VARCHAR(25)
);

CREATE TABLE cities
(
    id SERIAL PRIMARY KEY,
    country_id INTEGER references countries(id),
    city_name VARCHAR(25)
);

CREATE TYPE citizenship_status AS ENUM ('FOREIGNER', 'LOCAL', 'DUALCITIZENSHIP');

CREATE TABLE citizens
(
    id SERIAL PRIMARY KEY,
    city_id INTEGER references cities(id),
    citizen_name VARCHAR(25),
    salary INTEGER,
    citizenship citizenship_status
);