package security.orderpick.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import security.orderpick.datamodel.common.Entity;

@XmlRootElement
public class Bill extends Entity {

	private String title;
	private String table;
	private List<OrderRow> items;
	private String totalItemPrice;

	public Bill() {}

	public Bill(String title, String table, List<OrderRow> items, String totalItemPrice) {
		super();
		this.title = title;
		this.table = table;
		this.items = items;
		this.totalItemPrice=totalItemPrice;
	}
	
	public boolean isNewTable() {
		return getId() == 0;
	}
	
	@Override
	public String toString(){
		String bill = 
				"title: " + title + "\n" +
				"table: " + table + "\n" +
				"items: " + items + "\n" +
				"totalItemPrice: " + totalItemPrice + "\n";
		return bill;
		
	}
	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	@XmlElement
	public List<OrderRow> getItems() {
		return items;
	}

	public void setItems(List<OrderRow> items) {
		this.items = items;
	}
	@XmlElement
	public String getTotalItemPrice() {
		return totalItemPrice;
	}

	public void setTotalItemPrice(String totalItemPrice) {
		this.totalItemPrice = totalItemPrice;
	}

}
