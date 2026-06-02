-- resources/migrations/20260602120000-add-person-table.up.sql
CREATE TABLE person (
  id IDENTITY PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  date_of_birth DATE NOT NULL,
  weight DECIMAL(10,2) NOT NULL
);
