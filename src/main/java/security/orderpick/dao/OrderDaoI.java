package security.orderpick.dao;

import java.util.List;

import security.orderpick.datamodel.Order;
import security.orderpick.datamodel.OrderType;
import security.orderpick.datamodel.OrderView;
import security.orderpick.datamodel.ProductInOrder;

public interface OrderDaoI {

	public List<OrderView> getAll();

	public List<OrderView> getAllAlive();

	public int insertOrder(Order order);

	public int insertOrderType(OrderType orderType);

	public int insertProductInOrder(ProductInOrder productInOrder);
}
