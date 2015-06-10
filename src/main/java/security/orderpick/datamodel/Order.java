package security.orderpick.datamodel;

import java.util.Date;

import security.orderpick.datamodel.common.Entity;

public class Order extends Entity {

	public int idTable;

	public String description;

	public Order() {}

	public Order(int id, int idTable, String description, Date regTime) {
		super();
		setId(id);
		this.idTable = idTable;
		this.description = description;
		setReg_date(regTime);
	}

	public int getIdTable() {
		return idTable;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isNew() {
		return getId() == 0;
	}

}
