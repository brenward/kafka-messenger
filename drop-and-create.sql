
    alter table message 
       drop 
       foreign key FKmejd0ykokrbuekwwgd5a5xt8a;

    alter table message 
       drop 
       foreign key FKb3y6etti1cfougkdr0qiiemgv;

    alter table user_chat 
       drop 
       foreign key FKlr24iyc3pugqj18ybujh6hqmj;

    alter table user_chat 
       drop 
       foreign key FKojd9hqbl3e7kq3vvr9ym218i4;

    drop table if exists chat;

    drop table if exists message;

    drop table if exists user;

    drop table if exists user_chat;

    create table chat (
        id varchar(36) not null,
        chat_name varchar(50) not null,
        primary key (id)
    ) engine=InnoDB;

    create table message (
        sequence bigint not null,
        chat_id varchar(36),
        message_id varchar(36) not null,
        user_id varchar(36),
        message varchar(256) not null,
        primary key (message_id)
    ) engine=InnoDB;

    create table user (
        id varchar(36) not null,
        username varchar(50) not null,
        primary key (id)
    ) engine=InnoDB;

    create table user_chat (
        chat_id varchar(36) not null,
        user_id varchar(36) not null,
        primary key (chat_id, user_id)
    ) engine=InnoDB;

    alter table message 
       add constraint FKmejd0ykokrbuekwwgd5a5xt8a 
       foreign key (chat_id) 
       references chat (id);

    alter table message 
       add constraint FKb3y6etti1cfougkdr0qiiemgv 
       foreign key (user_id) 
       references user (id);

    alter table user_chat 
       add constraint FKlr24iyc3pugqj18ybujh6hqmj 
       foreign key (chat_id) 
       references chat (id);

    alter table user_chat 
       add constraint FKojd9hqbl3e7kq3vvr9ym218i4 
       foreign key (user_id) 
       references user (id);
