
USE newsagent;

DROP TABLE IF EXISTS DeliveryInvoices;

CREATE TABLE deliveryInvoices (
	Invoice_ID int NOT NULL AUTO_INCREMENT,
    Invoice_Date VARCHAR(60) NOT NULL,
	Success_Delivery int(4),
    Failed_Delivery int(4),
	Pay_Due DECIMAL(8),
    primary key (Invoice_ID, Invoice_Date)
    );
/*
DROP TRIGGER IF EXISTS populate_invoice;
DELIMITER //
CREATE TRIGGER populate_invoice
    AFTER INSERT on deliveryInvoices
    FOR EACH ROW
BEGIN
    UPDATE deliveryInvoices SET 
    Success_Delivery = ( Select count(DeliveryDockets.Order_success="Success") FROM DeliveryDockets where DeliveryDockets.Docket_date = new.Invoice_Date),
    Failed_Delivery = ( Select count(DeliveryDockets.Order_success="Failed") FROM DeliveryDockets where DeliveryDockets.Docket_date = new.Invoice_Date),
    Pay_Due =  ( select sum(DeliveryDockets.paysum) FROM DeliveryDockets where DeliveryDockets.Docket_Date = new.Invoice_Date)
    where Invoice_date = new.Invoice_Date;
END//
*/

/* 
An input into deliveryInvoices may come from a trigger that will get entities from DeliveryDockets
The User will create an entity in Delivery Invoices with the Date of the invoice to make, 
then the trigger will gather entities under the same date from the Delivery Docket table and populate the new entry in DeliveryInvoices

For testing and demonstrating purposes a different method will be constructed.
*/

Drop procedure if exists create_Invoice;

DELIMITER //
CREATE procedure create_Invoice(
in inv_date VARCHAR(60)
)
begin
	INSERT INTO deliveryInvoices(Invoice_Date) VALUES (inv_date);
    end
// DELIMITER ;

Drop procedure if exists create_Manual;

DELIMITER //
CREATE procedure create_Manual(
in inv_date VARCHAR(60),
in Success_amount int(4),
in Failed_amount int(4),
in Pay_sum decimal(8)
)
begin
	INSERT INTO deliveryInvoices(Invoice_Date) VALUES (inv_date);
    UPDATE deliveryInvoices SET 
    Success_Delivery= Success_amount, 
    Failed_Delivery= Failed_amount, 
    Pay_Due= Pay_sum
    where Invoice_Date = inv_date;
end
// DELIMITER ;

Drop procedure if exists delete_Invoice;
DELIMITER //

CREATE procedure delete_Invoice (
in inv_ID VARCHAR(60)
)
begin
	DELETE FROM deliveryInvoices where Invoice_ID = inv_ID;
end
// DELIMITER ;

Drop procedure if exists read_Invoices;
DELIMITER //
CREATE procedure read_Invoices()
begin
	Select * from deliveryAgents;
end
// DELIMITER ;

Drop procedure if exists update_Invoice;

DELIMITER //
CREATE procedure update_Invoice (
in inv_ID int(4),
in Success_amount int(4), 
in Failed_amount int(4), 
in Pay_sum decimal(8) 
)
begin
	UPDATE deliveryInvoices SET 
    Success_Delivery = Success_amount,
    Failed_Delivery = Failed_amount,
    Pay_Due = Pay_sum
    where Invoice_ID = inv_ID;
end
// DELIMITER ;





