create table if not exists "carsharing-schema".cars
(
    id           bigserial        not null primary key,
    model        varchar(255)     not null,
    state_number varchar(255)     not null,
    type         varchar(255)     not null,
    price        double precision not null,
    status       varchar(255)     not null,
    created_at   timestamp default current_timestamp,
    updated_at   timestamp default current_timestamp,
    latitude     double precision not null,
    longitude    double precision not null

);
