CREATE USER 'Shopper_user'@'localhost' IDENTIFIED BY 'spring';

GRANT ALL PRIVILEGES ON ShopperShop1.* TO 'Shopper_user'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'Shopper_user'@'localhost';