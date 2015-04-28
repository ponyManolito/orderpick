package security.orderpick.datamodel;

import java.util.Date;

import security.orderpick.datamodel.common.Entity;


public class Table extends Entity{
	
	private String name;
	private String description;
	public Table() {}
	public Table(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isNewTable(){
		return getId()==0;
	}
	
}
