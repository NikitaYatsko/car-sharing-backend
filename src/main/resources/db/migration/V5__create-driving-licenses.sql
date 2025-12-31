create table if not exists "carsharing-schema".driving_licenses
(
    id             uuid primary key,
    user_id        uuid references users (id) unique not null,
    license_number VARCHAR(30) UNIQUE                NOT NULL,
    issued_date    DATE                              NOT NULL,
    expiry_date    DATE                              NOT NULL,
    issued_by      VARCHAR(100)
);
CREATE TABLE "carsharing-schema".license_categories
(
    id   uuid PRIMARY KEY,
    code VARCHAR(5) UNIQUE NOT NULL
);
INSERT INTO "carsharing-schema".license_categories(id, code)
VALUES (gen_random_uuid(), 'A'),
       (gen_random_uuid(), 'B'),
       (gen_random_uuid(), 'C'),
       (gen_random_uuid(), 'D'),
       (gen_random_uuid(), 'BE'),
       (gen_random_uuid(), 'CE');

CREATE TABLE "carsharing-schema".driving_license_categories
(
    driving_license_id uuid REFERENCES driving_licenses (id) ON DELETE CASCADE,
    category_id        uuid REFERENCES license_categories (id) ON DELETE CASCADE,
    PRIMARY KEY (driving_license_id, category_id)
);

