create table "carsharing-schema".car_images
(
    id        uuid primary key,
    car_id    uuid not null
        references "carsharing-schema".cars(id)
            on delete cascade,
    image_url text not null
);
