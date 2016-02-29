
package model;

import java.util.List;

import controller.TurtleController;

public class YCor extends Command implements Executable{

	TurtleController turtleController;
	public YCor(TurtleController turtleController) {
		turtleController = turtleController;
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		return turtleController.getCurrentAgentYPosition();
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
}