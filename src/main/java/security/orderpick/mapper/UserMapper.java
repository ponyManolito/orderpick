package security.orderpick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import security.orderpick.datamodel.User;
import security.orderpick.datamodel.UserRole;

@Component(UserMapper.name)
public interface UserMapper {

	public static final String name = "userMapper";

	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "password", column = "PASSWORD"), @Result(property = "reg_date", column = "REG_DATE") })
	public User getUser(int id);

	@Insert("insert into users (name, password) values(#{name}, #{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addUser(User user);

	@Select("SELECT * FROM users")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "password", column = "PASSWORD"), @Result(property = "reg_date", column = "REG_DATE") })
	public List<User> getAll();
	
	@Select("SELECT name FROM roles")
	public List<String> getAllRoles();

	@Update("update users set name = #{name}, password = #{password} where id = #{id}")
	@Options(flushCache = true, useCache = true)
	public int updateUser(User user);

	@Delete("delete from users where id = #{id}")
	@Options(flushCache = true, useCache = true)
	public int deleteUser(int id);

	@Insert("insert into authorities(id_user,id_role) values((select id from users where name = #{user}),(select id from roles where name = #{role}))")
	public int addRoleAdmin(UserRole user);

	@Delete("delete from authorities where id_role = (select id from roles where name = #{role}) and id_user = #{id}")
	@Options(flushCache = true, useCache = true)
	public int deleteRoleAdmin(@Param("id") int id, @Param("role") String role);
	
	@Select("SELECT name from roles where id = (SELECT id_role FROM authorities where id_user = (select id from users where name = #{name}))")
	public String permision(String name);
	
	@Update("update authorities set id_role = (select id from roles where name = #{role})) where id_user =(select id from users where name = #{user})")
	@Options(flushCache = true, useCache = true)
	public int updateRoleAdmin(UserRole userRole);
} 
