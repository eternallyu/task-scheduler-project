DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    description  VARCHAR(5000),
    user_id      BIGINT      NOT NULL REFERENCES users(id),
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status       BOOLEAN      NOT NULL DEFAULT false,
    completed_at TIMESTAMP
);