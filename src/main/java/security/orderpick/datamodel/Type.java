package security.orderpick.datamodel;

import security.orderpick.datamodel.common.Entity;

public class Type extends Entity {

	private String name;

	private String description;

	private boolean available;

	public Type() {}

	public Type(String name, String description, boolean available) {
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

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isNewType() {
		return getId() == 0;
	}

}
