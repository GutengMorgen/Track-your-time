package src;

import src.Frames.MainFrame;

public class Singleton {
	private static Singleton intance;
	private MainFrame frame;
	
	private Singleton() {
		frame = null;
	}
	
	public static Singleton getInstance() {
		if(intance == null) {
			intance = new Singleton();
		}
		return intance;
	}
	
	public void setFrame(MainFrame mainFrame) {
		this.frame = mainFrame;
	}
	
	public MainFrame getFrame() {
		return frame;
	}
}
