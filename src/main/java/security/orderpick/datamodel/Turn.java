package security.orderpick.datamodel;

import java.sql.Time;
import java.util.Date;

import security.orderpick.datamodel.common.Entity;

public class Turn extends Entity {

	private short number;

	private Time time_init;

	private Time time_finish;

	public Turn() {}

	public Turn(short number, Time time_init, Time time_finish) {
		super();
		this.number = number;
		this.time_init = time_init;
		this.time_finish = time_finish;
	}

	public Turn(int id, short number, Time time_init, Time time_finish, Date reg_date) {
		setId(id);
		this.number = number;
		this.time_init = time_init;
		this.time_finish = time_finish;
		setReg_date(reg_date);
	}

	public short getNumber() {
		return number;
	}

	public void setNumber(short number) {
		this.number = number;
	}

	public Time getTime_init() {
		return time_init;
	}

	public void setTime_init(Time time_init) {
		this.time_init = time_init;
	}

	public Time getTime_finish() {
		return time_finish;
	}

	public void setTime_finish(Time time_finish) {
		this.time_finish = time_finish;
	}

	public boolean isNewTurn() {
		return getId() == 0;
	}

}
