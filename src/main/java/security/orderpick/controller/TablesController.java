package security.orderpick.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.dao.TableDaoI;
import security.orderpick.dao.impl.TableDaoImpl;
import security.orderpick.datamodel.Table;

@RestController
@RequestMapping("/tables")
public class TablesController {

	@Resource(name = TableDaoImpl.name)
	private TableDaoI tableDao;

	@RequestMapping(method = { RequestMethod.GET }, value = "/getall", produces = "application/json")
	public List<Table> getAll() {
		return tableDao.getAll();
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/gettable", produces = "application/json")
	public Table getTable(@RequestParam(value = "id") int id) {
		return tableDao.getTable(id);
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/addtable", produces = "application/json")
	public int addTable(@RequestBody Table table) {
		return tableDao.addTable(table);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = "/updatetable", produces = "application/json")
	public int updateTable(@RequestBody Table table) {
		return tableDao.updateTable(table);
	}

	@RequestMapping(method = { RequestMethod.DELETE }, value = "/deletetable/{id}", produces = "application/json")
	public int deleteTable(@PathVariable(value = "id") int id) {
		return tableDao.deleteTable(id);
	}
	
	@RequestMapping(method = { RequestMethod.POST }, value = "/assignTable", produces = "application/json")
	public boolean assignTable(@RequestParam(value = "name") String name, @RequestParam(value = "description") String description) throws Exception {
		return tableDao.assignTable(name, description);
	}
}
