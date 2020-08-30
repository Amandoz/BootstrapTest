CREATE TABLE IF NOT EXISTS users (
    id BIGINT NOT NULL,
    name VARCHAR(15) NOT NULL,
    email VARCHAR(20) NOT NULL,
    age int NOT NULL,
    primary key (id)
);

DROP TABLE IF EXISTS users;