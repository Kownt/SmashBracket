To compile:
mvn package
To run:
java -jar target/SmashBracket-1.0-SNAPSHOT.war

To do both:
mvn package && java -jar target/SmashBracket-1.0-SNAPSHOT.war

To set up mysql database in dev install mysql then start the server on port 3306
	mysql.server start --port=3306
set root password to 'testing123$'
	mysqladmin -u root password 'testing123$'
use mysql to login as root and create the smashapp database
	mysql --user=root --password=testing123$
	CREATE DATABASE 'smashapp'
	source smashTables.sql
	
