package orderPurchase.jdbc;

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
public class ProductCategoryController {

	private List<ProductCategory> categories;
	private ProductCategoryDbUtil categoryDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public ProductCategoryController() throws Exception {
		categories = new ArrayList<>();
		
		categoryDbUtil = ProductCategoryDbUtil.getInstance();
	}
	
	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void loadCategories() {

		logger.info("Loading category");
		
		categories.clear();

		try {
			
			// get all categories from database
			categories = categoryDbUtil.getProductCategory();
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading categoriess", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
	public String addProductCategory(ProductCategory theCategory) {

		logger.info("Adding category " + theCategory);

		try {
			
			// add category to the database
			categoryDbUtil.addProductCategory(theCategory);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding categories", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}
		
		return "list-customers?faces-redirect=true";
	}
	
	public String loadCategory(int categoryId) {
		
		logger.info("loading category: " + categoryId);
		
		try {
			// get category from database
			
			ProductCategory theCategory = categoryDbUtil.getProductCategory(categoryId);
			
			// put in the request attribute ... so we can use it on the form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("productCategory", theCategory);	
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading category id:" + categoryId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-category-form.xhtml";
	}	

	public String updateProductCategory(ProductCategory theCategory) {

		logger.info("updating category: " + theCategory.getId());
		
		try {
			
			// update category in the database
			categoryDbUtil.updateProductCategory(theCategory);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating category: " + theCategory, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-customers?faces-redirect=true";		
	}
	
	
	public String deleteProductCategory(int categoryId) {

		logger.info("Deleting category id: " + categoryId);
		
		try {

			// delete the student from the database
			categoryDbUtil.deleteProductCategory(categoryId);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting category id: " + categoryId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-customers-form";	
	}	
	
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
