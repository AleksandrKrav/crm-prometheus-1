-- create database usersdb;
--
-- use usersdb;
-- SET MODE PostgreSQL;
-- SET DATABASE SQL SYNTAX ORA TRUE;

CREATE TABLE users (
    id int  NOT NULL,
    name varchar(255)  NOT NULL,
    login varchar(255)  NOT NULL,
    password varchar(255)  NOT NULL,
    email varchar(255)  NOT NULL,
    roles_id int  NOT NULL,
    is_deleted boolean  NOT NULL DEFAULT false,
--     is_deleted boolean  NOT NULL
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE roles (
  id int  NOT NULL,
  name varchar(255)  NOT NULL,
  CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE TABLE permition (
  can_read boolean  NOT NULL,
  can_write boolean  NOT NULL,
  can_delete boolean  NOT NULL,
  roles_id int  NOT NULL,
  CONSTRAINT permition_pk PRIMARY KEY (roles_id)
);