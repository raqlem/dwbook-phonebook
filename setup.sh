mysql -uroot -p -S /var/run/mysql-mysql/mysqld.sock < /vagrant/dwbook-phonebook/bootstrap.sql
mvn package
