package security.orderpick.viewmodel;

import java.util.List;

import security.orderpick.datamodel.common.Entity;

public class Product extends Entity {

	private String name;

	private String description;

	private String imageName;

	private String imageData;

	private String movieData;

	private String movieName;

	private Double price;

	private boolean empty;
	
	private List<Integer> types;

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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public String getMovieData() {
		return movieData;
	}

	public void setMovieData(String movieData) {
		this.movieData = movieData;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
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

	public List<Integer> getTypes() {
		return types;
	}

	public void setTypes(List<Integer> types) {
		this.types = types;
	}

	public boolean isNewProduct() {
		return getId() == 0;
	}

}
