create table user_roles
(
    user_id bigint not null
        constraint fkq67ct9vcyegsufvaeotuni42d references tb_users,
    roles   varchar(255)
        constraint user_roles_roles_check
            check ((roles)::text = ANY ((ARRAY ['USER'::character varying, 'ADMIN'::character varying])::text[]))
);

alter table user_roles
    owner to postgres;

