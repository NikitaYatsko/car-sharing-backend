create table "carsharing-schema".bookings
(
    id            bigserial        not null primary key,
    user_id       bigint references "carsharing-schema".users (id),
    car_id        bigint references "carsharing-schema".cars (id),
    start_date    timestamp default current_timestamp,
    end_date      timestamp,
    general_price double precision not null,
    status        varchar(20)
)