CREATE TABLE user(user_id varchar(100) NOT NULL, name varchar(250) NOT NULL, password varchar(255) NOT NULL, PRIMARY KEY(user_id));
CREATE TABLE customer (customer_id varchar(100) NOT NULL, name varchar(250) NOT NULL, email varchar(250), PRIMARY KEY(customer_id));

INSERT INTO user values ('blahuser', 'Test User', '3b63db8f2742a9222df00da7b80d088b');
INSERT INTO customer values ('nmishra', 'nitin mishra', 'nmishra@email.com');