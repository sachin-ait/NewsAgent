DROP TABLE if exists DeliveryOrder;
CREATE TABLE DeliveryOrder(
    DeliveryOrderId int NOT NULL AUTO_INCREMENT,
    CustName varchar(30),
    PublicationName varchar(30),
    DeliveryDate varchar(12),     
    PRIMARY KEY (DeliveryOrderId)
);

INSERT INTO DeliveryOrder(DeliveryOrderId,CustName,PublicationName,DeliveryDate) VALUES (1,'Jack','Starry', '13/2/2021');