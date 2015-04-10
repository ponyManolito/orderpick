package security.orderpick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.mapper.OrderMapper;
import security.orderpick.datamodel.Order;

@RestController
@RequestMapping("/orders")
public class ordersController {
	
	@Autowired
	private OrderMapper orderMapper ;
	
	@RequestMapping(value="/getall",produces="application/json")
	public List<Order> getAll(){
	    return orderMapper.getAll();
	}
}
