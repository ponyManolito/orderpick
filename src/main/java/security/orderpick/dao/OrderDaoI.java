package security.orderpick.dao;

import java.util.List;

import security.orderpick.datamodel.Order;

public interface OrderDaoI {

	public List<Order> getAll();

	public List<Order> getAllAlive();
}
