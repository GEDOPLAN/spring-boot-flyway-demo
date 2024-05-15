create sequence PERSON_SEQ
    increment by 50;

create table PERSON
(
    ID         INTEGER not null
        primary key,
    FIRSTNAME  CHARACTER VARYING(255),
    LASTNAME   CHARACTER VARYING(255)
);