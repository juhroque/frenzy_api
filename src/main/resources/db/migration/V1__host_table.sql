create table users (
    id bigint not null auto_increment,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    plan varchar(50),
    email varchar(60),
    cpf varchar(12),
    date_of_birth datetime not null,
    reputation double,
    active tinyint,
    primary key(id)

);

