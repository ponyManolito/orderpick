package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import security.orderpick.dao.ProductDaoI;
import security.orderpick.datamodel.Product;
import security.orderpick.mapper.ProductMapper;

@Component(ProductDaoImpl.name)
public class ProductDaoImpl implements ProductDaoI {

	public static final String name = "productDaoImpl";

	@Resource(name = ProductMapper.name)
	private ProductMapper productMapper;

	@Override
	public List<Product> getAll() {
		return productMapper.getAll();
	}

	@Override
	public Product getProduct(int id) {
		return productMapper.getProduct(id);
	}

	@Override
	public int addProduct(Product product) {
		return product.isNewProduct() ? productMapper.addProduct(product) : productMapper.updateProduct(product);
	}

	@Override
	public int updateProduct(Product product) {
		return productMapper.updateProduct(product);
	}

	@Override
	public int deleteProduct(int id) {
		return productMapper.deleteProduct(id);
	}
}
