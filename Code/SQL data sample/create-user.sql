DROP user 'demo'@'localhost';
FLUSH privileges;
CREATE USER 'demo'@'localhost' IDENTIFIED BY 'demo';
GRANT ALL PRIVILEGES ON * . * TO 'demo'@'localhost';
