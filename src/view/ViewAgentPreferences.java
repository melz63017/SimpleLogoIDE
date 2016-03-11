package view;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import GUI.GuiObject;
import GUI.GuiObjectFactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


/**
 * This class is an extension of the View abstract class. It will display all the Agents properties and will be user interactive. 
 * @author Melissa Zhang
 *
 */
public class ViewAgentPreferences extends View{
	private HashMap<Integer, Agent> agentMap;
	private Group viewGroup;
	private IntegerProperty currentAgentNameProperty;
	private HBox customColorBox;
	private List<HBox> customColorBoxes;
	private CustomColorPalette colorPalette;
	List<GuiObject> guiObjects;
	
	private static final int COLOR_CELL_SIZE = 10;

	private static final String UPDATE_PROPERTIES = "updateObserver";
	private static final int CONSOLEX = 0;
	private static final int CONSOLEY = MENU_OFFSET;
//	private HashMap<String, Agent> agentMap;
	private VBox allPreferencesBox = new VBox();

	private static final int PADDING = 15;
	private Pane pane;

	private ResourceBundle updateResources;
	private ResourceBundle cssResources = ResourceBundle.getBundle("CSSClasses");
	
	public ViewAgentPreferences(ViewType ID, Preferences savedPreferences) {
		super(ID, savedPreferences);
		setX(CONSOLEX);
		setY(CONSOLEY);
		setPane(allPreferencesBox);
		allPreferencesBox.getStyleClass().addAll(cssResources.getString("DISPLAYVIEW"),cssResources.getString("VBOXVIEW"));

		agentMap = new HashMap<Integer,Agent>();
		currentAgentNameProperty = new SimpleIntegerProperty();

		addListenerToCurrentAgentProperty(currentAgentNameProperty);

		updateResources = ResourceBundle.getBundle(UPDATE_PROPERTIES);
		guiObjects = new ArrayList<>();

	}

	private void addListenerToCurrentAgentProperty(IntegerProperty property) {
			property.addListener(new ChangeListener<Object>(){

			@Override
			public void changed(ObservableValue<? extends Object> ov,
					Object oldValue, Object newValue) {
					updateView();
			}
		});
	}

	@Override
	public void update(Observable agent, Object updateType) {
		if (updateType == updateResources.getString("COLORPALETTE") ){
			updateView();
		}else if (updateType == updateResources.getString("IMAGEPALETTE")){
			updateView();
		}
		
	}

	@Override
	public Pane getView() {
		updateView();
		return super.getView();
	}
	private void updateView() {

		allPreferencesBox.getChildren().clear();
		allPreferencesBox.setPrefSize(NARROW_WIDTH, WIDE_WIDTH);
		
//<<<<<<< HEAD
		setUpAgentDropDown();		
//=======
//		setUpAgentDropDown();
//		
//>>>>>>> refs/remotes/origin/master
		VBox observerBox = new VBox();
		allPreferencesBox.getChildren().add(observerBox);

		List<Node> observerLabelList = new ArrayList<Node>();
		List<Node> mutableGuiObjectList = new ArrayList<Node>();

		
		if(currentAgentNameProperty.getValue()!=null && currentAgentNameProperty.getValue() != 0){
			populateObserverLabelList(agentMap.get(currentAgentNameProperty.getValue()), observerLabelList);
			populateMutableGuiObjectList(agentMap.get(currentAgentNameProperty.getValue()),mutableGuiObjectList);
			
			addToAgentPrefBox(observerBox, observerLabelList);
			addToAgentPrefBox(allPreferencesBox,mutableGuiObjectList);
			}
	}

	private void setUpAgentDropDown() {
		ComboBox<String> agentDropDown = new ComboBox<String>();
		for (Integer name: agentMap.keySet()){
			agentDropDown.getItems().add(""+name);
		}
		agentDropDown.setValue("TURTLE "+currentAgentNameProperty.getValue());
		agentDropDown.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String oldValue, String newValue) {                
            	currentAgentNameProperty.setValue(Integer.parseInt(newValue));
				updateView();
            }
		});
		allPreferencesBox.getChildren().add(agentDropDown);
	}

	private void addToAgentPrefBox(Pane agentPrefBox,List<Node> ObjectList) {
		for (Object object: ObjectList){
			agentPrefBox.getChildren().add((Node)object);
		}
	}

	private void populateMutableGuiObjectList(Agent agent, List<Node> mutableGuiObjectList) {
		GuiObjectFactory objectFactory = new GuiObjectFactory();
		for (String property: agent.getMutableProperties()){
			Object guiObject= objectFactory.createNewGuiObject(property,agent);
			if (guiObject!= null){
				mutableGuiObjectList.add((Node) ((GuiObject) guiObject).createObjectAndReturnObject());
				guiObjects.add((GuiObject) guiObject);
			}
		}
	}

	private void populateObserverLabelList(Agent agent, List<Node> observerLabelList) {
		for (String property: agent.getObserverProperties()){
			ObserverLabelFactory labelFactory = new ObserverLabelFactory();
			Object newLabel = labelFactory.createNewObserverLabel(property, agent);
			
			if (newLabel!= null){
				observerLabelList.add((Node) newLabel);
			}
		}
	}
	public void updateAgentMap(HashMap<Integer,Agent> newAgentMap){
		agentMap = (HashMap<Integer, Agent>) newAgentMap;
		updateView();
	}


	public IntegerProperty getCurrentAgentNameProperty() {
		return currentAgentNameProperty;
	}

	public void updateCurrentAgentSelection() {
		updateView();
	}

	public CustomColorPalette getColorPalette() {
		return colorPalette;
	}
}
