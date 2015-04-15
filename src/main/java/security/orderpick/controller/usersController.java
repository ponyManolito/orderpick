package security.orderpick.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.mapper.UserMapper;
import security.orderpick.datamodel.User;

@RestController
@RequestMapping("/users")
public class usersController {
	
	@Autowired
	private UserMapper userMapper ;
	
	@RequestMapping(value="/getall",produces="application/json")
	public List<User> getAll(){
	    return userMapper.getAll();
	}
	
	@RequestMapping(value="/getuser",produces="application/json")
	public User getUser(@RequestParam(value="id")int id){
	    return userMapper.getUser(id);
	}
}
