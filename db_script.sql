INSERT INTO `AUTHOR` (`id`, `biography`, `dateOfBirth`, `dateOfDeath`, `image`, `firstName`, `middleName`, `lastName`)
VALUES
  (1, 'Народився в Моринцях', 1814, 1861, 'authors/shevchenko.jpg', 'Тарас', 'Григорович', 'Шевченко'),
  (2, 'Народився в Нагуєвичах', 1856, 1916, 'authors/franko.gif', 'Іван', 'Якович', 'Франко');

INSERT INTO `BOOK` (`id`, `description`, `image`, `title`, `year`) VALUES
  (1, 'Шевченків Кобзар', 'books/kobzar.jpg', 'Кобзар', 1840),
  (2, 'Лис Микита', 'books/farbovanyi_lys.jpg', 'Фарбований лис', 1890);

INSERT INTO `BOOK_AUTHOR` (`book_id`, `author_id`) VALUES
  (1, 1),
  (2, 2);

INSERT INTO `CATEGORY` (`id`, `name`, `parent_id`) VALUES
  (1, 'Українська', NULL),
  (2, 'Зарубіжна', NULL),
  (3, 'Класика', 1),
  (4, 'Сучасна', 1),
  (5, 'Класика', 2),
  (6, 'Сучасна', 2),
  (7, 'Дитяча', 3),
  (8, 'Дитяча', 4),
  (9, 'Дитача', 5),
  (10, 'Дитяча', 6),
  (11, 'Фантастика', 3),
  (12, 'Фантастика', 4),
  (13, 'Фантастика', 5),
  (14, 'Фантастика', 6),
  (16, 'Проза', 3),
  (17, 'Для дорослих', 4),
  (18, 'Для дорослих', 6),
  (19, 'Містика', 3),
  (20, 'Поезія', 3),
  (21, 'Автобіграфії', 6);

INSERT INTO `BOOK_CATEGORY` (`book_id`, `category_id`) VALUES
  (1, 20),
  (2, 9);

INSERT INTO `TAG` (`id`, `title`) VALUES
  (1, 'казки'),
  (2, 'лірика'),
  (3, 'про_тварин'),
  (4, 'Шевченко');

INSERT INTO `BOOK_TAG` (`book_id`, `tag_id`) VALUES
  (1, 2),
  (1, 4),
  (2, 1),
  (2, 3);

