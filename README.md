# YesCode-Purchase-Web-Database

This 
## What is it?

Similar to the iOS Calculator App, this program contains 5 basic mathematical operations including addition, subtraction, multiplication, division, and negation. The application is built using Java, JavaFX and SceneBuilder. 

## Example

#### Customer Table
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Customer%20Table.png" width="80%">

Let's try to **add** a new customer to the Customer table, we will get into this page:

<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Add%20Customer.png" width="70%">

If some of the entries are left empty, users will be notified to fill in the required blanks

<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Constraint%20Add%20Customer.png" width="70%">

#### Product Category Table
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Product%20Table.png" width="80%">

Let's try to **delete** the `Electronics category` from the Product Category Table.

Because I set a Referential Integrity constraint between table Product and table Product Category, when I delete the Electronics category, all products belong to this category will also be removed from the Database (On Cascade)

<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Delete%20Electronic%20Category.png" width="80%">

#### Product Table
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Product%20Category%20Table.png"  width="80%">

Product Table after deleting the `Electronics category`
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Product%20Table%20after%20Catedeletion.png"  width="80%">


## How to run this program on your computer locally? (MacOS)

### 1. Download the project and save to your desired directory

### 2. Setup Eclipse

1. Download the JDK [Java SE Development Kit](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) (only if you don't have it)

2. Download and install Eclipse IDE for **Java EE Developers** [here](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers)

3. Open Esclipe

4. **Select** File --> Import --> Existing Projects into Workspace

5. In the `Select root directory`, **browse** for the `YesCode-Purchase-Web-Database` file

6. Click **open** then **finish**.
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Import%20Projects.png" width="80%">

### 3. Connect Eclipse to TomCat

1. Click on the servers tab

2. Click on `No servers are available. Click this link to create a new server...`

3. Select `Apache`--> `Tomcat v8.5 Server` --> `Next`
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Connect%20Tomcat.png" width="80%">

4. Browse `YesCode-Purchase-Web-Database/Servers/apache-tomcat-8.5.35` --> Click `Open` 
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Connect%20Tomcat2.png" width="80%">

### 4. Set Up Local MySQL Database

1. Download [mySQL](https://dev.mysql.com/downloads/mysql/) 

2. Download [mySQL workbench](https://dev.mysql.com/downloads/workbench/)

#### Create Connection

3. Open mySQL workbench: Click on the `Plus` icon next to MySQL Connections to create a new connection. You can create any name you want for the Connection Name and keep everything else the same. Then click `Ok`.
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Create%20new%20connection.png" width="80%">

#### Create User and Password

4. Open the newly created Connection.

5. Go to `File` --> `Open SQL Script`.

6. Browse `YesCode-Purchase-Web-Database/Code/SQL data sample/create-user.sql` and click Open.
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Set%20up%20SQL%20data.png" width="80%">

7. Run the current SQL file by clicking the `yello lighting` icon.
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Create%20user%20SQL.png" width="80%">

#### Insert sample data and the schema of the database

8. Go to `File` --> `Open SQL Script`.

9. Browse `YesCode-Purchase-Web-Database/Code/SQL data sample/sample data.sql` and click Open.

10. Run the current SQL file by clicking the `yello lighting` icon. If you right click and select refresh, you will see the information of the Database
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Insert%20data%20to%20the%20db.png" width="80%">

### 5. Final Step: 

1. Go back to Eclipse and run your application with the option: `Run on Server`
2. Select `Choose an existing server`
3. Select `Tomcat v8.5 Server at localhost` and `Finish`

This is what you should get
<img src="https://github.com/tphuong141607/YesCode-Purchase-Web-Database/blob/master/Example%20Images/Result.png" width="80%">
