create table users
(
    userid    int auto_increment
        primary key,
    username  varchar(20)  null,
    salt      varchar(60)  null,
    password  varchar(500) null,
    firstname varchar(20)  null,
    lastname  varchar(20)  null,
    constraint username
        unique (username)
);

create table credentials
(
    credentialid int auto_increment
        primary key,
    url          varchar(100) null,
    username     varchar(30)  null,
    `key`        varchar(60)  null,
    password     varchar(200) null,
    userid       int          null,
    constraint credentials_ibfk_1
        foreign key (userid) references users (userid)
);

create index userid
    on credentials (userid);

create table files
(
    fileId      int auto_increment
        primary key,
    filename    varchar(128) null,
    contenttype varchar(20)  null,
    filesize    varchar(12)  null,
    userid      int          null,
    filedata    blob         null,
    constraint files_ibfk_1
        foreign key (userid) references users (userid)
);

create index userid
    on files (userid);

create table notes
(
    noteid          int auto_increment
        primary key,
    notetitle       varchar(20)   null,
    notedescription varchar(1000) null,
    userid          int           null,
    constraint notes_ibfk_1
        foreign key (userid) references users (userid)
);

create index userid
    on notes (userid);

