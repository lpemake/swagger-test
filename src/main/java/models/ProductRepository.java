package models;

public interface ProductRepository {
	Iterable<Product> getAll();
    Product getById(int id);
    Product getByName(String name);
    Product add(Product item);
    Product removeAt(int index);
    boolean update(Product item);
}
