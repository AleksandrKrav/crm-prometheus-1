-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2015-12-14 10:53:55.826




-- tables
-- Table: browser
CREATE TABLE browser (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT browser_pk PRIMARY KEY (id)
);



-- Table: comment
CREATE TABLE comment (
    id serial  NOT NULL,
    date_of_create timestamp  NOT NULL,
    comment text  NOT NULL,
    CONSTRAINT comment_pk PRIMARY KEY (id)
);



-- Table: company
CREATE TABLE company (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    address varchar(255)  NOT NULL,
    web varchar(255)  NOT NULL,
    email varchar(255)  NOT NULL,
    owner_id int  NOT NULL,
    is_deleted boolean  NOT NULL DEFAULT false,
    CONSTRAINT company_pk PRIMARY KEY (id)
);



-- Table: company_comment
CREATE TABLE company_comment (
    company_id int  NOT NULL,
    comment_id int  NOT NULL,
    CONSTRAINT company_comment_pk PRIMARY KEY (company_id,comment_id)
);



-- Table: company_file
CREATE TABLE company_file (
    company_id int  NOT NULL,
    file_id int  NOT NULL,
    CONSTRAINT company_file_pk PRIMARY KEY (company_id,file_id)
);



-- Table: company_phone
CREATE TABLE company_phone (
    phone_id int  NOT NULL,
    company_id int  NOT NULL,
    CONSTRAINT company_phone_pk PRIMARY KEY (phone_id,company_id)
);



-- Table: company_tag
CREATE TABLE company_tag (
    company_id int  NOT NULL,
    tag_id int  NOT NULL,
    CONSTRAINT company_tag_pk PRIMARY KEY (company_id,tag_id)
);



-- Table: connection_history
CREATE TABLE connection_history (
    id serial  NOT NULL,
    date timestamp  NOT NULL,
    browser_id int  NOT NULL,
    ip varchar(20)  NOT NULL,
    users_id int  NOT NULL,
    CONSTRAINT connection_history_pk PRIMARY KEY (id)
);



-- Table: contact
CREATE TABLE contact (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    positions_id int  NOT NULL,
    company_id int  NOT NULL,
    email varchar(255)  NOT NULL,
    skype varchar(255)  NOT NULL,
    owner_id int  NOT NULL,
    is_deleted boolean  NOT NULL DEFAULT false,
    CONSTRAINT contact_pk PRIMARY KEY (id)
);



-- Table: contact_comment
CREATE TABLE contact_comment (
    contact_id int  NOT NULL,
    comment_id int  NOT NULL,
    CONSTRAINT contact_comment_pk PRIMARY KEY (contact_id,comment_id)
);



-- Table: contact_file
CREATE TABLE contact_file (
    contact_id int  NOT NULL,
    file_id int  NOT NULL,
    CONSTRAINT contact_file_pk PRIMARY KEY (contact_id,file_id)
);



-- Table: contact_phone
CREATE TABLE contact_phone (
    phone_id int  NOT NULL,
    contact_id int  NOT NULL,
    CONSTRAINT contact_phone_pk PRIMARY KEY (phone_id,contact_id)
);



-- Table: contact_tag
CREATE TABLE contact_tag (
    contact_id int  NOT NULL,
    tag_id int  NOT NULL,
    CONSTRAINT contact_tag_pk PRIMARY KEY (contact_id,tag_id)
);



-- Table: currency
CREATE TABLE currency (
    id serial  NOT NULL,
    code varchar(10)  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT currency_pk PRIMARY KEY (id)
);



-- Table: deal
CREATE TABLE deal (
    id serial  NOT NULL,
    date_of_create timestamp  NOT NULL,
    name varchar(255)  NOT NULL,
    budget int  NOT NULL,
    deal_status_id int  NOT NULL,
    currency_id int  NOT NULL,
    owner_id int  NOT NULL,
    company_id int NOT NULL,
    is_deleted boolean  NOT NULL DEFAULT false,
    CONSTRAINT deal_pk PRIMARY KEY (id)
);



-- Table: deal_comment
CREATE TABLE deal_comment (
    deal_id int  NOT NULL,
    comment_id int  NOT NULL,
    CONSTRAINT deal_comment_pk PRIMARY KEY (deal_id,comment_id)
);



-- Table: deal_contact
CREATE TABLE deal_contact (
    deal_id int  NOT NULL,
    contact_id int  NOT NULL,
    CONSTRAINT deal_contact_pk PRIMARY KEY (deal_id,contact_id)
);



-- Table: deal_file
CREATE TABLE deal_file (
    deal_id int  NOT NULL,
    file_id int  NOT NULL,
    CONSTRAINT deal_file_pk PRIMARY KEY (deal_id,file_id)
);



-- Table: deal_status
CREATE TABLE deal_status (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT deal_status_pk PRIMARY KEY (id)
);



-- Table: deal_tag
CREATE TABLE deal_tag (
    deal_id int  NOT NULL,
    tag_id int  NOT NULL,
    CONSTRAINT deal_tag_pk PRIMARY KEY (deal_id,tag_id)
);



-- Table: file
CREATE TABLE file (
    id serial  NOT NULL,
    date_of_create timestamp  NOT NULL,
    name varchar(255)  NOT NULL,
    data bytea  NULL,
    CONSTRAINT file_pk PRIMARY KEY (id)
);



-- Table: permition
CREATE TABLE permition (
    can_read boolean  NOT NULL,
    can_write boolean  NOT NULL,
    can_delete boolean  NOT NULL,
    roles_id int  NOT NULL,
    CONSTRAINT permition_pk PRIMARY KEY (roles_id)
);



-- Table: phone
CREATE TABLE phone (
    id serial  NOT NULL,
    phone_value varchar(255)  NOT NULL,
    phone_type_id int  NOT NULL,
    CONSTRAINT phone_pk PRIMARY KEY (id)
);



-- Table: phone_type
CREATE TABLE phone_type (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT phone_type_pk PRIMARY KEY (id)
);



-- Table: positions
CREATE TABLE positions (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT positions_pk PRIMARY KEY (id)
);



-- Table: roles
CREATE TABLE roles (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT roles_pk PRIMARY KEY (id)
);



-- Table: tag
CREATE TABLE tag (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT tag_pk PRIMARY KEY (id)
);



-- Table: task
CREATE TABLE task (
    id serial  NOT NULL,
    date_of_create timestamp  NOT NULL,
    finish_date timestamp  NOT NULL,
    description varchar(255)  NOT NULL,
    company_id int  NULL,
    deal_id int  NULL,
    contact_id int  NULL,
    owner_id int  NOT NULL,
    task_type_id int  NOT NULL,
    task_status_id int  NOT NULL,
    is_deleted boolean  NOT NULL DEFAULT false,
    CONSTRAINT task_pk PRIMARY KEY (id)
);



-- Table: task_comment
CREATE TABLE task_comment (
    task_id int  NOT NULL,
    comment_id int  NOT NULL,
    CONSTRAINT task_comment_pk PRIMARY KEY (task_id,comment_id)
);



-- Table: task_status
CREATE TABLE task_status (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT task_status_pk PRIMARY KEY (id)
);



-- Table: task_type
CREATE TABLE task_type (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    CONSTRAINT task_type_pk PRIMARY KEY (id)
);



-- Table: users
CREATE TABLE users (
    id serial  NOT NULL,
    name varchar(255)  NOT NULL,
    login varchar(255)  NOT NULL,
    password varchar(255)  NOT NULL,
    email varchar(255)  NOT NULL,
    roles_id int  NOT NULL,
    is_deleted boolean  NOT NULL DEFAULT false,
    CONSTRAINT users_pk PRIMARY KEY (id)
);







-- foreign keys
-- Reference:  Company_User (table: company)


ALTER TABLE company ADD CONSTRAINT Company_User 
    FOREIGN KEY (owner_id)
    REFERENCES users (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  ConnectHistory_User (table: connection_history)


ALTER TABLE connection_history ADD CONSTRAINT ConnectHistory_User 
    FOREIGN KEY (users_id)
    REFERENCES users (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Contact_Company (table: contact)


ALTER TABLE contact ADD CONSTRAINT Contact_Company 
    FOREIGN KEY (company_id)
    REFERENCES company (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Contact_User (table: contact)


ALTER TABLE contact ADD CONSTRAINT Contact_User 
    FOREIGN KEY (owner_id)
    REFERENCES users (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  DealContact_Contact (table: deal_contact)


ALTER TABLE deal_contact ADD CONSTRAINT DealContact_Contact 
    FOREIGN KEY (contact_id)
    REFERENCES contact (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  DealContact_Deal (table: deal_contact)


ALTER TABLE deal_contact ADD CONSTRAINT DealContact_Deal 
    FOREIGN KEY (deal_id)
    REFERENCES deal (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Deal_Currency (table: deal)


ALTER TABLE deal ADD CONSTRAINT Deal_Currency 
    FOREIGN KEY (currency_id)
    REFERENCES currency (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Deal_DealStatus (table: deal)


ALTER TABLE deal ADD CONSTRAINT Deal_DealStatus 
    FOREIGN KEY (deal_status_id)
    REFERENCES deal_status (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Deal_User (table: deal)


ALTER TABLE deal ADD CONSTRAINT Deal_User 
    FOREIGN KEY (owner_id)
    REFERENCES users (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

ALTER TABLE deal ADD CONSTRAINT Deal_Company
    FOREIGN KEY (company_id)
    REFERENCES company (id)
    NOT DEFERRABLE
    INITIALLY IMMEDIATE
;

-- Reference:  Task_Company (table: task)


ALTER TABLE task ADD CONSTRAINT Task_Company 
    FOREIGN KEY (company_id)
    REFERENCES company (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Task_Contact (table: task)


ALTER TABLE task ADD CONSTRAINT Task_Contact 
    FOREIGN KEY (contact_id)
    REFERENCES contact (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Task_Deal (table: task)


ALTER TABLE task ADD CONSTRAINT Task_Deal 
    FOREIGN KEY (deal_id)
    REFERENCES deal (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Task_TaskStatus (table: task)


ALTER TABLE task ADD CONSTRAINT Task_TaskStatus 
    FOREIGN KEY (task_status_id)
    REFERENCES task_status (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Task_TaskType (table: task)


ALTER TABLE task ADD CONSTRAINT Task_TaskType 
    FOREIGN KEY (task_type_id)
    REFERENCES task_type (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Task_User (table: task)


ALTER TABLE task ADD CONSTRAINT Task_User 
    FOREIGN KEY (owner_id)
    REFERENCES users (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  Users_Roles (table: users)


ALTER TABLE users ADD CONSTRAINT Users_Roles 
    FOREIGN KEY (roles_id)
    REFERENCES roles (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  company_comment_comment (table: company_comment)


ALTER TABLE company_comment ADD CONSTRAINT company_comment_comment 
    FOREIGN KEY (comment_id)
    REFERENCES comment (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  company_comment_company (table: company_comment)


ALTER TABLE company_comment ADD CONSTRAINT company_comment_company 
    FOREIGN KEY (company_id)
    REFERENCES company (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  company_file_company (table: company_file)


ALTER TABLE company_file ADD CONSTRAINT company_file_company 
    FOREIGN KEY (company_id)
    REFERENCES company (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  company_file_file (table: company_file)


ALTER TABLE company_file ADD CONSTRAINT company_file_file 
    FOREIGN KEY (file_id)
    REFERENCES file (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  company_phone_company (table: company_phone)


ALTER TABLE company_phone ADD CONSTRAINT company_phone_company 
    FOREIGN KEY (company_id)
    REFERENCES company (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  company_phone_phone (table: company_phone)


ALTER TABLE company_phone ADD CONSTRAINT company_phone_phone 
    FOREIGN KEY (phone_id)
    REFERENCES phone (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  company_tag_company (table: company_tag)


ALTER TABLE company_tag ADD CONSTRAINT company_tag_company 
    FOREIGN KEY (company_id)
    REFERENCES company (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  company_tag_tag (table: company_tag)


ALTER TABLE company_tag ADD CONSTRAINT company_tag_tag 
    FOREIGN KEY (tag_id)
    REFERENCES tag (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  connection_history_browser (table: connection_history)


ALTER TABLE connection_history ADD CONSTRAINT connection_history_browser 
    FOREIGN KEY (browser_id)
    REFERENCES browser (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_comment_comment (table: contact_comment)


ALTER TABLE contact_comment ADD CONSTRAINT contact_comment_comment 
    FOREIGN KEY (comment_id)
    REFERENCES comment (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_comment_contact (table: contact_comment)


ALTER TABLE contact_comment ADD CONSTRAINT contact_comment_contact 
    FOREIGN KEY (contact_id)
    REFERENCES contact (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_file_contact (table: contact_file)


ALTER TABLE contact_file ADD CONSTRAINT contact_file_contact 
    FOREIGN KEY (contact_id)
    REFERENCES contact (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_file_file (table: contact_file)


ALTER TABLE contact_file ADD CONSTRAINT contact_file_file 
    FOREIGN KEY (file_id)
    REFERENCES file (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_phone_contact (table: contact_phone)


ALTER TABLE contact_phone ADD CONSTRAINT contact_phone_contact 
    FOREIGN KEY (contact_id)
    REFERENCES contact (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_phone_phone (table: contact_phone)


ALTER TABLE contact_phone ADD CONSTRAINT contact_phone_phone 
    FOREIGN KEY (phone_id)
    REFERENCES phone (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_positions (table: contact)


ALTER TABLE contact ADD CONSTRAINT contact_positions 
    FOREIGN KEY (positions_id)
    REFERENCES positions (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_tag_contact (table: contact_tag)


ALTER TABLE contact_tag ADD CONSTRAINT contact_tag_contact 
    FOREIGN KEY (contact_id)
    REFERENCES contact (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  contact_tag_tag (table: contact_tag)


ALTER TABLE contact_tag ADD CONSTRAINT contact_tag_tag 
    FOREIGN KEY (tag_id)
    REFERENCES tag (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  deal_comment_comment (table: deal_comment)


ALTER TABLE deal_comment ADD CONSTRAINT deal_comment_comment 
    FOREIGN KEY (comment_id)
    REFERENCES comment (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  deal_comment_deal (table: deal_comment)


ALTER TABLE deal_comment ADD CONSTRAINT deal_comment_deal 
    FOREIGN KEY (deal_id)
    REFERENCES deal (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  deal_file_deal (table: deal_file)


ALTER TABLE deal_file ADD CONSTRAINT deal_file_deal 
    FOREIGN KEY (deal_id)
    REFERENCES deal (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  deal_file_file (table: deal_file)


ALTER TABLE deal_file ADD CONSTRAINT deal_file_file 
    FOREIGN KEY (file_id)
    REFERENCES file (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  deal_tag_deal (table: deal_tag)


ALTER TABLE deal_tag ADD CONSTRAINT deal_tag_deal 
    FOREIGN KEY (deal_id)
    REFERENCES deal (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  deal_tag_tag (table: deal_tag)


ALTER TABLE deal_tag ADD CONSTRAINT deal_tag_tag 
    FOREIGN KEY (tag_id)
    REFERENCES tag (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  permition_roles (table: permition)


ALTER TABLE permition ADD CONSTRAINT permition_roles 
    FOREIGN KEY (roles_id)
    REFERENCES roles (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  phone_phone_type (table: phone)


ALTER TABLE phone ADD CONSTRAINT phone_phone_type 
    FOREIGN KEY (phone_type_id)
    REFERENCES phone_type (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  task_comment_comment (table: task_comment)


ALTER TABLE task_comment ADD CONSTRAINT task_comment_comment 
    FOREIGN KEY (comment_id)
    REFERENCES comment (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;

-- Reference:  task_comment_task (table: task_comment)


ALTER TABLE task_comment ADD CONSTRAINT task_comment_task 
    FOREIGN KEY (task_id)
    REFERENCES task (id)
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE 
;






-- End of file.

