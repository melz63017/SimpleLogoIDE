package view;

import javafx.scene.paint.Color;

import javafx.collections.ObservableList;
import javafx.scene.Node;



public class CustomColorPalette extends Palette{

	private static final int SIZE = 20;

	public CustomColorPalette(ObservableList<Object> colorList) {
		super(colorList);
		super.paletteName = getResourceBundle().getString("CUSTOMCOLORS");
	}
	
	@Override
	public CustomColor getPaletteObject(int index){
		System.out.println("given ind: "+index);
		Color color = Color.web(getPaletteList().get(index).toString());
		return new CustomColor((int)(color.getRed()*255),(int)(color.getGreen()*255),(int)(color.getBlue()*255));
	}

	@Override
	public Node getPaletteObjectView(int index) {
		CustomColorElem colorView = new CustomColorElem((CustomColor) getPaletteObject(index),SIZE);
		
		return colorView.getView();
	}
}
