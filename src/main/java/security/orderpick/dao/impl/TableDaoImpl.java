package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import security.orderpick.dao.TableDaoI;
import security.orderpick.datamodel.Table;
import security.orderpick.mapper.TableMapper;

@Component(TableDaoImpl.name)
public class TableDaoImpl implements TableDaoI {

	public static final String name = "tableDaoImpl";

	@Resource(name = TableMapper.name)
	private TableMapper tableMapper;

	@Override
	public List<Table> getAll() {
		return tableMapper.getAll();
	}

	@Override
	public Table getTable(int id) {
		return tableMapper.getTable(id);
	}

	@Override
	public int addTable(Table table) {
		return table.isNewTable() ? tableMapper.addTable(table) : tableMapper.updateTable(table);
	}

	@Override
	public int updateTable(Table table) {
		return tableMapper.updateTable(table);
	}

	@Override
	public int deleteTable(int id) {
		return tableMapper.deleteTable(id);
	}
	
	@Override
	public int assignTable(String name, String description) {
		Table table = tableMapper.getTableByName(name);
		if (table != null) {
			table = tableMapper.getTableIfAvailable(name);
			if (table != null) {
				description.replace("+", " ");
				table.setDescription(description);
				table.setAvailable(false);
				tableMapper.updateTable(table);
				return 0;
			} else {
				// Table not available
				return 2;
			}
		} else {
			// Table not found
			return 1;
		}
	}
}
