package security.orderpick.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.dao.ProductDaoI;
import security.orderpick.dao.impl.ProductDaoImpl;
import security.orderpick.datamodel.Product;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Resource(name = ProductDaoImpl.name)
	private ProductDaoI productDao;

	@RequestMapping(method = { RequestMethod.GET }, value = "/getall", produces = "application/json")
	public List<Product> getAll() {
		return productDao.getAll();
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getproduct", produces = "application/json")
	public Product getProduct(@RequestParam(value = "id") int id) {
		return productDao.getProduct(id);
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/addproduct", produces = "application/json")
	public int addProduct(@RequestBody Product product) {
		return productDao.addProduct(product);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = "/updateproduct", produces = "application/json")
	public int updateUser(@RequestBody Product product) {
		return productDao.updateProduct(product);
	}

	@RequestMapping(method = { RequestMethod.DELETE }, value = "/deleteproduct/{id}", produces = "application/json")
	public int deleteUser(@PathVariable(value = "id") int id) {
		return productDao.deleteProduct(id);
	}
}
