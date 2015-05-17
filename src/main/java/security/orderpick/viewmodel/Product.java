package security.orderpick.viewmodel;

import org.springframework.web.multipart.MultipartFile;

import security.orderpick.datamodel.common.Entity;

public class Product extends Entity {

	private String name;

	private String description;

	private MultipartFile image;

	private MultipartFile movie;
	
	private Double price;
	
	private boolean empty;

	public Product() {}

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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public MultipartFile getMovie() {
		return movie;
	}

	public void setMovie(MultipartFile movie) {
		this.movie = movie;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isNewProduct() {
		return getId() == 0;
	}

}
