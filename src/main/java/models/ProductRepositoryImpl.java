package models;

import java.util.ArrayList;

public class ProductRepositoryImpl implements ProductRepository {

	private static ArrayList<Product> products;
	
	public ProductRepositoryImpl() {
		// TODO Auto-generated constructor stub
		if (products == null)
		{
			products = new ArrayList<Product>();
			products.add(new Product("product1", 100));
			products.add(new Product("product2", 200));
			products.add(new Product("product3", 300));
		}
	}

	@Override
	public Iterable<Product> getAll() {		
		return products;
	}

	@Override
	public Product getById(int id) {
		
		Product foundProduct = null;
		for (Product p : products)
		{
			if (p.getId() == id)
			{
				foundProduct = p;
				break;
			}			
		}
		return foundProduct;
	}

	@Override
	public Product add(Product item) {
		products.add(item);
		return item;
	}

	@Override
	public Product removeAt(int index) {
		
		Product p = products.remove(index);
		return p;
	}

	@Override
	public boolean update(Product item) {
		int index = products.indexOf(item);
		if (index == -1)
			return false;					
		products.remove(index);
		products.add(item);
		return true;
	}

	@Override
	public Product getByName(String name) {
		Product foundProduct = null;
		for (Product p : products)
		{
			if (p.getName().compareTo(name) == 0)
			{
				foundProduct = p;
				break;
			}			
		}
		return foundProduct;
	}
	

}
