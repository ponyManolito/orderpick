package security.orderpick.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import security.orderpick.dao.OrderDaoI;
import security.orderpick.datamodel.Bill;
import security.orderpick.datamodel.Order;
import security.orderpick.datamodel.OrderRow;
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
	public Bill getBill(int id) {
		Bill bill = new Bill();
		bill.setId(1);
		bill.setTable("1");
		List<OrderRow> lr = new LinkedList<OrderRow>();
		OrderRow order = new OrderRow();
		order.setId(1);
		order.setName("cerve");
		order.setPrice("2");
		order.setQuantity("2");
		order.setTotalItemPrice("4");
		lr.add(order);
		bill.setItems(lr);
		bill.setTotalItemPrice("5");
//		List<OrderRow> lOrder =	orderMapper.getBill(id);
//		bill.setItems(lOrder);
		return bill;
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
}
