package commands;

import java.util.List;

public class NotEqual extends Command implements Executable {

	public NotEqual() {
		numParams = 2;
	}
	
	public Object execute(List<Object> params) {
		if (params.get(0) instanceof Double && params.get(1) instanceof Double) {
			return ((Double) params.get(0)).compareTo((Double) params.get(1)) != 0 ? 1 : 0;
		}
		else if (params.get(0) instanceof Double) {
			return logicForFirstExprArrayOnly((double[]) params.get(1), (double) params.get(0));
		}
		else if (params.get(1) instanceof Double) {
			return logicForFirstExprArrayOnly((double[]) params.get(0), (double) params.get(1));
		}
		else {
			return logicForFirstExprAndSecondExprArray((double[]) params.get(0), (double[]) params.get(1));
		}
	}
	
	private double[] logicForFirstExprAndSecondExprArray(double[] firstExprArray, double[] secondExprArray) {
		for (int i=0; i<firstExprArray.length; i++) {
			firstExprArray[i] = ((Double) firstExprArray[i]).compareTo((Double) secondExprArray[i]) != 0 ? 1 : 0;
		}
		return firstExprArray;
	}
	
	private double[] logicForFirstExprArrayOnly(double[] firstExprArray, double secondExprVal) {
		for (int i=0; i<firstExprArray.length; i++) {
			firstExprArray[i] = ((Double) firstExprArray[i]).compareTo((Double) secondExprVal) != 0 ? 1 : 0;
		}
		return firstExprArray;
	}
	
	public String checkParamTypes(List<Object> params) {
		for (Object param : params) {
			if (!(param instanceof Integer || param instanceof Double)) {
				return String.format(errors.getString("WrongParamType"), param.toString());
			}	
		}
		return null;
	}
	
}
