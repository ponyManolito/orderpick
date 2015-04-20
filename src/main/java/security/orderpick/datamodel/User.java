package security.orderpick.datamodel;

import java.util.Date;

import security.orderpick.datamodel.common.Entity;


public class User extends Entity{
	
	private String name;
	private String password;
	public User() {}
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	
	public User(int id, String name, String password, Date reg_date) {
		setId(id);
		this.name = name;
		this.password = password;
		setReg_date(reg_date);
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
	public boolean isNewUser(){
		return getId()==0;
	}
	
}
