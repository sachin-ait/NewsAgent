drop table if exists publication;
CREATE TABLE `publication` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `frequence` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into publication values(default, "Fox News", 100, 1.5, "Daily");
insert into publication values(default, "Daily Mail", 200, 0.5, "Daily");
insert into publication values(default, "Digits Now", 10, 3, "Weekly");
insert into publication values(default, "Cosmopolitan", 10, 3, "Weekly");
insert into publication values(default, "New Scientist", 5, 5.5, "Monthly");
insert into publication values(default, "IEEE Explore", 5, 10, "Monthly");