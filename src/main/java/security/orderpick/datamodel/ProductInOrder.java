package security.orderpick.datamodel;

public class ProductInOrder {

	public int id;

	public int idOrderType;

	public int idProduct;

	public int quantity;

	public ProductInOrder() {}

	public ProductInOrder(int id, int idOrderType, int idProduct, int quantity) {
		super();
		this.id = id;
		this.idOrderType = idOrderType;
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdOrderType() {
		return idOrderType;
	}

	public void setIdOrderType(int idOrderType) {
		this.idOrderType = idOrderType;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isNew() {
		return getId() == 0;
	}

}
