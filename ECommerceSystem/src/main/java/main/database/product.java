package main.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class product {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String category;
	private Integer seller;

	private Integer NumberOfItems;
	
	
	
	public product() {
		
	}
	public product(Integer id, String name, String category, Integer seller, Integer numberOfItems) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.seller = seller;
		NumberOfItems = numberOfItems;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getSeller() {
		return seller;
	}
	public void setSeller(Integer seller) {
		this.seller = seller;
	}

	public Integer getNumberOfItems() {
		return NumberOfItems;
	}

	public void setNumberOfItems(Integer numberOfItems) {
		NumberOfItems = numberOfItems;
	}
}
