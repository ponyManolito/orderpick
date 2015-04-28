package security.orderpick.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import security.orderpick.dao.TurnDaoI;
import security.orderpick.datamodel.Turn;
import security.orderpick.mapper.TurnMapper;

@Component(TurnDaoImpl.name)
public class TurnDaoImpl implements TurnDaoI {

	public static final String name = "turnDaoImpl";

	@Resource(name = TurnMapper.name)
	private TurnMapper turnMapper;

	@Override
	public List<Turn> getAll() {
		return turnMapper.getAll();
	}

	@Override
	public Turn getTurn(int id) {
		return turnMapper.getTurn(id);
	}

	@Override
	public int addTurn(Turn turn) {
		return turn.isNewTurn() ? turnMapper.addTurn(turn) : turnMapper.updateTurn(turn);
	}

	@Override
	public int updateTurn(Turn turn) {
		return turnMapper.updateTurn(turn);
	}

	@Override
	public int deleteTurn(int id) {
		return turnMapper.deleteTurn(id);
	}
}
