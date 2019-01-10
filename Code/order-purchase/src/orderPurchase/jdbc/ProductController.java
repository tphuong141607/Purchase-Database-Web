package orderPurchase.jdbc;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ProductController {

	private List<Product> products;
	private ProductDbUtil productDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public ProductController() throws Exception {
		products = new ArrayList<>();
		
		productDbUtil = ProductDbUtil.getInstance();
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void loadProducts() {

		logger.info("Loading product");
		
		products.clear();

		try {
			
			// get all products from database
			products = productDbUtil.getProduct();
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading products", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
	public String addProduct(Product theProduct) {

		logger.info("Adding product: " + theProduct);

		try {
			
			// add product to the database
			productDbUtil.addProduct(theProduct);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding products", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}
		
		return "list-customers?faces-redirect=true";
	}
	
	public String loadProduct(int productId) {
		
		logger.info("loading product: " + productId);
		
		try {
			// get product from database
			
			Product theProduct = productDbUtil.getProduct(productId);
			
			// put in the request attribute ... so we can use it on the form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("product", theProduct);	
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading product id:" + productId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-product-form.xhtml";
	}	

	public String updateProduct(Product theProduct) {

		logger.info("updating product: " + theProduct);
		
		try {
			
			// update product in the database
			productDbUtil.updateProduct(theProduct);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating product: " + theProduct, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-customers?faces-redirect=true";		
	}
	
	
	public String deleteProduct(int productId) {

		logger.info("Deleting product id: " + productId);
		
		try {

			// delete the product from the database
			productDbUtil.deleteProduct(productId);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting product id: " + productId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-customers-form";	
	}	
	
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Message " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
