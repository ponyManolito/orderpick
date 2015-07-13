package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.orderpick.dao.UserDaoI;
import security.orderpick.datamodel.User;
import security.orderpick.datamodel.UserRole;
import security.orderpick.mapper.UserMapper;

@Component(UserDaoImpl.name)
public class UserDaoImpl implements UserDaoI {

	public static final String name = "userDaoImpl";

	@Resource(name = UserMapper.name)
	private UserMapper userMapper;

	@Override
	public List<User> getAll() {
		return userMapper.getAll();
	}
	
	@Override
	public List<String> getAllRoles() {
		return userMapper.getAllRoles();
	}

	@Override 
	public User getUser(int id) {
		User user = userMapper.getUser(id);
		user.setProfile(userMapper.permision(user.getName()));
		return user;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int addUser(User user) {
		int inserted = 0; 
		if (user.isNewUser()){
			inserted = userMapper.addUser(user);
			userMapper.addRoleAdmin(new UserRole(user.getName(), user.getProfile()));
		}
		else{
			inserted = userMapper.updateUser(user);
			userMapper.updateRoleAdmin(new UserRole(user.getName(), user.getProfile()));
		}
		return inserted;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int deleteUser(int id) {
		User user = userMapper.getUser(id);
		userMapper.deleteRoleAdmin(id, userMapper.permision(user.getName()));
		return userMapper.deleteUser(id);
	}

	@Override
	public String permision(String name) {
		return userMapper.permision(name);
	}

	@Override
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}
}
