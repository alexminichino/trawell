CREATE DATABASE IF NOT EXISTS trawell;
use trawell;
DROP table if exists users ;
CREATE TABLE  `trawell`.`users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
