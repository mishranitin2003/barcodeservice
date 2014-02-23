-- Spring security tables
CREATE TABLE users(username varchar(100) NOT NULL,
				   first_name varchar(250) NOT NULL,
				   middle_name varchar(100),
				   surname varchar(250),
				   password varchar(100) NOT NULL, 
				   enabled boolean not null default false);
				   
CREATE TABLE authorities (username varchar(50) not null,
      					  authority varchar(50) not null,
      					  constraint fk_authorities_users foreign key(username) references users(username));
CREATE UNIQUE INDEX ix_auth_username on authorities (username,authority);

-- Application Tables
CREATE TABLE customer(customer_id varchar(100) NOT NULL, name varchar(250) NOT NULL, email varchar(250), PRIMARY KEY(customer_id));

--- Users
INSERT INTO users values ('nmishra', 'Nitin', null, 'Mishra', '$2a$10$7gYm2/MbFNnzRQAOpUDAi.G52cVDlDnrXdSFe3jgyiJhRNmipgn4a', true);
INSERT INTO authorities values ('nmishra', 'ROLE_ADMIN');

--- Customers
INSERT INTO customer values ('tescos123', 'Tesco', 'tescos@tesco.co.uk');
INSERT INTO customer values ('argos123', 'Argos', 'argoss@argos.co.uk');
INSERT INTO customer values ('sainsburys123', 'Sainsburys', 'sainsburyss@sainsburys.co.uk');