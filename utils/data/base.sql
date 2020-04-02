create table Game
(
    name varchar2(128) not null comment 'Game''s name',
    id int auto_increment comment 'Game''s id',
    version varchar2(64) default 'v1.0' not null comment 'Game''s version',
    constraint Game_pk
        primary key (id)
)
comment 'Legendary studio game';

create unique index Game_game_uindex
    on Game (name);

create table News
(
    title varchar(128) null comment 'news''s title',
    sub_title varchar(256) null comment 'news''s subtitle',
    released Date not null comment 'news''s release date',
    id int auto_increment comment 'news''id',
    game varchar(128) null comment 'news''s game',
    content varchar(1024) not null comment 'new''s content url',
    img varchar(1024) null comment 'new''s image',
    lang varchar(64) not null comment 'news''s lang',
    constraint news___fk_game
        foreign key (game) references game (name)
);

create unique index News_id_uindex
    on News (id);

create unique index News_title_uindex
    on News (title);

alter table News
    add constraint News_pk
        primary key (id);