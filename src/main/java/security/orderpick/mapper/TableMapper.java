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
			@Result(property = "description", column = "DESCRIPTION")})
	public Table getTable(int id);

	@Insert("insert into cf_tables (name, description) values(#{name}, #{description})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addTable(Table table);

	@Select("SELECT * FROM cf_tables")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
			@Result(property = "description", column = "DESCRIPTION")})
	public List<Table> getAll();

	@Update("update cf_tables set name=#{name}, description=#{description} where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int updateTable(Table table);

	@Delete("delete from cf_tables where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int deleteTable(int id);
}