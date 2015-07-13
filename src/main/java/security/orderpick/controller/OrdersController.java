package security.orderpick.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.dao.OrderDaoI;
import security.orderpick.dao.impl.OrderDaoImpl;
import security.orderpick.datamodel.Order;
import security.orderpick.datamodel.OrderType;
import security.orderpick.datamodel.OrderView;
import security.orderpick.datamodel.ProductInOrder;
import security.orderpick.datamodel.in.InOrder;
import security.orderpick.util.Converter;
import security.orderpick.validation.OrderValidator;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Resource(name = OrderDaoImpl.name)
	private OrderDaoI orderDao;

	@Resource(name = Converter.name)
	private Converter converter;

	@Resource(name = OrderValidator.name)
	private OrderValidator orderValidator;

	@RequestMapping(value = "/getall", produces = "application/json")
	public List<OrderView> getAll() {
		return orderDao.getAll();
	}

	@MessageMapping("/insertorder")
	@SendTo("/topic/orders")
	public List<OrderView> insertOrder(InOrder order, Errors error) throws Exception {
		
		validateOrder(order, error);

		Order newOrder = converter.getOrder(order);
		int idOrder = orderDao.insertOrder(newOrder);
		List<OrderType> newOrdersStatus = converter.getOrdersStatus(idOrder, order);
		List<Integer> idOrdersType = new ArrayList<Integer>();
		if (!newOrdersStatus.isEmpty()) {
			for (OrderType orderType : newOrdersStatus) {
				idOrdersType.add(orderDao.insertOrderType(orderType));
			}
			List<ProductInOrder> newProductsInOrder = converter.getProductsInOrder(idOrdersType, order);
			if (!newProductsInOrder.isEmpty()) {
				for (ProductInOrder productInOrder : newProductsInOrder) {
					orderDao.insertProductInOrder(productInOrder);
				}
			}

		}

		return orderDao.getAllAlive();
	}

	@RequestMapping(value = "/getallbystatus", produces = "application/json")
	public List<OrderView> getAllByStatus(List<String> status) {
		return orderDao.getAllByStatus(status);
	}
	
	@RequestMapping(value = "/getallalive", produces = "application/json")
	public List<OrderView> getAllAlive() {
		return orderDao.getAllAlive();
	}
	
	private void validateOrder(InOrder order, Errors error) throws Exception {
		orderValidator.validate(order, error);
		if (error.hasErrors()) {
			List<ObjectError> errors = error.getAllErrors();
			String errosString = "";
			for (ObjectError objectError : errors) {
				if (!errosString.isEmpty()) {
					errosString += errosString;
				}
				errosString += objectError.getDefaultMessage();
			}
			throw new Exception(errosString);
		}
	}
}
