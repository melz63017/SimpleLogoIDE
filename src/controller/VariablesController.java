package controller;
import view.VariableElem;
import view.VariablesView;
import java.util.*;

import model.VariableModel;


public class VariablesController extends Controller implements Observer {
	private VariableModel model;
	private VariablesView view;
	
	public VariablesController(VariableModel model, VariablesView view) {
		this.model = model;
		this.view = view;
	}
	
	public void addVariable(String name, String value){
		model.addVariable(name,value);
		
		HashMap<String,Object> varMap = model.getVariables();
		ArrayList<VariableElem> varList = new ArrayList<VariableElem>();
		for(String key: varMap.keySet()){
			varList.add(new VariableElem(key,varMap.get(key).toString(),this));
		}
		view.update(varList);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg=="FIELDCHANGED"){
			addVariable(((VariableElem)o).getName(),((VariableElem)o).getValue());
		}
	}
	
	public Object getVariable(String name) { 
		return model.getVariable(name) == null? null: model.getVariable(name);
	}

}