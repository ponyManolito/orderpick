package security.orderpick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import security.orderpick.datamodel.ProductOrder;

@Component(ProductOrdersMapper.name)
public interface ProductOrdersMapper {
	
	public static final String name = "productOrdersMapper";

	@Select("SELECT id_type FROM product_types WHERE id_product = #{idProduct}")
	public List<Integer> getOrdersByProduct(int idProduct);
	
	@Insert("insert into product_types (id_product, id_type) values(#{idProduct}, #{idType})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addProductOrder(ProductOrder productOrder);
	
	@Delete("delete from product_types where id_product = #{idProduct}")
	@Options(flushCache = true, useCache = true)
	public int deleteByProduct(int idProduct);

}
