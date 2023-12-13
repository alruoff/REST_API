-- customer - пользователь в системе управления заказами, расширяет user

create table if not exists customer
(
    id         bigint       not null primary key auto_increment,
    created_at datetime(6)  null,
    email      varchar(255) null,
    full_name  varchar(255) null,
    is_blocked bit          null,
    position   varchar(255) null,
    updated_at datetime(6)  null,
    order_id   bigint       null
) engine = InnoDB ;

-- user - пользователь для системы Spring Security, в дальнейшей структуре используется customer

create table if not exists user
(
    id          bigint       not null primary key auto_increment,
    password    varchar(255) null,
    login       varchar(255) not null unique,
    customer_id bigint       null,

    constraint customer_FK1 foreign key (customer_id) references customer (id)

) engine = InnoDB;

-- role - роли в системе управления заказами

create table if not exists role
(
    id   bigint not null primary key auto_increment,
    name varchar(255) null
) engine = InnoDB;

-- user_role - взаимосвязь между user и role

create table if not exists user_role
(
    user_id bigint not null,
    role_id bigint not null,
    constraint user_FK foreign key (user_id) references user (id),
    constraint role_FK foreign key (role_id) references role (id)
) engine = InnoDB;

-- technology - хранит параметры, связанные с определённой технологией производства

create table if not exists technology
(
    id          bigint       not null primary key auto_increment,
    created_at  datetime(6)  null,
    description text         null,
    is_active   bit          null,
    name        varchar(255) null,
    updated_at  datetime(6)  null,
    author_id   bigint       null,
    techno_id   bigint       null,

    constraint techno_FK1 foreign key (techno_id) references customer (id),
    constraint author_FK foreign key (author_id) references customer (id)

) engine = InnoDB;

-- corder - заказ в системе управления заказами, тут хранятся значения переменных заказа

create table if not exists corder
(
    id          bigint       not null primary key auto_increment,
    is_active   bit          null,
    name        varchar(255) null,
    customer_id bigint       null,
    created_at  datetime(6)  null,
    updated_at  datetime(6)  null,
    techno_id   bigint       null,
    variables   text         null,
    constraint customer_FK2 foreign key (customer_id) references customer (id),
    constraint techno_FK2 foreign key (techno_id) references technology (id)
) engine = InnoDB ;

-- Таблица хранит взаимосвязь между технологией и заказами, которые по этой технологии запущены в Производстве

create table if not exists technology_list_of_orders
(
    technology_id     bigint not null,
    list_of_orders_id bigint not null unique,

    constraint corder_FK foreign key (list_of_orders_id) references corder (id),
    constraint techno_FK3 foreign key (technology_id) references technology (id)

) engine = InnoDB;

create table if not exists operationa
(
      id                  bigint       not null primary key auto_increment,
      add_amount          int          null,
      amount_of_operation int          null,
      size_string         varchar(255) null,
      fact_time           double       null,
      plan_date           datetime(6)  null,
      plan_time           double       null,
      name                varchar(255) null,
      second_name         varchar(255) null
) engine = InnoDB;

create table if not exists operationb
(
    id                  bigint not null auto_increment primary key,
    add_amount          int          null,
    amount_of_operation int          null,
    datum               datetime(6)  null,
    info                varchar(255) null,
    size_string         varchar(255) null,
    name                varchar(255) null
) engine = InnoDB;

create table if not exists operationc
(
    id                  bigint       not null primary key auto_increment,
    add_amount          int          null,
    amount_of_operation int          null,
    datum               datetime(6)  null,
    info                varchar(255) null,
    size_string         varchar(255) null,
    name                varchar(255) null
) engine = InnoDB;

create table if not exists operationd
(
    id                  bigint       not null primary key auto_increment,
    add_amount          int          null,
    amount_of_operation int          null,
    fact_time           double       null,
    plan_date           datetime(6)  null,
    plan_time           double       null,
    size_string         varchar(255) null,
    name                varchar(255) null
) engine = InnoDB;
create table if not exists operatione
(
    id                  bigint       not null primary key auto_increment,
    add_amount          int          null,
    amount_of_operation int          null,
    fact_time           double       null,
    plan_date           datetime(6)  null,
    plan_time           double       null,
    size_string         varchar(255) null,
    name                varchar(255) null
) engine = InnoDB;
create table if not exists hibernate_sequence
(
    next_val bigint null
);






