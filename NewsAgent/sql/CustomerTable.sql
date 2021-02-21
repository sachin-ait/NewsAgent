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


INSERT INTO Customer (CustId,Name,Address,PhoneNumber,Payment,Area) VALUES (1,'Van', 'Willow Park',0885667095,50,'Willow Park');
