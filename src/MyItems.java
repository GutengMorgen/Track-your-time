package src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

public class MyItems {
	private String name;
	private String template;
	
	public static void main(String[] args) {
		MyItems myItems = new MyItems();
		myItems.reaMap2();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public MyItems() {
		
	}
	
	public MyItems(String name, String template) {
		this.name = name;
		this.template = template;
	}

	public Map<String, MyItems> reaMap(){
		Map<String, MyItems> map = new HashMap<>();
		MainFrame mainFrame = new MainFrame();
		String[] tags = mainFrame.getTags();
		
		for (String tag : tags) {
			System.out.println(tag);
		}
		
		return map;
	}
	
	public String reaMap2(){
		Map<String, MyItems> map = new HashMap<>();
		MainFrame mainFrame = new MainFrame();
		String[] tags = mainFrame.getTags();
		
		for (String tag : tags) {
			map.put(tag, new MyItems(tag, "default template"));
//			System.out.println(tag);
		}
		
		return "";
	}
	
	public void setItems(JComboBox<MyItems> comboBox, MainFrame mainFrame) {
		List<String> tags = DataManager.linesTemplate(DataManager.TAG);
		
		for (String tag : tags) {
			System.out.println(tag);
			comboBox.addItem(new MyItems(tag, "default template"));
		}
		
//		MyItems myItems = (MyItems) comboBox.getSelectedItem();
//		System.out.println(myItems.name + " " + myItems.template);
//		System.out.println(comboBox.getSelectedItem());
	}
	
	@Override
	public String toString() {
		return name;
	}
}
