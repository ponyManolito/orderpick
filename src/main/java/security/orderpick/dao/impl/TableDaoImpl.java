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
	public boolean assignTable(String name, String description) throws Exception {
		Table table = tableMapper.getTableByName(name);
		if (table != null) {
			table = tableMapper.getTableIfAvailable(name);
			if (table != null) {
				description = description.replace("+", " ");
				table.setDescription(description);
				table.setAvailable(false);
				tableMapper.updateTable(table);
				return true;
			} else {
				// Table not available
				throw new Exception("Mesa ya asignada");
			}
		} else {
			// Table not found
			throw new Exception("Numero de mesa no existente");
		}
	}
	
	@Override
	public boolean unassignTable(String name) {
		Table table = tableMapper.getTableByName(name);
		if (table != null) {
			table.setDescription("");
			table.setAvailable(true);
			tableMapper.updateTable(table);
			return true;
		}
		return false;
	}
}
