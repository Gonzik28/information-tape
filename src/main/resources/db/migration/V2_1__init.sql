ALTER TABLE users ADD COLUMN enabled smallint DEFAULT 1;

CREATE TABLE IF NOT EXISTS rights
(
    id SERIAL,
    user_id text NOT NULL,
    user_right text DEFAULT 'user',
    PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES users(id)
);

INSERT INTO rights (user_id)
SELECT id FROM users;

