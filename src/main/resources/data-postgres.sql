create table category
(
    category_id   bigint generated always as identity
        primary key,
    category_name varchar(255)
);

alter table category
    owner to postgres;

create table product
(
    product_price numeric,
    category_id   bigint not null
        constraint fk1mtsbur82frn64de7balymq9s
            references category,
    product_id    bigint generated always as identity
        primary key,
    product_desc  varchar(255),
    product_name  varchar(255)
);

alter table product
    owner to postgres;

create table image
(
    id         bigint generated always as identity
        primary key,
    name       varchar(255),
    type       varchar(255),
    pic_byte   bytea,
    product_id integer
        constraint image_product_product_id_fk
            references product
);

alter table image
    owner to postgres;

create table "user"
(
    user_id       bigint generated always as identity
        primary key,
    role          varchar(255),
    user_address  varchar(255),
    user_email    varchar(255)
        unique,
    user_password varchar(255)
);

alter table "user"
    owner to postgres;

create table basket
(
    number_of_products integer,
    basket_id          bigint generated always as identity
        primary key,
    product_id         bigint not null
        constraint basket_productid
            references product,
    user_id            bigint not null
        constraint basket_customer_customer_id_fk
            references "user"
);

alter table basket
    owner to postgres;

create table "order"
(
    order_status varchar(10),
    order_total  numeric(9, 2),
    order_date   date,
    order_id     bigint generated always as identity
        primary key,
    user_id      bigint not null
        constraint ordertable_userid
            references "user"
);

alter table "order"
    owner to postgres;

create table order_detail
(
    number_of_product   integer,
    order_product_price numeric(9, 2),
    order_detail_id     bigint generated always as identity
        primary key,
    order_id            bigint not null
        constraint order_detail_order_order_id_fk
            references "order",
    order_product_id    bigint
        constraint order_detail_product_product_id_fk
            references product
);

alter table order_detail
    owner to postgres;

