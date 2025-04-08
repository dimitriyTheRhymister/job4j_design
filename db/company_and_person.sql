CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

SELECT name
FROM person
WHERE company_id != 5;

SELECT p.name AS person_name, c.name AS company_name
FROM person p
JOIN company c ON p.company_id = c.id;

SELECT c.name AS company_name, COUNT(p.id) AS person_count
FROM company c
LEFT JOIN person p ON c.id = p.company_id
GROUP BY c.id, c.name
HAVING COUNT(p.id) = (
    SELECT MAX(person_count)
    FROM (
        SELECT COUNT(p.id) AS person_count
        FROM company c
        LEFT JOIN person p ON c.id = p.company_id
        GROUP BY c.id
    ) AS counts
)
ORDER BY c.name;