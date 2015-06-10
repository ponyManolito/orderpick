package security.orderpick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import security.orderpick.datamodel.Order;
import security.orderpick.datamodel.OrderRow;

@Component(OrderMapper.name)
public interface OrderMapper {

	public static final String name = "orderMapper";

	@Select("SELECT orders.id as ID, cf_tables.name as NAME, orders_type.order_type as ORDER_TYPE, "
			+ "orders_type.status as STATUS, orders.reg_date as REG_DATE FROM cf_tables,orders,orders_type "
			+ "WHERE cf_tables.id = orders.id_table AND orders.id = orders_type.id_order")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "order_type", column = "ORDER_TYPE"), @Result(property = "status", column = "STATUS"),
			@Result(property = "reg_date", column = "REG_DATE") })
	List<Order> getAll();

	@Select("SELECT orders.id as ID, cf_tables.name as NAME, orders_type.order_type as ORDER_TYPE, "
			+ "orders_type.status as STATUS, orders.reg_date as REG_DATE FROM cf_tables,orders,orders_type "
			+ "WHERE cf_tables.id = orders.id_table AND orders.id = orders_type.id_order orders_type.status<>'DELIVERED'")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "order_type", column = "ORDER_TYPE"), @Result(property = "status", column = "STATUS"),
			@Result(property = "reg_date", column = "REG_DATE") })
	List<Order> getAllAlive();
	
	@Select("SELECT a.name, a.price, b.quantity , a.price * b.quantity totalItemPrice FROM orderit.cf_products a, orderit.orders_products b, orderit.cf_tables c WHERE c.id = b.id_product AND b.id_product = a.id AND c.id = #{id}")
	@Results(value = { @Result(property = "id", column = "ID"),
			@Result(property = "name", column = "NAME"),
			@Result(property = "precio", column = "PRECIO"),
			@Result(property = "producto", column = "AVAILABLE") })
	public List<OrderRow> getBill(int id);
}
