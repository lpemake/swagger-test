package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Product {
	@JsonProperty(required = true)
    @ApiModelProperty(notes = "id of the product", required = true)
	private int id;
	
	@JsonProperty(required = true)
    @ApiModelProperty(notes = "name of the product", required = true)
	private String name;
	
	@JsonProperty(required = true)
    @ApiModelProperty(notes = "price of the product", required = true)	
	private int price;
	
	private static int counter;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
		this.id = counter++;
	}

	public Product() {
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
			
}
