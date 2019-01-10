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

public class ProductCategoryDbUtil {

	private static ProductCategoryDbUtil instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/order_purchase";
	
	public static ProductCategoryDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new ProductCategoryDbUtil();
		}
		
		return instance;
	}
	
	private ProductCategoryDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
		return theDataSource;
	}
		
	public List<ProductCategory> getProductCategory() throws Exception {

		List<ProductCategory> productCategories = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from productCategory order by category_name";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				int id = myRs.getInt("category_id");
				String categoryName = myRs.getString("category_name");

				// create new customer object
				ProductCategory tempCategory = new ProductCategory(id,categoryName);
				
				// add it to the list of categories
				productCategories.add(tempCategory);
			}
			
			return productCategories;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	public void addProductCategory(ProductCategory theCategory) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();
		
			String sql = "insert into productCategory(category_name) values (?)";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, theCategory.getCategoryName());
			
			myStmt.execute();			
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public ProductCategory getProductCategory(int categoryId) throws Exception {
	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();

			String sql = "select * from productCategory where category_id=?";

			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, categoryId);
			
			myRs = myStmt.executeQuery();

			ProductCategory theCategory = null;
			
			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("category_id");
				String categoryName = myRs.getString("category_name");

				// create new customer object
				
				theCategory = new ProductCategory(id, categoryName);
			}
			else {
				throw new Exception("Could not find customer id: " + categoryId);
			}

			return theCategory;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
	
	public void updateProductCategory(ProductCategory theCategory) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "update productCategory "
						+ " set category_name=?"
						+ " where category_id=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, theCategory.getCategoryName());
			myStmt.setInt(2, theCategory.getId());
			
			myStmt.execute();
		}
		finally {
			close (myConn, myStmt);
		}
		
	}
	
	public void deleteProductCategory(int categoryId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from productCategory where category_id=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, categoryId);
			
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
