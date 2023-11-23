-- CREATE TABLE Posts
-- (
--     id          BIGINT PRIMARY KEY AUTO_INCREMENT,
--     description VARCHAR(255) UNIQUE NOT NULL,
--     image_url   VARCHAR(255) null,
--     created_at  DATETIME,
--     updated_at  DATETIME
-- );



create table posts
(
    created_at  datetime(6),
    id          bigint not null auto_increment,
    updated_at  datetime(6),
    description varchar(255),
    image_url   varchar(255),
    primary key (id)
) engine=InnoDB;
create table refreshtokens
(
    id          integer not null auto_increment,
    expiry_date datetime(6),
    user_id     bigint,
    token       varchar(255),
    primary key (id)
) engine=InnoDB;
create table users
(
    id       bigint not null auto_increment,
    email    varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
) engine=InnoDB;

alter table refreshtokens
    add constraint UK_d9nxc88vu0ad8pqfupn0kjlmf unique (user_id);

alter table refreshtokens
    add constraint FKg71xhi5ujnqbgw2rcvtxyrc8s foreign key (user_id) references users (id);

alter table posts add column user_id bigint;
alter table posts
    add constraint FK5lidm6cqbc7u4xhqpxm898qme foreign key (user_id) references users (id);