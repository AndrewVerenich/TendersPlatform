ALTER TABLE tenders_db_test.authorities DROP FOREIGN KEY FKk91upmbueyim93v469wj7b2qh;
ALTER TABLE tenders_db_test.tenders DROP FOREIGN KEY FKj34grmfcumlrsemw6e8bwgi1a;
ALTER TABLE tenders_db_test.tenders DROP FOREIGN KEY FKf8p7q8t9d9eicmo37jwetyk4y;
ALTER TABLE tenders_db_test.participants DROP FOREIGN KEY FK61nb4n11pivxkyqlhmxqt6c3n;
ALTER TABLE tenders_db_test.participants DROP FOREIGN KEY FKghixrahoj1s8cloinfx8lyeqa;
ALTER TABLE tenders_db_test.projects DROP FOREIGN KEY FK5t81yf4oxm1m4at389l9na0fy;
DROP TABLE IF EXISTS tenders_db_test.authorities;
DROP TABLE IF EXISTS tenders_db_test.tenders;
DROP TABLE IF EXISTS tenders_db_test.participants;
DROP TABLE IF EXISTS tenders_db_test.projects;
DROP TABLE IF EXISTS tenders_db_test.users;
DROP TABLE IF EXISTS tenders_db_test.persistent_logins;

create table authorities
(
  id int not null auto_increment
    primary key,
  role varchar(255) not null,
  user_id int null
)
;

create index FKk91upmbueyim93v469wj7b2qh
  on authorities (user_id)
;

create table participants
(
  id int not null auto_increment
    primary key,
  bet int null,
  tender_id int null,
  user_id int null
)
;

create index FK61nb4n11pivxkyqlhmxqt6c3n
  on participants (tender_id)
;

create index FKghixrahoj1s8cloinfx8lyeqa
  on participants (user_id)
;

create table persistent_logins
(
  username varchar(64) not null,
  series varchar(64) not null
    primary key,
  token varchar(64) not null,
  last_used timestamp default CURRENT_TIMESTAMP not null
)
;

create table projects
(
  id int not null auto_increment
    primary key,
  complexityClass int null,
  endDate date null,
  firstPrice int null,
  name varchar(255) null,
  customer_id int null
)
;

create index FK5t81yf4oxm1m4at389l9na0fy
  on projects (customer_id)
;

create table tenders
(
  id int not null auto_increment
    primary key,
  active bit null,
  dateEndOfTender date null,
  project_id int null,
  winner_id int null
)
;

create index FKf8p7q8t9d9eicmo37jwetyk4y
  on tenders (winner_id)
;

create index FKj34grmfcumlrsemw6e8bwgi1a
  on tenders (project_id)
;

create table users
(
  id int not null auto_increment
    primary key,
  address varchar(255) null,
  email varchar(255) null,
  name varchar(255) null,
  password varchar(255) null,
  telNumber varchar(255) null,
  username varchar(255) null,
  enabled bit not null
)
;

