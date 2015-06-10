package security.orderpick.dao;

import java.util.List;

import security.orderpick.datamodel.Product;

public interface ProductDaoI {

	public List<Product> getAll();

	public Product getProduct(int id);

	public int addProduct(Product product);

	public int updateProduct(Product product);

	public int deleteProduct(int id);
	
	public List<Product> getAllProductsByType(String type);
}
