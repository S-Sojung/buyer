create table user_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null, 
    email varchar not null,
    created_at timestamp
);

create table product_tb(
    id int auto_increment primary key,
    name varchar not null unique,
    price int not null, 
    qty int not null,
    created_at timestamp
);

create table purchase_tb(
    id int auto_increment primary key,
    user_id int ,
    product_id int,
    count int not null,
    created_at timestamp,
    constraint fk_user foreign key (user_id) references user_tb (id),
    constraint fk_product foreign key (product_id) references product_tb (id)
);