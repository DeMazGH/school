CREATE TABLE users
(
    id          SERIAL8 PRIMARY KEY,
    name        VARCHAR,
    age         int,
    have_license BOOLEAN,
    car_id      BIGINT REFERENCES cars (id)
);

CREATE TABLE cars
(
    id    SERIAL8 PRIMARY KEY,
    brand VARCHAR,
    model VARCHAR,
    cost  money
);