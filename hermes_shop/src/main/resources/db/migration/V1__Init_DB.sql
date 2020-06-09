create table cart (
    id int8 not null,
    wit_delivery boolean not null,
    user_id int8,
    primary key (id));

create table cart_item (
    cart_item_id int8 not null,
    product_id int8 not null,
    quantity int4,
    cart_id int8 not null,
    primary key (cart_item_id, product_id));

create table product_categories (
    product_id int8 not null,
    categories varchar(255));

create table products (
    id int8 not null,
    description varchar(255),
    product_filename varchar(255),
    product_name varchar(255) not null,
    product_price float8 not null,
    storage_count int4 not null,
    primary key (id));

create table user_contacts (
    id int8 not null,
    address varchar(255),
    city varchar(255),
    phone varchar(255),
    user_id int8,
    primary key (id));

create table user_roles (
    user_id int8 not null,
    roles varchar(255));

create table users (
    id int8 not null,
    date_joined timestamp,
    email varchar(255) not null,
    full_name varchar(255) not null,
    is_active boolean not null,
    is_email_verified boolean,
    password varchar(255),
    username varchar(255) not null,
    verification_code varchar(255),
    primary key (id));

alter table if exists products
    drop constraint if exists UK_f55t6sm19p5lrihq24a6knota;

alter table if exists products
    add constraint UK_f55t6sm19p5lrihq24a6knota unique (product_name);

alter table if exists users
    drop constraint if exists UK_6dotkott2kjsp8vw4d0m25fb7;

alter table if exists users add
    constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

create sequence hibernate_sequence start 1 increment 1;

alter table if exists cart
    add constraint FKg5uhi8vpsuy0lgloxk2h4w5o6
    foreign key (user_id) references users;

alter table if exists cart_item
    add constraint FK1uobyhgl1wvgt1jpccia8xxs3
    foreign key (cart_id) references cart;

alter table if exists cart_item
    add constraint FKqkqmvkmbtiaqn2nfqf25ymfs2
    foreign key (product_id) references products;

alter table if exists product_categories
    add constraint FKlda9rad6s180ha3dl1ncsp8n7
    foreign key (product_id) references products;

alter table if exists user_contacts
    add constraint FKqgbpf3rh5b6i7npvr2rf776rd
    foreign key (user_id) references users;

alter table if exists user_roles
    add constraint FKhfh9dx7w3ubf1co1vdev94g3f
    foreign key (user_id) references users;