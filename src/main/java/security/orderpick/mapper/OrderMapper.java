package security.orderpick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import security.orderpick.datamodel.Order;

public interface OrderMapper {
	@Select("SELECT orders.id as ID, cf_tables.name as NAME, orders_type.order_type as ORDER_TYPE, "
			+ "orders_type.status as STATUS, orders.reg_date as REG_DATE FROM cf_tables,orders,orders_type "
			+ "WHERE cf_tables.id = orders.id_table AND orders.id = orders_type.id_order")
	@Results(value = {
		@Result(property="id", column="ID"),
		@Result(property="name", column="NAME"),
		@Result(property="order_type", column="ORDER_TYPE"),
		@Result(property="status", column="STATUS"),
		@Result(property="reg_date", column="REG_DATE")
	})
	List<Order> getAll();
}
