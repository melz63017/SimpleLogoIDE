
package commands;

import java.util.List;

import model.Interpreter;



public class IfElse extends ControlCommand implements Executable {

	Interpreter interpreter;
	
	public IfElse(Interpreter interpreter) {
		this.interpreter = interpreter;
		numParams = 2;
	}
	
	public double execute(List<Object> params) {
		double ifCondition = (double) params.get(0);
		String trueCommands = (String) params.get(1);
		String falseCommands = (String) params.get(2);
		
		if (ifCondition != 0) {
			interpreter.run(trueCommands);
		}
		else {
			interpreter.run(falseCommands);
		}
		
		//NEED TO ADD IN RETURN VALUE
		return 0;
	}	
}
