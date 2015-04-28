package security.orderpick.dao;

import java.util.List;

import security.orderpick.datamodel.Turn;

public interface TurnDaoI {

	public List<Turn> getAll();

	public Turn getTurn(int id);

	public int addTurn(Turn turn);

	public int updateTurn(Turn turn);

	public int deleteTurn(int id);
}
