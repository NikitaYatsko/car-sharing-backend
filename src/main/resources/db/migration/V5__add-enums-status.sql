CREATE TYPE booking_status AS ENUM ('ACTIVE', 'COMPLETED', 'CANCELLED');
CREATE TYPE car_status AS ENUM ('AVAILABLE', 'RENTED', 'MAINTENANCE', 'OUT_OF_SERVICE');

UPDATE "carsharing-schema".cars
SET status = UPPER(TRIM(status));


UPDATE "carsharing-schema".cars
SET status = UPPER(TRIM(status));

ALTER TABLE "carsharing-schema".bookings
    ALTER COLUMN status TYPE booking_status
        USING status::booking_status;

ALTER TABLE "carsharing-schema".cars
    ALTER COLUMN status TYPE car_status
        USING status::car_status;

