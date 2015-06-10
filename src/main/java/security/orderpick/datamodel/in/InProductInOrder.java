package security.orderpick.datamodel.in;

public class InProductInOrder {

	public int id;

	public int idProduct;

	public int quantity;

	public InProductInOrder() {}

	public InProductInOrder(int id, int idProduct, int quantity) {
		super();
		this.id = id;
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
