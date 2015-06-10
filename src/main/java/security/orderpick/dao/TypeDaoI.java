package security.orderpick.dao;

import java.util.List;

import security.orderpick.datamodel.Type;

public interface TypeDaoI {

	public List<Type> getAll();

	public Type getType(int id);

	public int addType(Type type);

	public int updateType(Type type);

	public int deleteType(int id);

	public boolean assignType(String name, String description) throws Exception;

	public boolean unassignType(String name);
}
