-- 2.1
INSERT INTO towns(town_id, town_name)
VALUES
(1, 'Sofia'),
(2, 'Moscow'),
(3, 'Los Angeles'),
(4, 'Athene'),
(5, 'New York');

INSERT INTO airports(airport_id, airport_name, town_id)
VALUES
(1, 'Sofia International Airport', 1),
(2, 'New York Airport', 5),
(3, 'Royals Airport', 1),
(4, 'Moscow Central Airport', 2);

INSERT INTO airlines(airline_id, airline_name, nationality, rating)
VALUES
(1, 'Royal Airline', 'Bulgarian', 200),
(2, 'Russia Airlines', 'Russian', 150),
(3, 'USA Airlines', 'American', 100),
(4, 'Dubai Airlines', 'Arabian', 149),
(5, 'South African Airlines', 'African', 50),
(6, 'Sofia Air', 'Bulgarian', 199),
(7, 'Bad Airlines', 'Bad', 10);

INSERT INTO customers(customer_id, first_name, last_name, date_of_birth, gender, home_town_id)
VALUES
(1, 'Cassidy', 'Isacc', '1997-10-20', 'F', 1),
(2, 'Jonathan', 'Half', '1983-03-22', 'M', 2),
(3, 'Zack', 'Cody', '1989-08-08', 'M', 4),
(4, 'Joseph', 'Priboi', '1950-01-01', 'M', 5),
(5, 'Ivy', 'Indigo', '1993-12-31', 'F', 1);

 

INSERT INTO flights 
VALUES (1,STR_TO_DATE("2016-10-13 06:00 AM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2016-10-13 10:00 AM","%Y-%m-%d %h:%m %p"),"Delayed",1,	4,	1), 
(2,STR_TO_DATE("2016-10-12 12:00 PM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2016-10-12 12:01 PM","%Y-%m-%d %h:%m %p"),	"Departing",1	,3,2),
(3	,STR_TO_DATE("2016-10-14 03:00 PM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2016-10-20 04:00 AM","%Y-%m-%d %h:%m %p"),"Delayed",4,2,	4),
(4, STR_TO_DATE("2016-10-12 01:24 PM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2016-10-12 4:31 PM","%Y-%m-%d %h:%m %p"),"Departing",3,1,	3),
(5	,STR_TO_DATE("2016-10-12 08:11 AM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2016-10-12 11:22 PM","%Y-%m-%d %h:%m %p"),"Departing",	4,	1,	1),
(6,STR_TO_DATE("1995-06-21 12:30 PM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("1995-06-22 08:30 PM","%Y-%m-%d %h:%m %p"),"Arrived",2,	3,	5),
(7,STR_TO_DATE("2016-10-12 11:34 PM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2016-10-13 03:00 AM","%Y-%m-%d %h:%m %p"),	"Departing",2,	4,	2),
(8,STR_TO_DATE("2016-11-11 01:00 PM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2016-11-12 10:00 PM","%Y-%m-%d %h:%m %p"),"Delayed",	4,	3,	1),
(9,STR_TO_DATE("2015-10-01 12:00 PM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2015-12-01 01:00 AM","%Y-%m-%d %h:%m %p"),	"Arrived",1,	2,	1),
(10,STR_TO_DATE("2016-10-12 07:30 PM","%Y-%m-%d %h:%m %p"),STR_TO_DATE("2016-10-13 12:30 PM","%Y-%m-%d %h:%m %p"),"Departing",2,	1,	7);

INSERT INTO tickets
VALUES (1,	3000.00,"First","233-A",3,	8),
(2,	1799.90,	"Second",	"123-D",	1	,1),
(3,	1200.50,	"Second",	"12-Z",	2	,5),
(4,	410.68,	"Third",	"45-Q"	,2	,8),
(5,	560.00,	"Third","201-R"	,4	,6),
(6,	2100.00,	"Second",	"13-T",	1,	9),
(7,	5500.00,	"First","98-O",	2,	7);


-- 2.2 
UPDATE flights
SET airline_id = 1
WHERE `status` = 'Arrived';

-- 2.3
UPDATE tickets
SET  price= price * 1.5
WHERE flight_id IN (SELECT flight_id FROM flights
							WHERE airline_id IN
													(SELECT airline_id FROM airlines
														WHERE rating = (SELECT MAX(rating) FROM airlines)))


-- 2.4
CREATE TABLE customer_reviews
(review_id INT PRIMARY KEY,
review_content VARCHAR(255) NOT NULL,
review_grade INT(10),
airline_id INT,
customer_id INT,
FOREIGN KEY (airline_id) REFERENCES airlines(airline_id),
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE customer_bank_accounts
(account_id INT PRIMARY KEY,
account_number VARCHAR(10) NOT NULL UNIQUE,
balance DECIMAL(10,2) NOT NULL,
customer_id INT,
FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- 2.5
INSERT INTO customer_reviews
VALUES (1,"Me is very happy. Me likey this airline. Me good.",	10,1,	1),
(2,"Ja, Ja, Ja... Ja, Gut, Gut, Ja Gut! Sehr Gut!",	10,	1,	4),
(3,	"Meh..."	,5,	4,	3),
(4,	"Well Ive seen better, but Ive certainly seen a lot worse...",	7,	3,	5);

INSERT INTO customer_bank_accounts
VALUES (1,	"123456790",	2569.23,	1),
(2,"18ABC23672",14004568.23,	2),
(3,	"F0RG0100N3",	19345.20	,5);

