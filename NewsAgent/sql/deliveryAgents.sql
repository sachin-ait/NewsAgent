USE newsagent;

DROP TABLE IF EXISTS DeliveryAgents;


CREATE TABLE DeliveryAgents (
	Agent_ID int NOT NULL AUTO_INCREMENT,
	Agent_Name VARCHAR(50),
    Agent_Area VARCHAR(60) unique,
	Agent_Pay_Rate DECIMAL(4),
    Agent_Hours_Logged DECIMAL(3),
    primary key (Agent_ID),
    UNIQUE (Agent_Area)
    );

Drop procedure if exists create_Agent;
DELIMITER //
CREATE procedure create_Agent(
in Agent_Name_UI VARCHAR(50), 
in Agent_Area_UI VARCHAR(60), 
in Agent_Pay_Rate_UI DECIMAL(4), 
in Agent_Hours_Logged_UI DECIMAL(3)
)
begin
	INSERT INTO DeliveryAgents(Agent_Name, Agent_Area, Agent_Pay_Rate, Agent_Hours_Logged) VALUES (Agent_Name_UI, Agent_Area_UI, Agent_Pay_Rate_UI, Agent_Hours_Logged_UI);
end
//
DELIMITER ;

Drop procedure if exists delete_Agent;
DELIMITER //
CREATE procedure delete_Agent(
in Agent_ID_UI DECIMAL(3)
)
begin
	DELETE FROM DeliveryAgents where Agent_ID = Agent_ID_UI;
end
//
DELIMITER ;

Drop procedure if exists read_Agent;
DELIMITER //
CREATE procedure read_Agent()
begin
	Select * from deliveryAgents;
end
//
DELIMITER ;

Drop procedure if exists update_Agent;
DELIMITER //
CREATE procedure update_Agent(
in Agent_ID_UI DECIMAL(3),
in Agent_Name_UI VARCHAR(50), 
in Agent_Area_UI VARCHAR(60), 
in Agent_Pay_Rate_UI DECIMAL(4), 
in Agent_Hours_Logged_UI DECIMAL(3)
)
begin
	UPDATE DeliveryAgents SET 
    Agent_Name = Agent_Name_UI,
    Agent_Area = Agent_Area_UI,
    Agent_Pay_Rate = Agent_Pay_Rate_UI,
    Agent_Hours_Logged = Agent_Hours_Logged_UI
    where Agent_ID = Agent_ID_UI;
end
//
DELIMITER ;

call create_Agent("Sachin", "willow park", 20, 20);
call create_Agent("Leon", "croi oige", 20, 20);
call create_Agent("Li", "ait", 20, 20);
call create_Agent("Huang", "athlone town", 20, 20);