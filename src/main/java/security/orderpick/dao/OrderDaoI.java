package security.orderpick.dao;

import java.util.List;

import security.orderpick.datamodel.Bill;
import security.orderpick.datamodel.Order;

public interface OrderDaoI {

	public List<Order> getAll();

	public List<Order> getAllAlive();
	
	public Bill getBill(int id);
}
