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
public class CustomerController {

	private List<Customer> customers;
	private CustomerDbUtil customerDbUtil;
	private Logger logger = Logger.getLogger(getClass().getName());
	
	public CustomerController() throws Exception {
		customers = new ArrayList<>();
		
		customerDbUtil = CustomerDbUtil.getInstance();
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void loadCustomers() {

		logger.info("Loading customer");
		
		customers.clear();

		try {
			
			// get all customers from database
			customers = customerDbUtil.getCustomer();
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading customers", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
	public String addCustomer(Customer theCustomer) {

		logger.info("Adding customer: " + theCustomer);

		try {
			
			// add customer to the database
			customerDbUtil.addCustomer(theCustomer);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding customers", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}
		
		return "list-customers?faces-redirect=true";
	}
	
	public String loadCustomer(int customerId) {
		
		logger.info("loading customer: " + customerId);
		
		try {
			// get customer from database
			
			Customer theCustomer = customerDbUtil.getCustomer(customerId);
			
			// put in the request attribute ... so we can use it on the form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("customer", theCustomer);	
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading customer id:" + customerId, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
				
		return "update-customer-form.xhtml";
	}	

	public String updateCustomer(Customer theCustomer) {

		logger.info("updating student: " + theCustomer);
		
		try {
			
			// update customer in the database
			customerDbUtil.updateCustomer(theCustomer);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error updating customer: " + theCustomer, exc);
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-customers?faces-redirect=true";		
	}
	
	
	public String deleteCustomer(int customerId) {

		logger.info("Deleting customer id: " + customerId);
		
		try {

			// delete the student from the database
			customerDbUtil.deleteCustomer(customerId);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error deleting customer id: " + customerId, exc);
			
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
