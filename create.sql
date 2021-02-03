## Add the sql commands here

#create newsagent user
CREATE USER 'agent'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'agent'@'%';
FLUSH PRIVILEGES;

create database newsagent;
use newsagent;
