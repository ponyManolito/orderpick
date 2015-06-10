package security.orderpick.datamodel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import security.orderpick.datamodel.common.Entity;

@XmlRootElement
public class Bill2 extends Entity {


	private String name;
	
	private String mesa;

	private String producto;

	private String cantidadProducto;
	
	private String precio;

	public Bill2() {}

	public Bill2(String mesa, String producto, String cantidadProducto, String precio) {
		super();
		this.mesa = mesa;
		this.producto = producto;
		this.cantidadProducto = cantidadProducto;
		this.precio=precio;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}
	@XmlElement
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	@XmlElement
	public String getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(String cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	@XmlElement
	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public boolean isNewTable() {
		return getId() == 0;
	}
	
	@Override
	public String toString(){
		String bill = 
				"Name: " + name + "\n" +
				"Mesa: " + mesa + "\n" +
				"Producto: " + producto + "\n" +
				"cantidadProducto: " + cantidadProducto + "\n" +
				"precio: " + precio + "\n";
		return bill;
		
	}

}
