package security.orderpick.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.dao.TypeDaoI;
import security.orderpick.dao.impl.TypeDaoImpl;
import security.orderpick.datamodel.Type;

@RestController
@RequestMapping("/types")
public class TypesController {

	@Resource(name = TypeDaoImpl.name)
	private TypeDaoI typeDao;

	@RequestMapping(method = { RequestMethod.GET }, value = "/getall", produces = "application/json")
	public List<Type> getAll() {
		return typeDao.getAll();
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/gettype", produces = "application/json")
	public Type getType(@RequestParam(value = "id") int id) {
		return typeDao.getType(id);
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/addtype", produces = "application/json")
	public int addType(@RequestBody Type type) {
		return typeDao.addType(type);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = "/updatetype", produces = "application/json")
	public int updateType(@RequestBody Type type) {
		return typeDao.updateType(type);
	}

	@RequestMapping(method = { RequestMethod.DELETE }, value = "/deletetype/{id}", produces = "application/json")
	public int deleteType(@PathVariable(value = "id") int id) {
		return typeDao.deleteType(id);
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/assigntype", produces = "application/json")
	public boolean assignType(@RequestParam(value = "name") String name,
			@RequestParam(value = "description") String description) throws Exception {
		return typeDao.assignType(name, description);
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/unassigntype", produces = "application/json")
	public boolean unassignType(@RequestParam(value = "name") String name) {
		return typeDao.unassignType(name);
	}
}
