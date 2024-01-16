create table tb_tasks
(
    id          bigserial primary key,
    description varchar(255) not null,
    sql_query   varchar(255) not null,
    title       varchar(255) not null
);

alter table tb_tasks
    owner to postgres;