-- 5
CREATE TABLE arrived_flights
(flight_id INT PRIMARY KEY,
arrival_time DATETIME NOT NULL,
origin VARCHAR(50) NOT NULL,
destination VARCHAR(50) NOT NULL,
passengers INT NOT NULL
); 


DELIMITER $$
CREATE TRIGGER tr_status_changed
AFTER UPDATE
ON flights
FOR EACH ROW
BEGIN
		
		DECLARE origin VARCHAR(50);
		DECLARE destination VARCHAR(50);
		DECLARE passengers INT;
		
		SET origin := (SELECT airport_name FROM airports WHERE  airport_id = new.origin_airport_id);
		SET destination := (SELECT airport_name FROM airports WHERE airport_id = new.destination_airport_id);
		SET passengers := (SELECT COUNT(*) FROM tickets
									WHERE flight_id = new.flight_id);			
		
		
		IF	(new.`status` = 'Arrived') THEN
			INSERT INTO arrived_flights
			VALUES(new.flight_id,new.arrival_time,origin,destination,passengers);		
		END IF;
END $$


UPDATE flights
SET `status` = 'Arrived'
WHERE flight_id = 1;


UPDATE flights
SET `status` = 'Delayed'
WHERE flight_id = 2;