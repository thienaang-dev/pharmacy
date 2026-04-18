-- =====================
-- ROLES TABLE
-- =====================
CREATE TABLE roles
(
    id   UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE UNIQUE INDEX idx_roles_name ON roles (name);

-- =====================
-- USER TABLE
-- =====================
CREATE TABLE users
(
    id            UUID PRIMARY KEY,
    username      VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,

    is_active     BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMP    NOT NULL,
    created_by    UUID,
    modified_at   TIMESTAMP,
    modified_by   UUID,
    deleted_at    TIMESTAMP
);

CREATE UNIQUE INDEX idx_users_username ON users (username);

-- =====================
-- USER_ROLES TABLE
-- =====================
CREATE TABLE user_roles
(
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id)
            REFERENCES roles (id)
            ON DELETE CASCADE
);

-- =====================
-- POPULATE ROLES TABLE
-- =====================
INSERT INTO roles (id, name)
VALUES (gen_random_uuid(), 'ADMIN'),
       (gen_random_uuid(), 'USER');