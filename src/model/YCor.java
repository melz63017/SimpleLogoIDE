package model;

import java.util.List;

import controller.TurtleController;

public class YCor extends Command implements Executable{

	public YCor(TurtleController turtleController) {
		numParams = 0;
	}
	
	public double execute(List<Object> params) {
		// need to figure out how to communicate with front-end
		return 0;
	}
	
	public String checkParamTypes(List<Object> params) {
		return null;
	}
	
	
}