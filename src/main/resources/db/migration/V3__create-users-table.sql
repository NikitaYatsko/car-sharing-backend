create table if not exists "carsharing-schema".users
(
    id         bigserial           not null primary key,
    first_name varchar(255)        not null,
    last_name  varchar(255)        not null,
    email      varchar(255) unique not null,
    password   varchar(255)        not null,
    roles      varchar(50) default 'user',
    created_at timestamp   default current_timestamp,
    updated_at timestamp   default current_timestamp
)