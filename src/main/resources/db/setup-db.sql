drop user if exists 'ecommerceuser'@'localhost';
create user 'ecommerceuser'@'localhost' identified by 'ecommerce123';
grant all privileges on ecommercedb.* to 'ecommerceuser'@'localhost';
flush privileges;

drop database if exists ecommercedb;
create database ecommercedb;