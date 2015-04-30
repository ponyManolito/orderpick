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

import security.orderpick.datamodel.Table;

@Component(TableMapper.name)
public interface TableMapper {

	public static final String name = "tableMapper";

	@Select("SELECT * FROM cf_tables WHERE id = #{id}")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "available", column = "AVAILABLE") })
	public Table getTable(int id);
	
	@Select("SELECT * FROM cf_tables WHERE name = #{name}")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "available", column = "AVAILABLE") })
	public Table getTableByName(String name);
	
	@Select("SELECT * FROM cf_tables WHERE name = #{name} and available = 1")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "available", column = "AVAILABLE") })
	public Table getTableIfAvailable(String name);

	@Insert("insert into cf_tables (name, description, available) values(#{name}, #{description}, #{available})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addTable(Table table);

	@Select("SELECT * FROM cf_tables")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION"),
			@Result(property = "available", column = "AVAILABLE") })
	public List<Table> getAll();

	@Update("update cf_tables set name=#{name}, description=#{description}, available=#{available} where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int updateTable(Table table);

	@Delete("delete from cf_tables where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int deleteTable(int id);
}