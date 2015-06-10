package security.orderpick.datamodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import security.orderpick.datamodel.common.Entity;

@XmlRootElement
public class OrderRow extends Entity {

	private String name;
	private String price;
	private String quantity;
	private String totalItemPrice;

	public OrderRow() {}

	public OrderRow(String name, String price, String quantity, String totalItemPrice) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.totalItemPrice=totalItemPrice;
	}
	
	public boolean isNewTable() {
		return getId() == 0;
	}
	
	@Override
	public String toString(){
		String row = 
				"name: " + name + "\n" +
				"price: " + price + "\n" +
				"quantity: " + quantity + "\n" +
				"totalItemPrice: " + totalItemPrice + "\n";
		return row;
		
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	@XmlElement
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@XmlElement
	public String getTotalItemPrice() {
		return totalItemPrice;
	}

	public void setTotalItemPrice(String totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}
	

}
