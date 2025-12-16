CREATE TYPE booking_status AS ENUM ('ACTIVE', 'COMPLETED', 'CANCELLED');
CREATE TYPE car_status AS ENUM ('AVAILABLE', 'RENTED', 'MAINTENANCE', 'OUT_OF_SERVICE');
create table if not exists "carsharing-schema".cars
(
    id           bigserial        not null primary key,
    model        varchar(255)     not null,
    state_number varchar(255)     not null,
    type         varchar(255)     not null,
    price        double precision not null,
    status       car_status       not null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp,
    latitude     double precision ,
    longitude    double precision

);
INSERT INTO "carsharing-schema".cars
    (model, state_number, type, price, status, latitude, longitude)
VALUES ('Toyota Corolla', 'AB1234CD', 'sedan', 25.50, 'AVAILABLE', 47.0104, 28.8638),
       ('Volkswagen Golf', 'CD5678EF', 'hatchback', 22.00, 'AVAILABLE', 47.0250, 28.8500),
       ('Tesla Model 3', 'GH9012IJ', 'electric', 35.75, 'MAINTENANCE', 47.0300, 28.8700),
       ('BMW X5', 'KL3456MN', 'SUV', 40.00, 'AVAILABLE', 47.0150, 28.8600),
       ('Mercedes-Benz C200', 'OP7890QR', 'sedan', 30.00, 'AVAILABLE', 47.0200, 28.8750);

create table if not exists "carsharing-schema".users
(
    id         bigserial           not null primary key,
    first_name varchar(255)        not null,
    last_name  varchar(255)        not null,
    email      varchar(255) unique not null,
    password   varchar(255)        not null,
    created_at timestamp   default current_timestamp,
    updated_at timestamp   default current_timestamp
);

create table "carsharing-schema".bookings
(
    id            bigserial        not null primary key,
    user_id       bigint references "carsharing-schema".users (id),
    car_id        bigint references "carsharing-schema".cars (id),
    start_date    timestamp default current_timestamp,
    end_date      timestamp,
    general_price double precision not null,
    status        booking_status   not null
);

