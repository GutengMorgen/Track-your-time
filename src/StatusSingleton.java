package src;

public class StatusSingleton {
	private static StatusSingleton intance;
	private MainFrame frame;
	
	private StatusSingleton() {
		frame = null;
	}
	
	public static StatusSingleton getInstance() {
		if(intance == null) {
			intance = new StatusSingleton();
		}
		return intance;
	}
	
	public void setStatus(MainFrame mainFrame) {
		this.frame = mainFrame;
	}
	
	public MainFrame getStatus() {
		return frame;
	}
}
