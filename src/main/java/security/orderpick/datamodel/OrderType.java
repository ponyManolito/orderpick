package security.orderpick.datamodel;

public class OrderType {
	public int id;

	public int idOrder;

	public int idOrderType;

	public String status;

	public OrderType() {}

	public OrderType(int id, int idOrder, int idOrderType, String status) {
		super();
		this.id = id;
		this.idOrder = idOrder;
		this.idOrderType = idOrderType;
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

	public int getIdOrderType() {
		return idOrderType;
	}

	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
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
