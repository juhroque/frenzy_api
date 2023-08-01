create table cars (
    id bigint not null auto_increment,
    host_id bigint not null,
    brand varchar(255) not null,
    model varchar(255) not null,
    description varchar(255),
    category varchar(255) not null,
    color varchar(255),
    seats int,
    license_plate varchar(8) not null unique,
    review double,
    state varchar(30),
    daily_price decimal(15,2),
    lat DECIMAL(10, 8) NOT NULL,
    lng DECIMAL(11, 8) NOT NULL,
    active tinyint,

    constraint fk_car_host_id foreign key(host_id) references users(id),

    primary key(id)
);
