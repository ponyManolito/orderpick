package security.orderpick.datamodel;

public class OrderType {
	public int id;

	public int idOrder;

	public String orderType;

	public String status;

	public OrderType() {}

	public OrderType(int id, int idOrder, String orderType, String status) {
		super();
		this.id = id;
		this.idOrder = idOrder;
		this.orderType = orderType;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isNew() {
		return getId() == 0;
	}

}
