create table posts (
                       id bigint not null auto_increment,
                       content varchar(255),
                       description varchar(255),
                       title varchar(255) unique ,
                       primary key (id)
) engine=InnoDB


