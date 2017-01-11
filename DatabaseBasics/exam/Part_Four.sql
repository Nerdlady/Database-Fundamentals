-- 4.1
DELIMITER $$
CREATE PROCEDURE usp_submit_review(customer_id INT,review_content VARCHAR(255),review_grade INT(10),airline_name VARCHAR(30))
BEGIN
		DECLARE airline_id INT;
		DECLARE id INT;
		SET airline_id := (SELECT a.airline_id FROM airlines AS a WHERE a.airline_name = airline_name);	
		SET id := (SELECT MAX(review_id) FROM customer_reviews);
		
		IF	(airline_id IS NULL OR airline_id < 1) THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT  = 'Airline does not exist.';
		END IF;
		
		INSERT INTO customer_reviews
		VALUES(id + 1,review_content,review_grade,airline_id,customer_id);
END $$

DELIMITER ;
CALL usp_submit_review(1,"DA DA",5,"BLQBLQ");
CALL usp_submit_review(1,"DADA",5,"Sofia Air");

-- 4.2
DELIMITER $$
CREATE PROCEDURE usp_purchase_ticket(customer_id INT,flight_id INT,	ticket_price DECIMAL ,class VARCHAR(6),seat VARCHAR(5))
BEGIN
	DECLARE customer_balance DECIMAL;
	DECLARE id INT;
	SET customer_balance := (SELECT cba.balance FROM customer_bank_accounts AS cba WHERE customer_id = cba.customer_id);	
	SET id := (SELECT MAX(ticket_id) FROM tickets);
	
	IF	(ticket_price > customer_balance) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT  = 'Insufficient bank account balance for ticket purchase.';
	END IF;
	
	INSERT INTO tickets
	VALUES (id +1,ticket_price,class,seat,customer_id,flight_id);
	
	UPDATE customer_bank_accounts AS cba
	SET cba.balance = cba.balance - ticket_price
	WHERE customer_id = cba.customer_id;
END $$

DELIMITER ;
CALL usp_purchase_ticket(1,1,3000,"First","15-A");
CALL usp_purchase_ticket(1,1,569.23,"First","15-A");