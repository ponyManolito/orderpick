package security.orderpick.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.dao.UserDaoI;
import security.orderpick.dao.impl.UserDaoImpl;
import security.orderpick.datamodel.User;
import security.orderpick.util.Encrypt;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Resource(name = UserDaoImpl.name)
	private UserDaoI userDao;

	@Resource(name = Encrypt.name)
	private Encrypt encrypt;

	@RequestMapping(method = { RequestMethod.GET }, value = "/getall", produces = "application/json")
	public List<User> getAll() {
		List<User> result = userDao.getAll();
		for (User user : result) {
			user.setPassword(encrypt.decrypt(user.getPassword()));
		}
		return result;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getuser", produces = "application/json")
	public User getUser(@RequestParam(value = "id") int id) {
		User user = userDao.getUser(id);
		user.setPassword(encrypt.decrypt(user.getPassword()));
		return user;
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/adduser", produces = "application/json")
	public int addUser(@RequestBody User user) {
		user.setPassword(encrypt.encrypt(user.getPassword()));
		return userDao.addUser(user);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = "/updateuser", produces = "application/json")
	public int updateUser(@RequestBody User user) {
		user.setPassword(encrypt.encrypt(user.getPassword()));
		return userDao.updateUser(user);
	}

	@RequestMapping(method = { RequestMethod.DELETE }, value = "/deleteuser/{id}", produces = "application/json")
	public int deleteUser(@PathVariable(value = "id") int id) {
		return userDao.deleteUser(id);
	}
}
