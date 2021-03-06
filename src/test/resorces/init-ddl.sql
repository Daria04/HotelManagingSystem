--DDL

CREATE TABLE USERS
(
 ID BIGSERIAL NOT NULL PRIMARY KEY,
 NAME VARCHAR (255),
 LASTNAME VARCHAR (255),
 PASSPORTDATA BIGINT NOT NULL,
 EMAIL VARCHAR (120),
 PHONENUMBER BIGINT NOT NULL
);

CREATE TABLE APARTMENT
(
 ID BIGSERIAL NOT NULL PRIMARY KEY,
 COST INT NOT NULL,
 COMFORT VARCHAR(255),
 ACCOMODATION VARCHAR(255),
 ROOMNUMBER INT NOT NULL
);

CREATE TABLE ORDERS
(
 ID BIGSERIAL NOT NULL PRIMARY KEY,
 USERID INT NOT NULL,
 ORDERSAPTID INT,
 DATEIN DATE NOT NULL,
 DATEOUT DATE NOT NULL,
 FOREIGN KEY (USERID) REFERENCES USERS(ID),
 FOREIGN KEY (ORDERSAPTID) REFERENCES APARTMENT(ID)
);

COMMIT;
