DROP TABLE IF EXISTS cart;
create table cart(
  id           int8 not null,
  wit_delivery boolean not null,
  user_id      int8,
primary key(id));

DROP TABLE IF EXISTS cart_item;
create table cart_item(
  cart_id    int8 not null,
  product_id int8 not null,
  quantity   int4,
primary key(cart_id, product_id));

DROP TABLE IF EXISTS product;
create table product(
  id               int8 not null,
  description      varchar(255),
  product_filename varchar(255),
  product_name     varchar(255) not null,
  product_price    float8 not null,
  storage_count    int4 not null, primary key(id));

DROP TABLE IF EXISTS product_categories;
create table product_categories(
  product_id int8 not null,
  categories varchar(255));

DROP TABLE IF EXISTS user_contacts;
create table user_contacts(
  id      int8 not null,
  address varchar(255),
  city    varchar(255),
  phone   varchar(255),
  user_id int8, primary key(id));

DROP TABLE IF EXISTS user_roles;
create table user_roles(
  user_id int8 not null,
  roles   varchar(255));

DROP TABLE IF EXISTS users;
create table users(
  id                int8 not null,
  date_joined       timestamp,
  email             varchar(255) not null,
  full_name         varchar(255) not null,
  is_active         boolean not null,
  is_email_verified boolean,
  password          varchar(255),
  username          varchar(255) not null,
  verification_code varchar(255), primary key(id));
