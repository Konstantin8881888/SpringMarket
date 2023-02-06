create table categories (
    id              bigserial primary key,
    title           varchar(255) unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into categories (title) values ('Food'), ('Others');

create table products
(
    id              bigserial primary key,
    title           varchar(255),
    category_id     bigint references categories (id),
    price           int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id) values
('Milk', 80, 1),
('Milk1', 22, 1),
('Milk2', 83, 1),
('Milk3', 85, 1),
('Milk4', 70, 1),
('Milk5', 75, 1),
('Milk6', 86, 1),
('Milk7', 44, 1),
('Milk8', 56, 1),
('Milk9', 83, 1),
('Milk11', 55, 1),
('Milk22', 74, 1),
('Milk33', 36, 1),
('Milk44', 74, 1),
('Milk55', 86, 1),
('Milk77', 11, 1),
('Bread', 25, 1),
('Cheese', 300, 1);

create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price int not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);


