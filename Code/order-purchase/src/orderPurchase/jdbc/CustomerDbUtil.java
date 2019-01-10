package orderPurchase.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CustomerDbUtil {

	private static CustomerDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/order_purchase";
	
	public static CustomerDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new CustomerDbUtil();
		}
		
		return instance;
	}
	
	private CustomerDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<Customer> getCustomer() throws Exception {

		List<Customer> customers = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from customer order by last_name";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				int id = myRs.getInt("customer_id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String phone_num = myRs.getString("phone_num");
				String address = myRs.getString("address");
				String email = myRs.getString("email");

				// create new customer object
				Customer tempCustomer = new Customer(id, firstName, lastName,
						phone_num, address,email);
				
				// add it to the list of customers
				customers.add(tempCustomer);
			}
			
			return customers;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into customer (first_name, last_name, phone_Num, address, email) values (?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theCustomer.getFirstName());
			myStmt.setString(2, theCustomer.getLastName());
			myStmt.setString(3, theCustomer.getPhoneNum());
			myStmt.setString(4, theCustomer.getAddress());
			myStmt.setString(5, theCustomer.getEmail());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public Customer getCustomer(int customerId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from customer where customer_id=?";

			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, customerId);
			
			myRs = myStmt.executeQuery();

			Customer theCustomer = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("customer_id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String phone_num = myRs.getString("phone_num");
				String address = myRs.getString("address");
				String email = myRs.getString("email");

				// create new customer object
				
				theCustomer = new Customer(id, firstName, lastName,
						phone_num, address,email);
			}
			else {
				throw new Exception("Could not find customer id: " + customerId);
			}

			return theCustomer;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update customer "
						+ " set first_name=?, last_name=?, phone_num=?, address=?, email=?"
						+ " where customer_id=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, theCustomer.getFirstName());
			myStmt.setString(2, theCustomer.getLastName());
			myStmt.setString(3, theCustomer.getPhoneNum());
			myStmt.setString(4, theCustomer.getAddress());
			myStmt.setString(5, theCustomer.getEmail());
			myStmt.setInt(6, theCustomer.getId());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteCustomer(int customerId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from customer where customer_id=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, customerId);
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}		
	}	
	
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();
		
		return theConn;
	}
	
	private void close(Connection theConn, Statement theStmt) {
		close(theConn, theStmt, null);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}	
}
