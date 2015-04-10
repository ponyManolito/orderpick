package security.orderpick.datamodel.common;

import java.util.Date;

public class Entity {
	int id;
	Date reg_date;
	
	public Entity() {
		super();
		reg_date = new Date();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}
