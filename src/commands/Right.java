package commands;

import java.util.List;

import controller.TurtleController;

public class Right extends Command implements Executable {

	TurtleController turtleTracker;
	public Right(TurtleController turtleController) {
		turtleTracker = turtleController;
		numParams = 1;
	}
	
	public double execute(List<Object> params) {
		double changeDegrees = (Double) params.get(0) % 360;
		turtleTracker.changeCurrentAgentOrientation(changeDegrees);
	
		return changeDegrees;
	}
	
	public String checkParamTypes(List<Object> params) {
		Object paramValue = params.get(0);
		if (paramValue instanceof Integer || paramValue instanceof Double) {
			return null;
		}
		else {
			return String.format(errors.getString("WrongParamType"), paramValue.toString());
		}
	}
	
	
}