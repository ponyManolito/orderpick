package security.orderpick.datamodel;

import java.util.Date;

import security.orderpick.datamodel.common.Entity;

public class OrderView extends Entity {

	public String name;

	public String order_type;

	public String status;

	public OrderView() {}

	public OrderView(String name, String order_type, String status) {
		super();
		this.name = name;
		this.order_type = order_type;
		this.status = status;
	}

	public OrderView(int id, String name, String order_type, String status, Date reg_date) {
		setId(id);
		this.name = name;
		this.order_type = order_type;
		this.status = status;
		setReg_date(reg_date);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isNewOrder() {
		return getId() == 0;
	}

}
