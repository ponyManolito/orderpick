package security.orderpick.datamodel;

import security.orderpick.datamodel.common.Entity;

public class Product extends Entity {

	private String name;

	private String description;

	private String image;

	private String movie;

	private boolean empty;

	public Product() {}

	public Product(String name, String description, String image, String movie, boolean empty) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.movie = movie;
		this.empty = empty;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public boolean isNewProduct() {
		return getId() == 0;
	}

}
