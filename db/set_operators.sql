CREATE TABLE movie
(
    id       SERIAL PRIMARY KEY,
    name     text,
    director text
);

CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  text,
    author text
);

INSERT INTO movie (name, director)
VALUES ('���������', '����� �����'),
       ('�������', '������ ��������'),
       ('��������� �����', '����� �������'),
       ('����� ������ � ����� ��������', '�������� ������'),
       ('�������� �������', '���� �����');

INSERT INTO book (title, author)
VALUES ('����� ������ � ����� ��������', '����� �������'),
       ('��������� �����', '���� ������'),
       ('1984', '������ ������'),
       ('���������', '���� ���'),
       ('������������ �������', '����� ��������');

SELECT name as movies_based_on_the_book
FROM movie
INTERSECT
SELECT title
FROM book;

SELECT title as books_without_film_adaptations
FROM book
EXCEPT
SELECT name
FROM movie;

(SELECT name as movies_and_books_unique
  FROM movie
  EXCEPT
  SELECT title
  FROM book)
UNION
  (SELECT title
  FROM book
  EXCEPT
  SELECT name
  FROM movie)
  ORDER BY movies_and_books_unique;