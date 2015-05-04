package security.orderpick.dao;

import java.util.List;

import security.orderpick.datamodel.Table;

public interface TableDaoI {

	public List<Table> getAll();

	public Table getTable(int id);

	public int addTable(Table table);

	public int updateTable(Table table);

	public int deleteTable(int id);
	
	public boolean assignTable(String name, String description) throws Exception;
}
