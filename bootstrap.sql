CREATE DATABASE IF NOT EXISTS phonebook;
CREATE USER 'phonebookuser'@'localhost' IDENTIFIED BY 'phonebookpassword';
GRANT ALL ON phonebook.* TO 'phonebookuser'@'localhost';
USE phonebook;
CREATE TABLE IF NOT EXISTS `contact` ( `id` int(11) NOT NULL AUTO_INCREMENT, `firstName` varchar(255) NOT NULL, `lastName` varchar(255) NOT NULL, `phone` varchar(30) NOT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1; 

