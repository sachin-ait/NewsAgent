USE newsagent;
DROP TABLE if exists DeliveryOrder;
CREATE TABLE DeliveryOrder(
    DeliveryOrderId int NOT NULL AUTO_INCREMENT,
    CustID int(4),
    CustName varchar(30),
    CustAddress varchar(30),
    PublicationName varchar(30),
    PAmount int(4),
    DeliveryDate varchar(18),     
    PRIMARY KEY (DeliveryOrderId)
);

insert into DeliveryOrder values(1, 1, "Van", '03 Willow Park', "Fox News", 2, "April");
insert into DeliveryOrder values(2, 2, "John", 'International Office, Ait', "Digits Now", 2, "April");
insert into DeliveryOrder values(3, 3, "Mike", 'Apartment 1, Croi Oige', "IEEE Explore", 1, "April");