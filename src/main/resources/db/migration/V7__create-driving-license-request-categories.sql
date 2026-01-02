create table "carsharing-schema".driving_license_request_categories
(
    request_id  uuid not null,
    category_id uuid not null,
    primary key (request_id, category_id),
    foreign key (request_id) references driving_license_request (id),
    foreign key (category_id) references license_categories (id)
);
