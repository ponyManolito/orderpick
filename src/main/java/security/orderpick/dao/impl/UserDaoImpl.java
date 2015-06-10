package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

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
	public User getUser(int id) {
		return userMapper.getUser(id);
	}

	@Override
	public int addUser(User user) {
		int inserted = user.isNewUser() ? userMapper.addUser(user) : userMapper.updateUser(user);
		userMapper.addRoleAdmin(new UserRole(user.getName(), "ROLE_ADMIN"));
		return inserted;
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(int id) {
		userMapper.deleteRoleAdmin(id, "ROLE_ADMIN");
		return userMapper.deleteUser(id);
	}
}
