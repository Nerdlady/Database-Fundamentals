-- 3.1
SELECT ticket_id,price,class,seat FROM tickets
ORDER BY ticket_id ASC

-- 3.2
SELECT customer_id,CONCAT(first_name," ",last_name),gender FROM customers
ORDER BY CONCAT(first_name," ",last_name) ASC,customer_id ASC 

-- 3.3
SELECT flight_id,departure_time,arrival_time FROM flights
WHERE `status` = 'Delayed'
ORDER BY flight_id ASC 

-- 3.4 
SELECT DISTINCT a.airline_id,a.airline_name,a.rating FROM airlines AS a
INNER JOIN flights AS f
ON f.airline_id = a.airline_id
ORDER BY a.rating DESC, a.airline_id ASC
LIMIT 5

-- 3.5
SELECT t.ticket_id,a.airport_name AS destination,CONCAT(c.first_name," ",c.last_name) AS customer_name
FROM tickets AS t
INNER JOIN flights AS f
   ON t.flight_id = f.flight_id
INNER JOIN airports AS a
   ON f.destination_airport_id = a.airport_id
INNER JOIN customers AS c
   ON c.customer_id = t.customer_id
WHERE t.price < 5000
ORDER BY t.ticket_id ASC

-- 3.6
SELECT c.customer_id,CONCAT(c.first_name," ",c.last_name) AS full_name,town.town_name AS 	home_town
FROM customers AS c
INNER JOIN towns AS town
ON town.town_id = c.home_town_id
INNER JOIN airports AS air
ON air.town_id = c.home_town_id
INNER JOIN flights AS f
ON f.origin_airport_id = air.airport_id
-- GROUP BY c.customer_id
ORDER BY customer_id ASC 

-- 3.7

SELECT c.customer_id,CONCAT(c.first_name," ",c.last_name) AS full_name,2016 - YEAR(c.date_of_birth) AS age
FROM customers AS c
INNER JOIN tickets AS t
ON t.customer_id = c.customer_id
INNER JOIN flights AS f
ON f.flight_id = t.flight_id
WHERE f.`status` = "Departing"
ORDER BY age ASC,c.customer_id ASC;

-- 3.8

SELECT c.customer_id,CONCAT(c.first_name," ",c.last_name) AS full_name,t.price,a.airport_name AS destination
FROM customers AS c
INNER JOIN tickets AS t
ON t.customer_id = c.customer_id
INNER JOIN flights AS f
ON f.flight_id = t.flight_id
INNER JOIN airports AS a
ON a.airport_id = f.destination_airport_id
ORDER BY  t.price DESC, c.customer_id ASC
LIMIT 3;
 
-- 3.9 

SELECT f.flight_id,f.departure_time,f.arrival_time,ao.airport_name AS origin, ad.airport_name AS destination
FROM flights AS f
INNER JOIN airports AS ao
ON ao.airport_id = f.origin_airport_id
INNER JOIN airports as ad
ON ad.airport_id= f.destination_airport_id
WHERE f.`status` = 'Departing'
ORDER BY f.departure_time ASC,f.flight_id ASC
LIMIT 5;

-- 3.10

SELECT c.customer_id,CONCAT(c.first_name," ",c.last_name) AS full_name,2016 - YEAR(c.date_of_birth) AS age
FROM customers AS c
INNER JOIN tickets AS t
ON t.customer_id = c.customer_id
INNER JOIN flights AS f
ON f.flight_id = t.flight_id
WHERE f.`status` = 'Arrived'
AND 2016 - YEAR(c.date_of_birth) < 21
ORDER BY age DESC ,c.customer_id ASC;

-- 3.11

SELECT air.airport_id,air.airport_name,COUNT(t.customer_id) AS passengers
FROM airports AS air
INNER JOIN flights AS fo
ON fo.origin_airport_id = air.airport_id
INNER JOIN tickets AS t
ON t.flight_id = fo.flight_id
WHERE fo.`status` = 'Departing'
GROUP BY air.airport_id

