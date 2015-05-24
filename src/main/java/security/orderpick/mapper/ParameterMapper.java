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

import security.orderpick.datamodel.Parameter;
import security.orderpick.datamodel.User;
import security.orderpick.datamodel.UserRole;

@Component(ParameterMapper.name)
public interface ParameterMapper {

	public static final String name = "parameterMapper";

	@Select("SELECT * FROM cf_parameters WHERE name = #{name}")
	@Results(value = { @Result(property = "name", column = "NAME"),@Result(property = "value", column = "VALUE")})
	public Parameter getParameter(String name);
	
	@Insert("insert into cf_parameters (name, value) values(#{name}, #{value})")
	@Options(useGeneratedKeys = false)
	public int addParameter(Parameter parameter);

	@Select("SELECT * FROM cf_parameters")
	@Results(value = {@Result(property = "name", column = "NAME"),@Result(property = "value", column = "VALUE")})
	public List<Parameter> getAll();

	@Update("update cf_parameters set value = #{value} where name = #{name}")
	@Options(flushCache = true, useCache = true)
	public int updateUser(Parameter user);

	@Delete("delete from cf_parameters where name = #{name}")
	@Options(flushCache = true, useCache = true)
	public int deleteParameter(String name);
}
