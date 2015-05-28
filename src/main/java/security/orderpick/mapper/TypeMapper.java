package security.orderpick.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import security.orderpick.datamodel.Type;

@Component(TypeMapper.name)
public interface TypeMapper {

	public static final String name = "typeMapper";

	@Select("SELECT * FROM cf_types WHERE id = #{id}")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "available", column = "AVAILABLE") })
	public Type getType(int id);

	@Select("SELECT * FROM cf_types WHERE name = #{name}")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "available", column = "AVAILABLE") })
	public Type getTypeByName(String name);

	@Select("SELECT * FROM cf_types WHERE name = #{name} and available = 1")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "available", column = "AVAILABLE") })
	public Type getTypeIfAvailable(String name);

	@Insert("insert into cf_types (name, description, available) values(#{name}, #{description}, #{available})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addType(Type type);

	@Select("SELECT * FROM cf_types")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "available", column = "AVAILABLE") })
	public List<Type> getAll();

	@Update("update cf_types set name=#{name}, description=#{description}, available=#{available} where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int updateType(Type type);

	@Delete("delete from cf_types where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int deleteType(int id);
}