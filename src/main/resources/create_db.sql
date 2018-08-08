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
	bet int not null,
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
	complexityClass int not null,
	endDate date not null,
	firstPrice int not null,
	name varchar(255) not null,
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
	active bit not null,
	dateEndOfTender date not null,
	project_id int null,
	winner_id int null,
	constraint FKj34grmfcumlrsemw6e8bwgi1a
	foreign key (project_id) references tenders_db.projects (id),
	constraint FKf8p7q8t9d9eicmo37jwetyk4y
	foreign key (winner_id) references tenders_db.participants (id)
)
;

create index FKf8p7q8t9d9eicmo37jwetyk4y
	on tenders (winner_id)
;

create index FKj34grmfcumlrsemw6e8bwgi1a
	on tenders (project_id)
;

alter table participants
	add constraint FK61nb4n11pivxkyqlhmxqt6c3n
foreign key (tender_id) references tenders_db.tenders (id)
;

create table users
(
	id int not null auto_increment
		primary key,
	address varchar(255) not null,
	email varchar(255) not null,
	name varchar(255) not null,
	password varchar(255) not null,
	telNumber varchar(255) not null,
	username varchar(255) not null,
	enabled bit not null
)
;

alter table authorities
	add constraint FKk91upmbueyim93v469wj7b2qh
foreign key (user_id) references tenders_db.users (id)
;

alter table participants
	add constraint FKghixrahoj1s8cloinfx8lyeqa
foreign key (user_id) references tenders_db.users (id)
;

alter table projects
	add constraint FK5t81yf4oxm1m4at389l9na0fy
foreign key (customer_id) references tenders_db.users (id)
;

