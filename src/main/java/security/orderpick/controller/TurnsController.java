package security.orderpick.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import security.orderpick.dao.TurnDaoI;
import security.orderpick.dao.impl.TurnDaoImpl;
import security.orderpick.datamodel.Turn;
import security.orderpick.validation.TurnValidator;

@RestController
@RequestMapping("/turns")
public class TurnsController {

	@Resource(name = TurnDaoImpl.name)
	private TurnDaoI turnDao;

	@Resource(name = TurnValidator.name)
	private TurnValidator turnValidator;

	@RequestMapping(method = { RequestMethod.GET }, value = "/getall", produces = "application/json")
	public List<Turn> getAll() {
		return turnDao.getAll();
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/getturn", produces = "application/json")
	public Turn getTurn(@RequestParam(value = "id") int id) {
		return turnDao.getTurn(id);
	}

	@RequestMapping(method = { RequestMethod.POST }, value = "/addturn", produces = "application/json")
	public int addTurn(@RequestBody Turn turn, Errors error) throws Exception {
		turnValidator.validate(turn, error);
		if (error.hasErrors()) {
			List<ObjectError> errors = error.getAllErrors();
			String errosString = "";
			for (ObjectError objectError : errors) {
				if (!errosString.isEmpty()) {
					errosString += errosString;
				}
				errosString += objectError.getDefaultMessage();
			}
			throw new Exception(errosString);
		}
		return turnDao.addTurn(turn);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = "/updateturn", produces = "application/json")
	public int updateTurn(@RequestBody Turn turn, Errors error) throws Exception {
		turnValidator.validate(turn, error);
		if (error.hasErrors()) {
			List<ObjectError> errors = error.getAllErrors();
			String errosString = "";
			for (ObjectError objectError : errors) {
				if (!errosString.isEmpty()) {
					errosString += errosString;
				}
				errosString += objectError.getDefaultMessage();
			}
			throw new Exception(errosString);
		}
		return turnDao.updateTurn(turn);
	}

	@RequestMapping(method = { RequestMethod.DELETE }, value = "/deleteturn/{id}", produces = "application/json")
	public int deleteTurn(@PathVariable(value = "id") int id) {
		return turnDao.deleteTurn(id);
	}
}
