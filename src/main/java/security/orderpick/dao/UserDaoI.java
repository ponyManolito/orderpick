package security.orderpick.dao;

import java.util.List;

import security.orderpick.datamodel.User;

public interface UserDaoI {

	public List<User> getAll();
	
	public List<String> getAllRoles();

	public User getUser(int id);

	public int addUser(User user);

	public int updateUser(User user);

	public int deleteUser(int id);

	public String permision(String name);
}
