package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import security.orderpick.dao.ProductDaoI;
import security.orderpick.datamodel.Product;
import security.orderpick.mapper.ProductMapper;
import security.orderpick.mapper.ProductOrdersMapper;

@Component(ProductDaoImpl.name)
public class ProductDaoImpl implements ProductDaoI {

	public static final String name = "productDaoImpl";

	@Resource(name = ProductMapper.name)
	private ProductMapper productMapper;
	
	@Resource(name = ProductOrdersMapper.name)
	private ProductOrdersMapper productOrdersMapper;

	@Override
	public List<Product> getAll() {
		List<Product> products = productMapper.getAll();
		
		for (Product product:products){
			List<Integer> types = productOrdersMapper.getOrdersByProduct(product.getId());
			product.setTypes(types);
		}
		
		return products;
	}

	@Override
	public Product getProduct(int id) {
		List<Integer> types = productOrdersMapper.getOrdersByProduct(id);
		Product product = productMapper.getProduct(id);
		product.setTypes(types);
		return product;
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
		productOrdersMapper.deleteByProduct(id);
		return productMapper.deleteProduct(id);
	}
}
