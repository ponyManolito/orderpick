package security.orderpick.datamodel;

public class ProductOrder {

	public int id;

	public int idType;

	public int idProduct;


	public ProductOrder() {}

	public ProductOrder(int id, int idType, int idProduct) {
		super();
		this.id = id;
		this.idType = idType;
		this.idProduct = idProduct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	public boolean isNew() {
		return getId() == 0;
	}

}
