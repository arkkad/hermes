create table user_roles (
    user_id varchar(36) not null,
    roles varchar(255));

create table users (
    id varchar(36) not null,
    date_joined timestamp,
    email varchar(255) not null,
    full_name varchar(255) not null,
    is_active boolean not null,
    is_email_verified boolean,
    password varchar(255),
    username varchar(255) not null,
    verification_code varchar(255),
    primary key (id));

create table user_contacts (
    id int8 not null,
    address varchar(255),
    city varchar(255),
    phone varchar(255),
    primary key (id));

alter table if exists users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7
    unique (email);

alter table if exists user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f
     foreign key (user_id) references users;

create table products (
    id int8 not null,
    description TEXT,
    product_name varchar(255) not null,
    product_filename varchar(255),
    product_price float8 not null,
    storage_count int4 not null,
    primary key (id));

create table product_categories (
    product_id int8 not null,
    categories varchar(255));

create sequence hibernate_sequence start 1 increment 1;

alter table if exists product_categories
    add constraint FKlda9rad6s180ha3dl1ncsp8n7
    foreign key (product_id) references products;


create table shopping_cart (
    id int8 not null,
    wit_delivery boolean not null,
    primary key (id));

create table shopping_cart_item (
    product_id int8 not null,
    shopping_cart_item_id int8 not null,
    quantity int4,
    shopping_cart_id int8 not null,
    primary key (product_id, shopping_cart_item_id));


alter table if exists shopping_cart_item
    add constraint FKp7bt5oxffihlykeyv9eu5vs7i
    foreign key (product_id) references products;

alter table if exists shopping_cart_item
    add constraint FKtaxfo8drwlxjtg1f1y9h4t5t2
    foreign key (shopping_cart_id) references shopping_cart;