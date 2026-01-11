create type request_status as ENUM (
    'PENDING','APPROVED','REJECTED'
    );

create table if not exists "carsharing-schema".driving_license_request
(
    id                uuid primary key,
    user_id           uuid references "carsharing-schema".users (id) not null,
    license_number    varchar(30)                                    not null,
    issued_date       date,
    expiry_date       date,
    issued_by         varchar(100),

    user_selfie_url   text,
    license_photo_url text,

    request_status    request_status default 'PENDING'               not null,
    created_at        timestamp      default now()

);