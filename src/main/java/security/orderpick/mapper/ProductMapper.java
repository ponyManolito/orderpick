package security.orderpick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import security.orderpick.datamodel.Product;

@Component(ProductMapper.name)
public interface ProductMapper {

	public static final String name = "productMapper";

	@Select("SELECT * FROM cf_products WHERE id = #{id}")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"), @Result(property = "image", column = "IMAGE"),
			@Result(property = "movie", column = "MOVIE"), @Result(property = "empty", column = "EMPTY"),
			@Result(property = "price", column = "PRICE") })
	public Product getProduct(int id);

	@Insert("insert into cf_products (name, description, image, movie, empty, price) "
			+ "values(#{name}, #{description}, #{image}, #{movie}, #{empty}, #{price})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addProduct(Product product);

	@Select("SELECT * FROM cf_products")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"), @Result(property = "image", column = "IMAGE"),
			@Result(property = "movie", column = "MOVIE"), @Result(property = "empty", column = "EMPTY"),
			@Result(property = "price", column = "PRICE") })
	public List<Product> getAll();

	@Update("update cf_products set name=#{name}, description=#{description}, "
			+ "image=#{image}, movie=#{movie}, empty=#{empty}, price=#{price} where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int updateProduct(Product product);

	@Delete("delete from cf_products where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int deleteProduct(int id);
	
	@Select("SELECT * FROM cf_products "
		+ "WHERE id in "
		+ "(SELECT id_product FROM product_types "
		+ "WHERE id_type in "
		+ "(SELECT id FROM cf_types "
		+ "WHERE name = #{type}))")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"), @Result(property = "image", column = "IMAGE"),
			@Result(property = "movie", column = "MOVIE"), @Result(property = "empty", column = "EMPTY"),
			@Result(property = "price", column = "PRICE") })
	public List<Product> getAllProductsByType(String type);
	
	@Select("SELECT cf_products.id, cf_products.name, cf_products.description,cf_products.image,cf_products.movie,cf_products.empty,cf_products.price "
			+ "FROM product_types,cf_types,cf_products where product_types.id_product = cf_products.id "
			+ "AND product_types.id_type = cf_types.id AND cf_types.name in (#{types}) "
			+ "GROUP BY product_types.id_product "
			+ "HAVING COUNT(*) > 1")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"), @Result(property = "image", column = "IMAGE"),
			@Result(property = "movie", column = "MOVIE"), @Result(property = "empty", column = "EMPTY"),
			@Result(property = "price", column = "PRICE") })
	public List<Product> getAllProductsInMenuByTypes(String[] types);
}
