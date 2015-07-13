package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import security.orderpick.dao.OrderDaoI;
import security.orderpick.datamodel.Order;
import security.orderpick.datamodel.OrderType;
import security.orderpick.datamodel.OrderView;
import security.orderpick.datamodel.ProductInOrder;
import security.orderpick.mapper.OrderMapper;

@Component(OrderDaoImpl.name)
public class OrderDaoImpl implements OrderDaoI {

	public static final String name = "orderDaoImpl";

	@Resource(name = OrderMapper.name)
	private OrderMapper orderMapper;

	@Override
	public List<OrderView> getAll() {
		return orderMapper.getAll();
	}

	@Override
	@MessageMapping("/orders/getalive")
	@SendTo("/topic/orders")
	public List<OrderView> getAllAlive() {
		return orderMapper.getAllAlive();
	}

	@Override
	public int insertOrder(Order order) {
		return order.isNew() ? orderMapper.addOrder(order) : orderMapper.updateOrder(order);
	}

	@Override
	public int insertOrderType(OrderType orderType) {
		return orderType.isNew() ? orderMapper.addOrderType(orderType) : orderMapper.updateOrderType(orderType);
	}

	@Override
	public int insertProductInOrder(ProductInOrder productInOrder) {
		return productInOrder.isNew() ? orderMapper.addProductInOrder(productInOrder) : orderMapper
				.updateProductInOrder(productInOrder);
	}

	@Override
	public List<OrderView> getAllByStatus(List<String> status) {
		return orderMapper.getAllByStatus((String[]) status.toArray());
	}
}
