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

import security.orderpick.datamodel.Turn;

@Component(TurnMapper.name)
public interface TurnMapper {

	public static final String name = "turnMapper";

	@Select("SELECT * FROM cf_turns WHERE id = #{id}")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "number", column = "NUMBER"),
			@Result(property = "time_init", column = "TIME_INIT"),
			@Result(property = "time_finish", column = "TIME_FINISH") })
	public Turn getTurn(int id);

	@Insert("insert into cf_turns (number, time_init, time_finish) values(#{number}, #{time_init}, #{time_finish})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public int addTurn(Turn turn);

	@Select("SELECT * FROM cf_turns")
	@Results(value = { @Result(property = "id", column = "ID"), @Result(property = "number", column = "NUMBER"),
			@Result(property = "time_init", column = "TIME_INIT"),
			@Result(property = "time_finish", column = "TIME_FINISH") })
	public List<Turn> getAll();

	@Update("update cf_turns set number=#{number}, time_init=#{time_init}, time_finish=#{time_finish} where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int updateTurn(Turn turn);

	@Delete("delete from cf_turns where id=#{id}")
	@Options(flushCache = true, useCache = true)
	public int deleteTurn(int id);
}
