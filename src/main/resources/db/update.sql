create table person (
    id serial primary key not null,
    login varchar(2000),
    password varchar(2000),
    employee_id int
);

create table employees (
    id serial primary key not null,
    name varchar(2000),
    surname varchar(2000),
    inn int,
    hire_date timestamp
);

insert into employees (name, surname, inn, hire_date) values ('name1', 'surname1', 111, now());

insert into person (login, password, employee_id) values ('user1', '123', 1);
insert into person (login, password, employee_id) values ('user2', '123', 1);
