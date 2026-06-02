-- resources/sql/queries/person.sql
-- :name get-persons :? :*
SELECT id, name, surname, date_of_birth, weight
FROM person
ORDER BY id;

-- :name get-person-by-id :? :1
SELECT id, name, surname, date_of_birth, weight
FROM person
WHERE id = :id;

-- :name create-person! :<! :1
INSERT INTO person (name, surname, date_of_birth, weight)
VALUES (:name, :surname, :date_of_birth, :weight)
RETURNING id;

-- :name update-person! :! :n
UPDATE person
SET name = :name,
    surname = :surname,
    date_of_birth = :date_of_birth,
    weight = :weight
WHERE id = :id;

-- :name delete-person! :! :n
DELETE FROM person
WHERE id = :id;
