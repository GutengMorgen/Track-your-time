package src;

import java.util.List;
import javax.swing.JComboBox;

import src.Data.DataManager;

public class MyItems {
	private String tag;
	private String template;

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public MyItems() {
		
	}
	
	private MyItems(String tag, String template) {
		this.tag = tag;
		this.template = template;
	}
	
	/**
	 * set every line of the file Templates.csv as MyItems like Items of comboBox
	 */
	public void setItems(JComboBox<MyItems> comboBox) {
		List<String> lines = DataManager.linesTemplate();
		
		for (String line : lines) {
			String[] dataType = line.split(";");
			comboBox.addItem(new MyItems(dataType[0], dataType[1]));
		}
	}
	
	/**
	 * return the tag of MyItems
	 */
	@Override
	public String toString() {
		return tag;
	}
}
