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
  (1, 'Classic', NULL),
  (2, 'Children`s', 1);

INSERT INTO `CATEGORY_BOOK` (`category_id`, `book_id`) VALUES
  (1, 1),
  (2, 2);