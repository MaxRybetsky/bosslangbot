create table usr (
    chat_id int8 not null,
    first_name varchar(255),
    last_name varchar(255),
    username varchar(255),
    language int,
    state int,
    primary key (chat_id)
);
