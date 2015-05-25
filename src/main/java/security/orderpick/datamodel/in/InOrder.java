package security.orderpick.datamodel.in;

import java.util.Date;
import java.util.List;

import security.orderpick.datamodel.common.Entity;

public class InOrder extends Entity {

	public int idTable;

	public String description;

	public List<InOrderType> types;

	public InOrder() {}

	public InOrder(int id, int idTable, String description, List<InOrderType> types, Date reg_date) {
		super();
		setId(id);
		this.idTable = idTable;
		this.description = description;
		this.types = types;
		setReg_date(reg_date);
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

	public List<InOrderType> getTypes() {
		return types;
	}

	public void setTypes(List<InOrderType> types) {
		this.types = types;
	}

	public boolean isNew() {
		return getId() == 0;
	}

}
