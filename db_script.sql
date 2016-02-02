INSERT INTO `Author` (`id`, `biography`, `dateOfBirth`, `dateOfDeath`, `image`, `firstName`, `secondName`) VALUES
(1, 'Born in Morynci', 1814, 1861, 'authors/shevchenko.jpg', 'Taras', 'Shevchenko'),
(2, 'Born in Naguevychi', 1856, 1916, 'authors/franko.gif', 'Ivan', 'Franko');

INSERT INTO `Book` (`id`, `description`, `image`, `title`, `year`) VALUES
(1, 'Shevchenko''s Kobzar', 'books/kobzar.jpg', 'Kobzar', 1840),
(2, 'Lys Mykyta', 'books/farbovanyi_lys.jpg', 'Farbovanyi lys', 1890);

INSERT INTO `Author_Book` (`Author_id`, `books_id`) VALUES
(1, 1),
(2, 2);