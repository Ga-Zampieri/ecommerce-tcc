create sequence perfume_id_seq start 14 increment 1;
create sequence users_id_seq start 2 increment 1;
create sequence order_item_seq start 12 increment 1;
create sequence orders_seq start 6 increment 1;

CREATE TABLE IF NOT EXISTS FOTOS (
    ID_FOTO     NUMERIC        NOT NULL,
    NOME        VARCHAR(50)    NOT NULL,
    TIPO        VARCHAR(10)    NOT NULL,
    ARQUIVO     BYTEA           NOT NULL,

    CONSTRAINT PK_FOTOS PRIMARY KEY (ID_FOTO)
);

CREATE SEQUENCE SEQ_FOTOS
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

    create table order_item
(
    id_order   numeric,
    quantity   numeric,
    id_perfume numeric
);

create table orders
(
    id           numeric,
    id_user      numeric,
    date         date,
    status       numeric,
    total_price  decimal(11,2),
    primary key (id)
);

create table perfume
(
    id_perfume              numeric not null,
    categories              varchar(255),
    price                   decimal(11,2),
    name                    varchar(255),
    foto_id                 numeric(255),
    description             varchar(255),

    primary key (id_perfume),
    foreign key (foto_id) references fotos (ID_FOTO)
);

create table perfume_reviews
(
    perfume_id numeric not null,
    reviews_id numeric not null
);

create table review
(
    id      numeric,
    author  varchar(255),
    date    date,
    message varchar(255),
    rating  numeric,
    primary key (id)
);

CREATE TABLE CARGO (
    ID_CARGO NUMERIC NOT NULL,
    NOME varchar(512) UNIQUE NOT NULL,
    PRIMARY KEY(ID_CARGO)
);

CREATE SEQUENCE seq_cargo
 START WITH     3
 INCREMENT BY   1;

create table USERS
(
    id                  numeric    not null,
    active              boolean not null,
    address             varchar(255),
    city                varchar(255),
    email               varchar(255),
    first_name          varchar(255),
    last_name           varchar(255),
    password            varchar(255),
    phone_number        varchar(255),
    post_index          varchar(255),
    primary key (id)
);


CREATE TABLE USER_CARGO (
    ID_USER  NUMERIC NOT NULL,
    ID_CARGO NUMERIC NOT NULL,
    PRIMARY KEY(ID_USER, ID_CARGO),
    CONSTRAINT FK_USER_CARGO_CARGO FOREIGN KEY (ID_CARGO) REFERENCES CARGO (ID_CARGO),
  	CONSTRAINT FK_USER_CARGO_USER FOREIGN KEY (ID_USER) REFERENCES USERS (ID)
);


alter table if exists perfume_reviews add constraint UK_REVIEW_ID unique (reviews_id);
alter table if exists order_item add constraint FK_ORDER_ITEM_PERFUME foreign key (id_perfume) references perfume;
alter table if exists order_item add constraint PK_ORDER_ITEM primary key (id_order, id_perfume);
alter table if exists orders add constraint FK_ORDERS_USER foreign key (id_user) references users (id);
alter table if exists perfume_reviews add constraint FK_PERF_REV_REVIEW foreign key (reviews_id) references review;
alter table if exists perfume_reviews add constraint FK_PERF_REV_PERFUME foreign key (perfume_id) references perfume;