INSERT INTO `AUTHOR` (`id`, `biography`, `dateOfBirth`, `dateOfDeath`, `image`, `firstName`, `middleName`, `lastName`) VALUES
(1, 'Born in Morynci', 1814, 1861, 'authors/shevchenko.jpg', 'Taras', 'Hryhorovych', 'Shevchenko'),
(2, 'Born in Naguevychi', 1856, 1916, 'authors/franko.gif', 'Ivan', 'Yakovych','Franko');

INSERT INTO `BOOK` (`id`, `description`, `image`, `title`, `year`) VALUES
(1, 'Shevchenko''s Kobzar', 'books/kobzar.jpg', 'Kobzar', 1840),
(2, 'Lys Mykyta', 'books/farbovanyi_lys.jpg', 'Farbovanyi lys', 1890);

INSERT INTO `BOOK_AUTHOR` (`author_id`, `book_id`) VALUES
(1, 1),
(2, 2);

INSERT INTO `CATEGORY` (`id`, `name`, `parent_id`) VALUES
  (1, 'Ukrainian', NULL),
  (2, 'Foreign', NULL),
  (3, 'Classic', 1),
  (4, 'Modern', 1),
  (5, 'Classic', 2),
  (6, 'Modern', 2),
  (7, 'Children`s', 3),
  (8, 'Children`s', 4),
  (9, 'Children`s', 5),
  (10, 'Children`s', 6),
  (11, 'Fiction', 3),
  (12, 'Fiction', 4),
  (13, 'Fiction', 5),
  (14, 'Fiction', 6),
  (16, 'Prose', 3),
  (17, 'Adult`s', 4),
  (18, 'Adult`s', 6),
  (19, 'Mystic', 3),
  (20, 'Poems', 3),
  (21, 'Autobiography', 6);

INSERT INTO `BOOK_CATEGORY` (`category_id`, `book_id`) VALUES
  (1, 1),
  (2, 2),
  (20, 1),
  (20, 2);