DROP database IF EXISTS `ShopperShop1`;
create database ShopperShop1;
use ShopperShop1;
create table product (
category varchar(30) not null,
item_id int not null auto_increment,
item_name varchar(30) not null,
quantity int,
amount double,
size varchar(30),
primary key (item_id)
);

create table orderTable(customer_firstName varchar(30) not null,
customer_lastName varchar(30) not null,
order_no int not null auto_increment,
order_amount double,
primary key (order_no));

create table orderDetail( order_no int not null,
item_id int not null,
quantity int not null
foreign key(order_no) references orderTable(order_no),
foreign key (item_id) references product(item_id)
);
//inserted the sample data only in the inventory table. Then used the java code.

Insert into product (category,item_name,quantity,amount) values('Toys','Robot',25,25);
Insert into product (category,item_name,quantity,amount,size) values('Clothing','JiraffPaintTShirt',20,7,'medium,large');