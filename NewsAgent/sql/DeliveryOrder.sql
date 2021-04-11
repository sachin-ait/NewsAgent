USE newsagent;
DROP TABLE if exists DeliveryOrder;
CREATE TABLE DeliveryOrder(
    DeliveryOrderId int NOT NULL AUTO_INCREMENT,
    CustID int(4),
    CustName varchar(30),
    CustAddress varchar(30),
    PublicationName varchar(30),
    DeliveryDate varchar(18),     
    PRIMARY KEY (DeliveryOrderId)
);
/*
INSERT INTO DeliveryOrder(DeliveryOrderId,CustID,CustName,PublicationName,DeliveryDate) VALUES (1,'Jack', 'Athlone','Starry', '13/2/2021');
*/