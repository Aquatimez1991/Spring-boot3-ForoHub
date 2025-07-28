create table topicos
(

    id            bigint       not null auto_increment,
    idUsuario        varchar(100) not null,
    mensaje         varchar(100) not null,
    nombreCurso     varchar(50)  not null unique,
    titulo  varchar(100) not null,
    fecha datetime,

    primary key (id)

);