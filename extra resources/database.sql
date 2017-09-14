--------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------																						-------------------------
-------------------------										MySQL Database									-------------------------
-------------------------																						-------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------

--Structure:

CREATE TABLE users (
  user_id int(11) NOT NULL,
  fullname varchar(500) NOT NULL,
  email varchar(100) NOT NULL,
  username varchar(200) NOT NULL,
  password varchar(100) NOT NULL,
  created_at datetime DEFAULT NULL,
  updated_at datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE users
  ADD PRIMARY KEY (user_id),
  ADD UNIQUE KEY username (username);
  
ALTER TABLE users
  MODIFY user_id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=200;
  

CREATE TABLE todo_list (
  todo_id int(10) UNSIGNED NOT NULL,
  todo_title varchar(30) NOT NULL,
  todo_desc varchar(300) NOT NULL,
  todo_owner_id int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE todo_list
  ADD PRIMARY KEY (todo_id),
  ADD KEY FK_owner (todo_owner_id);
  
ALTER TABLE todo_list
  MODIFY todo_id int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=200;
  
ALTER TABLE todo_list
 ADD CONSTRAINT FK_owner FOREIGN KEY (todo_owner_id) REFERENCES users (user_id);
 
 ALTER TABLE todo_list 
	ADD CONSTRAINT todo_owner_forign_key
	FOREIGN KEY (todo_owner_id)
	REFERENCES users (user_id);
	
	
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- Data:
	
