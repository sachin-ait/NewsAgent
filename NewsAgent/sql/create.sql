## Add the sql commands here (related to creting of user and database ,
##  should be run under the root user)

#create newsagent user
DROP USER 'agent';
CREATE USER  'agent'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'agent'@'localhost';
FLUSH PRIVILEGES;

create database IF NOT EXISTS newsagent;
use newsagent;
