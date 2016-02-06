INSERT INTO `AUTHOR` (`id`, `biography`, `dateOfBirth`, `dateOfDeath`, `image`, `firstName`, `middleName`, `lastName`) VALUES
(1, 'Born in Morynci', 1814, 1861, 'authors/shevchenko.jpg', 'Taras', 'Hryhorovych', 'Shevchenko'),
(2, 'Born in Naguevychi', 1856, 1916, 'authors/franko.gif', 'Ivan', 'Yakovych','Franko');

INSERT INTO `BOOK` (`id`, `description`, `image`, `title`, `year`) VALUES
(1, 'Shevchenko''s Kobzar', 'books/kobzar.jpg', 'Kobzar', 1840),
(2, 'Lys Mykyta', 'books/farbovanyi_lys.jpg', 'Farbovanyi lys', 1890);

INSERT INTO `AUTHOR_BOOK` (`author_id`, `book_id`) VALUES
(1, 1),
(2, 2);

INSERT INTO `CATEGORY` (`id`, `name`, `parent_id`) VALUES
  (1, 'Classic', 4),
  (2, 'Children`s', 1),
  (3, 'Modern', 4),
  (4, 'Ukrainian', NULL),
  (5, 'Foreign', NULL),
  (6, 'Classic', 5),
  (7, 'Modern', 5),
  (8, 'Children`s', 3),
  (9, 'Children`s', 6),
  (10, 'Children`s', 7),
  (11, 'Fiction', 1),
  (12, 'Fiction', 3),
  (13, 'Fiction', 6),
  (14, 'Fiction', 7),
  (16, 'Prose', 2),
  (17, 'Adult`s', 1),
  (18, 'Adult`s', 3),
  (19, 'Mystic', 1),
  (20, 'Poems', 1),
  (21, 'Autobiography', 1);

INSERT INTO `CATEGORY_BOOK` (`category_id`, `book_id`) VALUES
  (1, 1),
  (2, 2),
  (20, 1),
  (20, 2);