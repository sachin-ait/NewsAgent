use newsagent;
drop table if exists Customer;
CREATE TABLE if not exists Customer (
    CustID int NOT NULL AUTO_INCREMENT,
    Name varchar(25),
    Address varchar(25),
    PhoneNumber varchar(11),
    Payment int,
    Area varchar(25),    
    PRIMARY KEY (CustID)
);


INSERT INTO Customer (CustId,Name,Address,PhoneNumber,Payment,Area) VALUES (1,'Van', '03 Willow Park',0885667095,50,'willow park');
INSERT INTO Customer (CustId,Name,Address,PhoneNumber,Payment,Area) VALUES (2,'John', 'International Office, Ait',0885667097,50,'ait');
INSERT INTO Customer (CustId,Name,Address,PhoneNumber,Payment,Area) VALUES (3,'Mike', 'Apartment 1, Croi Oige',0885667098,50,'croi oige');
INSERT INTO Customer (CustId,Name,Address,PhoneNumber,Payment,Area) VALUES (4,'Peter', '2, GarryCastle',0885667099,50,'athlone town');
