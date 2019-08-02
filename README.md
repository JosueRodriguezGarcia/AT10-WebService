# AT10-WebService
# DATABASE INSTALLATION TUTORIAL

# PREREQUIREMENTS

MYSQL DATABASE Ver 10.3.16

# INSTALL
# STEP 1.-
Download Xamp defauld setup instalations.
https://www.apachefriends.org/xampp-files/7.3.7/xampp-windows-x64-7.3.7-1-VC15-installer.exe
# STEP 2.-
Go to the file: “C:\xampp2\mysql\bin” and download “Script.sql” Them you 
can found in the following direcctions.
\\AT10-WebService\src\main\java\com\fundation\webservice\database\Script.sql
# STEP 3.-
Execute CMD.exe
Got to directory: “C:\xampp2\mysql\bin”
Them execute the following command “mysql -u root -p mysql < Script.sql”
If apperar a messague to authentications press enter.
#STEP 4.-
EXECUTE CMD.
1.- Go to the file:
“C:\xampp2\mysql\bin”
2.- Execute the following command.
“mysql -u root –p” to enter mysql console.
3.- If show a message to need password press enter.
4.-Them in side Sql  execute the next command.
CREATE USER 'pepe'@'localhost' IDENTIFIED BY '!Contrasena123';
5.-Give permission for testing purposes we assign all.
GRANT ALL PRIVILEGES ON *.* TO 'pepe'@'localhost' IDENTIFIED BY 
'!Contrasena123';
6.-Add the following command.
GRANT SELECT ON *.* TO 'pepe'@'localhost';
7.- The last step put the following zone time.
SET GLOBAL time_zone = '-3:00';

