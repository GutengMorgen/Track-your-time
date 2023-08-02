package src.Data;

import java.util.List;
import javax.swing.JComboBox;

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
	 * Set every line of the file Templates.csv as MyItems in a comboBox
	 * 
	 * @param comboBox to set the MyItems as Items
	 */
	public void addItems(JComboBox<MyItems> comboBox) {
		List<String> lines = TaggedManager.readLines("Templates.csv");

		for (String line : lines) {
			String[] filter = line.split(";");
			comboBox.addItem(new MyItems(filter[0], filter[1]));
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
