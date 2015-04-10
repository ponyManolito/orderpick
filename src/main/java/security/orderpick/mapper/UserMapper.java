package security.orderpick.mapper;

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
	User getUser(int id);
	
	@Insert("insert into users (name, password) values(#{name}, #{password})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addUser(User user);
}
