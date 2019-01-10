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

public class ProductDbUtil {

	private static ProductDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/order_purchase";
	
	public static ProductDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new ProductDbUtil();
		}
		
		return instance;
	}
	
	private ProductDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<Product> getProduct() throws Exception {

		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from product order by product_name";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				int id = myRs.getInt("product_id");
				String name = myRs.getString("product_name");
				String description = myRs.getString("description");
				double price = myRs.getDouble("product_price");
				int categoryId = myRs.getInt("category_id");

				// create new product object
				Product tempProduct = new Product(id, name, description,
						price, categoryId);
		
				
				// add it to the list of products
				products.add(tempProduct);
			}
			
			return products;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addProduct(Product theProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "insert into product (product_name, description, product_price, category_id) values (?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, theProduct.getName());
			myStmt.setString(2, theProduct.getDescription());
			myStmt.setDouble(3, theProduct.getPrice());
			myStmt.setInt(4, theProduct.getCategoryId());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public Product getProduct(int productId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from product where product_id=?";

			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, productId);
			
			myRs = myStmt.executeQuery();

			Product theProduct = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("product_id");
				String name = myRs.getString("product_name");
				String description = myRs.getString("description");
				double price = myRs.getDouble("product_price");
				int categoryId = myRs.getInt("category_id");

				// create new product object
				
				theProduct = new Product(id, name, description, price, categoryId);
			}
			else {
				throw new Exception("Could not find product id: " + productId);
			}

			return theProduct;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateProduct(Product theProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update product "
						+ " set product_name=?, description=?, product_price=?, category_id=?"
						+ " where product_id=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, theProduct.getName());
			myStmt.setString(2, theProduct.getDescription());
			myStmt.setDouble(3, theProduct.getPrice());
			myStmt.setInt(4, theProduct.getCategoryId());
			myStmt.setInt(5, theProduct.getId());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteProduct(int productId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from product where product_id=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, productId);
			
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
