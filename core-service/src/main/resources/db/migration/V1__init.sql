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
    price           numeric(8, 2),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

insert into products (title, price, category_id) values
('Milk', 80.00, 1),
('Milk1', 22.00, 1),
('Milk2', 83.00, 1),
('Milk3', 85.00, 1),
('Milk4', 70.00, 1),
('Milk5', 75.00, 1),
('Milk6', 86.00, 1),
('Milk7', 44.00, 1),
('Milk8', 56.00, 1),
('Milk9', 83.00, 1),
('Milk11', 55.00, 1),
('Milk22', 74.00, 1),
('Milk33', 36.00, 1),
('Milk44', 74.00, 1),
('Milk55', 86.00, 1),
('Milk77', 11.00, 1),
('Bread', 25.00, 1),
('Cheese', 300.00, 1);

create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price numeric(8, 2) not null,
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
    price_per_product numeric(8, 2)    not null,
    price             numeric(8, 2)    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);


