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
			@Result(property = "reg_date", column = "REG_DATE") })
	public Product getProduct(int id);

	@Insert("insert into cf_products (name, description, image, movie, empty) values(#{name}, #{description}, #{image}, #{movie}, #{empty})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addProduct(Product product);

	@Select("SELECT * FROM cf_products")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"), @Result(property = "image", column = "IMAGE"),
			@Result(property = "movie", column = "MOVIE"), @Result(property = "empty", column = "EMPTY"),
			@Result(property = "reg_date", column = "REG_DATE") })
	public List<Product> getAll();

	@Update("update cf_products set name=#{name}, description=#{description}, image=#{image}, movie=#{movie}, empty=#{empty} where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int updateProduct(Product product);

	@Delete("delete from cf_products where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int deleteProduct(int id);
}
