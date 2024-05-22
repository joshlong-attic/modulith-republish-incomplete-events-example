create table if not exists orders
(
    id serial primary key
);

create table if not exists orders_line_items
(

    id       serial primary key,
    orders   bigint references orders (id) not null,
    product  bigint                        not null,
    quantity int                           not null default 0

);