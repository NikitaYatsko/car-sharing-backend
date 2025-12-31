ALTER TABLE users
    ADD COLUMN firstname VARCHAR(255),
    ADD COLUMN lastname VARCHAR(255);

UPDATE users
SET firstname = 'Unknown',
    lastname  = 'Unknown';

ALTER TABLE users
    ALTER COLUMN firstname SET NOT NULL,
    ALTER COLUMN lastname  SET NOT NULL;
