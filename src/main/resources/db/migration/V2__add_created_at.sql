ALTER TABLE "carsharing-schema".cars
    ADD COLUMN created_at TIMESTAMP DEFAULT NOW();
ALTER TABLE "carsharing-schema".cars
    ADD COLUMN updated_at TIMESTAMP DEFAULT NOW();


