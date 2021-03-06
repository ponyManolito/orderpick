package security.orderpick.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import security.orderpick.dao.ProductDaoI;
import security.orderpick.dao.impl.ProductDaoImpl;
import security.orderpick.datamodel.Product;
import security.orderpick.util.Converter;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Resource(name = ProductDaoImpl.name)
	private ProductDaoI productDao;

	@Resource(name = Converter.name)
	private Converter converter;

	@RequestMapping(method = { RequestMethod.GET }, value = "/getall", produces = "application/json")
	public List<Product> getAll() {
		return productDao.getAll();
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getproduct", produces = "application/json")
	public security.orderpick.viewmodel.Product getProduct(@RequestParam(value = "id") int id) throws IOException {
		return converter.convertToDataViewProduct(productDao.getProduct(id));
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/addproduct", produces = "application/json")
	public int addProduct(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name") String name, @RequestParam(value = "description") String description,
			@RequestParam(value = "price") String price, @RequestParam(value = "empty") Boolean empty,
			@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "types") List<Integer> types,
			@RequestParam(value = "movie", required = false) MultipartFile movie) throws IOException {
		Product productNew = converter.converterProduct(id, name, description, empty, price, image, movie, types);
		return id!=null&&!id.isEmpty()?productDao.updateProduct(productNew):productDao.addProduct(productNew);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = "/updateproduct", produces = "application/json")
	public int updateProduct(@RequestParam security.orderpick.viewmodel.Product product) throws IOException {
		return -1; // productDao.updateProduct(converter.converterProduct(product));
	}

	@RequestMapping(method = { RequestMethod.DELETE }, value = "/deleteproduct/{id}", produces = "application/json")
	public int deleteProduct(@PathVariable(value = "id") int id) {
		converter.deleteMedia(productDao.getProduct(id));
		return productDao.deleteProduct(id);
	}
	
	@RequestMapping(method = { RequestMethod.GET }, value = "/getallproducts", produces = "application/json")
	public List<Product> getAllProductsByType(@RequestParam(value = "type") String type) {
		return productDao.getAllProductsByType(type);
	}
	
	@RequestMapping(method = { RequestMethod.GET }, value = "/getallproductsinmenubytypes", produces = "application/json")
	public List<Product> getAllProductsInMenuByTypes(@RequestParam(value = "types") String types) {
		return productDao.getAllProductsInMenuByTypes(types);
	}
}
