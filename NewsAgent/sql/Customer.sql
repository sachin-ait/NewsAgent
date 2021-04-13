DROP TABLE Customer;
CREATE TABLE Customer (
    CustID int NOT NULL AUTO_INCREMENT,
    Name varchar(25),
    Address varchar(25),
    PhoneNumber int,
    Payment int,
    Area varchar(25),    
    PRIMARY KEY (CustID),
    FOREIGN KEY (Area) REFERENCES deliveryAgents(Agent_Area)
);


INSERT INTO Customer (CustId,Name,Address,PhoneNumber,Payment,Area) VALUES (1,'Van', 'Willow Park',0885667095,50,'Athlone');