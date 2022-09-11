CREATE TABLE IF NOT EXISTS users
(
    id text NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    birth_date date NOT NULL,
    create_ts timestamp NOT NULL,
    update_ts timestamp NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS authorizations
(
    id text NOT NULL,
    user_id text NOT NULL,
    login text NOT NULL,
    password_hash text NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS tweets
(
    id text NOT NULL,
    tweet text NOT NULL,
    user_id text NOT NULL,
    create_ts timestamp NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id text NOT NULL,
    comment text NOT NULL,
    user_id text NOT NULL,
    create_ts timestamp NOT NULL,
    tweet_id text NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (tweet_id) REFERENCES tweets (id)
);
