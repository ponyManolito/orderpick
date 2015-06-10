package security.orderpick.datamodel.in;

import java.util.List;

public class InOrderType {

	public int id;

	public String type;

	public String status;

	public List<InProductInOrder> products;

	public InOrderType() {}

	public InOrderType(int id, String type, String status, List<InProductInOrder> products) {
		super();
		this.id = id;
		this.type = type;
		this.status = status;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<InProductInOrder> getProducts() {
		return products;
	}

	public void setProducts(List<InProductInOrder> products) {
		this.products = products;
	}

}
