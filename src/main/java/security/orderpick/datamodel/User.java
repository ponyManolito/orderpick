package security.orderpick.datamodel;

import java.util.Date;


public class User{
	
	int id;
	Date reg_date;
	private String name;
	private String password;
	public User() {}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.reg_date = new Date();
	}
	
	public User(int id, String name, String password, Date reg_date) {
		this.id=id;
		this.name = name;
		this.password = password;
		this.reg_date=reg_date;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}
