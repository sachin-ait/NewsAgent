DROP TABLE IF EXISTS OrderReport;
CREATE TABLE OrderReport(
    OrderId int NOT NULL AUTO_INCREMENT,
    CustName varchar(30),
    CustAddress varchar(30),
    PublicationName varchar(30),
    DeliveryDate varchar(12), 
    Status varchar(3),
    DeliveryOrderId int,   
    PRIMARY KEY (OrderId)
);

INSERT INTO OrderReport(OrderId,CustName,PublicationName,DeliveryDate,Status,DeliveryOrderId) VALUES (1,'Jack','Starry', '13/2/2021','UPD',1);
INSERT INTO OrderReport(OrderId,CustName,PublicationName,DeliveryDate,Status,DeliveryOrderId) VALUES (2,'Sean','Starry', '15/3/2020','DEL',2);