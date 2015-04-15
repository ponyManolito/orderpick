package security.orderpick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import security.orderpick.datamodel.User;

public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results(value = {
		@Result(property="id", column="ID"),
		@Result(property="name", column="NAME"),
		@Result(property="password", column="PASSWORD"),
		@Result(property="reg_date", column="REG_DATE")
	})
	public User getUser(int id);
	
	@Insert("insert into users (name, password) values(#{name}, #{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addUser(User user);
	
	@Select("SELECT * FROM users")
	@Results(value = {
		@Result(property="id", column="ID"),
		@Result(property="name", column="NAME"),
		@Result(property="password", column="PASSWORD"),
		@Result(property="reg_date", column="REG_DATE")
	})
	public List<User> getAll();
}
