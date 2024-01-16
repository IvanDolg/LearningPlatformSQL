create table tb_users
(
    id         bigserial primary key,
    email      varchar(255) not null,
    last_visit timestamp(6),
    password   varchar(255) not null,
    username   varchar(255) not null
);

alter table tb_users
    owner to postgres;