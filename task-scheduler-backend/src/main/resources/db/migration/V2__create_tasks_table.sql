DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks
(
    id       SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(5000),
    user_id INTEGER NOT NULL REFERENCES users(id),
    completed BOOLEAN NOT NULL DEFAULT false,
    completed_at TIMESTAMP
);