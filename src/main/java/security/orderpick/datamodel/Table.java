package security.orderpick.datamodel;

import security.orderpick.datamodel.common.Entity;

public class Table extends Entity {

	private String name;

	private String description;

	private boolean available;

	public Table() {}

	public Table(String name, String description, boolean available) {
		super();
		this.name = name;
		this.description = description;
		this.available = available;
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isNewTable() {
		return getId() == 0;
	}

}
