package security.orderpick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import security.orderpick.datamodel.Order;
import security.orderpick.datamodel.OrderType;
import security.orderpick.datamodel.OrderView;
import security.orderpick.datamodel.ProductInOrder;

@Component(OrderMapper.name)
public interface OrderMapper {

	public static final String name = "orderMapper";

	@Select("SELECT orders.id as ID, cf_tables.name as NAME, cf_types.name as ORDER_TYPE, "
			+ "orders_type.status as STATUS, orders.reg_date as REG_DATE FROM cf_tables,orders,orders_type, cf_types "
			+ "WHERE cf_types.id=orders_type.id_type AND cf_tables.id = orders.id_table AND orders.id = orders_type.id_order")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "order_type", column = "ORDER_TYPE"), @Result(property = "status", column = "STATUS"),
			@Result(property = "reg_date", column = "REG_DATE") })
	public List<OrderView> getAll();
	
	@Select("SELECT orders.id as ID, cf_tables.name as NAME, cf_types.name as ORDER_TYPE, "
			+ "orders_type.status as STATUS, orders.reg_date as REG_DATE FROM cf_tables,orders,orders_type, cf_types "
			+ "WHERE cf_types.id=orders_type.id_type AND cf_tables.id = orders.id_table AND orders.id = orders_type.id_order "
			+"orders_type.status<>'DELIVERED'")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "order_type", column = "ORDER_TYPE"), @Result(property = "status", column = "STATUS"),
			@Result(property = "reg_date", column = "REG_DATE") })
	public List<OrderView> getAllAlive();

	@Insert("Insert into orders(id_table,description,reg_date) values(#{idTable},#{description},#{reg_date})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addOrder(Order order);

	@Insert("Insert into orders_type(id_order,id_type,status) values(#{idOrder},#{idOrderType},#{status})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addOrderType(OrderType orderType);

	@Insert("Insert into orders_products(id_type,id_product,quantity) values(#{idOrderType},#{idProduct},#{quantity})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addProductInOrder(ProductInOrder productsInOrder);

	@Update("Update orders set id_table = #{idTable},description=#{description} where id=#{id})")
	@Options(flushCache = true, useCache = true)
	public int updateOrder(Order order);

	@Update("Update orders_type set id_order=#{idOrder},id_type=#{orderType},status=#{status} where id=#{id})")
	@Options(flushCache = true, useCache = true)
	public int updateOrderType(OrderType orderType);

	@Update("Update orders_products set id_type=#{idOrderType},id_product=#{idProduct},quantity=#{quantity} where id=#{id})")
	@Options(flushCache = true, useCache = true)
	public int updateProductInOrder(ProductInOrder productsInOrder);
}
