package orderPurchase.jdbc;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String address;
	private String email;
	
	public Customer() {}
	
	public Customer(int id, String firstName, String lastName, 
			String phoneNum, String address,String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNum = phoneNum;
		this.address = address;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "customer [id=" + id 
				+ ", firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", phoneNum=" + phoneNum 
				+ ", address=" + address 
				+ ", email=" + email 
				+ "]";
	}

}
