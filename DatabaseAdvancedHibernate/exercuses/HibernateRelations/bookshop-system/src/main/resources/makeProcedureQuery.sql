DELIMITER $$
CREATE PROCEDURE up_get_number_of_book_by_author(IN fisrt_name VARCHAR(255) , IN last_name VARCHAR(255), OUT count INT)
BEGIN
			SELECT COUNT(b.id) FROM authors AS a
			INNER JOIN books AS b
			ON b.author_id = a.author_id
			WHERE a.first_name = fisrt_name
			AND a.last_name = last_name INTO count;
END$$

