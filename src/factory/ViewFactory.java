package factory;

import java.util.*;

import controller.TurtleController;
import controller.VariableController;
import javafx.scene.paint.Color;
import model.Interpreter;
import model.VariableModel;
import view.ConsoleView;
import view.HistoryView;
import view.MethodView;
import view.VariableView;
import view.View;
import view.ViewAgents;
import view.ViewAgentPreferences;
import view.ViewWindowPreferences;

public class ViewFactory {
//	private static HashMap<String,View> allViews = new HashMap<String,View>();
	private HistoryView historyView; 
	private VariableView variableView; 
	private ConsoleView consoleView; 
	private MethodView methodView; 
	private ViewAgents agentsView; 
	private ViewAgentPreferences preferencesView;
	private ViewWindowPreferences windowPreferencesView;
	
	public View createView(String ID){
		switch(ID){
		case "Console":
			consoleView = new ConsoleView(ID,historyView);
			return consoleView;
		case "History":
			historyView = new HistoryView(ID);
			return historyView;
		case "Variables":
			variableView = new VariableView(ID);
			return variableView;
		case "Methods":
			methodView = new MethodView(ID);
			return methodView;
		case "Agent":
			agentsView = new ViewAgents(ID);
			return agentsView;
		case "Preferences":
			preferencesView = new ViewAgentPreferences(ID);
			return preferencesView;
		case "WindowPreferences":
			windowPreferencesView = new ViewWindowPreferences(ID);
			return windowPreferencesView;
		default:
			return null;
		}
		//allViews.put(ID, view);
		//return view;
	}

}
