package security.orderpick.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.dao.OrderDaoI;
import security.orderpick.dao.impl.OrderDaoImpl;
import security.orderpick.datamodel.Order;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Resource(name = OrderDaoImpl.name)
	private OrderDaoI orderDao;

	@RequestMapping(value = "/getall", produces = "application/json")
	public List<Order> getAll() {
		return orderDao.getAll();
	}

	@MessageMapping("/orders/getalive")
	@SendTo("/topic/orders")
	public List<Order> getAllAlive() {
		return orderDao.getAllAlive();
	}
}
