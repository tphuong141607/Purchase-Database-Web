package orderPurchase.jdbc;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {

	private int id;
	private String name;
	private String description;
	private double price;
	private int categoryId;
	
	public Product() {}
	
	public Product(int id, String name, String description, 
			double price, int categoryId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "product [id=" + id 
				+ ", name=" + name 
				+ ", description=" + description
				+ ", price=" + price 
				+ ", categoryId=" + categoryId 
				+ "]";
	}

}
