## Add the sql commands here (related to creting of user and database ,
  should be run under the root user)

#create newsagent user
CREATE USER IF NOT EXISTS  'agent'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'agent'@'%';
FLUSH PRIVILEGES;

create database IF NOT EXISTS newsagent;
use newsagent;
