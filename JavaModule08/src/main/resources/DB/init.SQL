-- Создание таблицы Roles
CREATE TABLE Roles (
    id SERIAL PRIMARY KEY,    -- Уникальный идентификатор роли
    role_name VARCHAR(50) NOT NULL UNIQUE  -- Имя роли
);

-- Создание таблицы Users
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,     -- Уникальный идентификатор пользователя
    login VARCHAR(50) NOT NULL UNIQUE,  -- Логин пользователя
    password VARCHAR(255) NOT NULL,      -- Пароль пользователя
    name VARCHAR(100) NOT NULL,           -- Имя пользователя
    role_id INT REFERENCES Roles(id)      -- Ссылка на роль
);

-- Добавление ролей: 'USER' и 'ADMIN'
INSERT INTO Roles (role_name) VALUES ('USER'), ('ADMIN');

-- Добавление пользователей: 'USER' и 'ADMIN'
INSERT INTO Users (login, password, name, role_id) VALUES ('user', 'password', 'Usernessa', 1);
INSERT INTO Users (login, password, name, role_id) VALUES ('admin', 'password', 'Adminisander', 2);
