CREATE TABLE voyage(
ID INT NOT NULL,
Flight_number VARCHAR(10),
Arrival_port VARCHAR(10),
Departure_port VARCHAR(10),
PRIMARY KEY (Flight_number)
);

CREATE TABLE airplanes(
Voyage_flightNumber VARCHAR(10) NOT NULL,
Name VARCHAR(10),
Seats_capacity INT,
FOREIGN KEY(Voyage_flightNumber) REFERENCES voyage(Flight_number),
PRIMARY KEY(Voyage_flightNumber)
);

CREATE TABLE passengers(
ID INT NOT NULL AUTO_INCREMENT,
First_name VARCHAR(20),
Last_name VARCHAR(20),
Nationality VARCHAR(10),
Passport VARCHAR(10),
DOB VARCHAR(10),
SEX VARCHAR(10),
PRIMARY KEY(ID)
);
CREATE TABLE ticket(
ID INT NOT NULL AUTO_INCREMENT,
Voyage_flightNumber VARCHAR(10),
Class VARCHAR(10),
Passenger_id INT NOT NULL,
Seat_number VARCHAR(5),
PRIMARY KEY (ID),
FOREIGN KEY (Voyage_flightNumber) REFERENCES voyage(Flight_number),
FOREIGN KEY (Passenger_id) REFERENCES passengers(ID)
);
CREATE TABLE departure(
Voyage_flightNumber VARCHAR(10) NOT NULL,
Date VARCHAR(20),
Time VARCHAR(20),
Terminal VARCHAR(10),
Flight_status VARCHAR(10),
Gate VARCHAR(10),
PRIMARY KEY(Voyage_flightNumber),
FOREIGN KEY(Voyage_flightNumber) REFERENCES voyage(Flight_number)
);

CREATE TABLE arrival(
Voyage_flightNumber VARCHAR(10) NOT NULL,
Date VARCHAR(20),
Time VARCHAR(20),
Terminal VARCHAR(10),
Flight_status VARCHAR(10),
Gate VARCHAR(10),
PRIMARY KEY(Voyage_flightNumber),
FOREIGN KEY(Voyage_flightNumber) REFERENCES voyage(Flight_number)
);
