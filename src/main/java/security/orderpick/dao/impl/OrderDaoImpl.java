package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import security.orderpick.dao.OrderDaoI;
import security.orderpick.datamodel.Order;
import security.orderpick.mapper.OrderMapper;

@Component(OrderDaoImpl.name)
public class OrderDaoImpl implements OrderDaoI {

	public static final String name = "orderDaoImpl";

	@Resource(name = OrderMapper.name)
	private OrderMapper orderMapper;

	@Override
	@RequestMapping(value = "/getall", produces = "application/json")
	public List<Order> getAll() {
		return orderMapper.getAll();
	}

	@Override
	@MessageMapping("/orders/getalive")
	@SendTo("/topic/orders")
	public List<Order> getAllAlive() {
		return orderMapper.getAllAlive();
	}
}
