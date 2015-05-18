package security.orderpick.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import security.orderpick.datamodel.Turn;

@Component(TurnValidator.name)
public class TurnValidator implements Validator {

	public static final String name = "turnValidator";

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Turn turn = (Turn) arg0;
		if (turn.getTime_init() == null) {
			arg1.rejectValue("Time init", "Time init can't be null");
		}
		if (turn.getTime_finish() == null) {
			arg1.rejectValue("Time init", "Time finish can't be null");
		}
		if (!turn.getTime_finish().after(turn.getTime_init())) {
			arg1.rejectValue("Invalid time", "Time finish must be after than time init");
		}

	}

}
