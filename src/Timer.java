package src;

import java.util.concurrent.TimeUnit;

public class Timer {
	//value in seconds
	private int display =  30;

	public int getDisplay() {
		return display;
	}
	
	public void setDisplay(int displayMin) {
		int displayseconds = displayMin * 60;
		this.display = displayseconds;
	}

	public void start(Popup frame) {
		boolean x = true;
		long startTime = System.currentTimeMillis();
		
		try {
			while (x) {
				TimeUnit.SECONDS.sleep(1);
				long timepassed = System.currentTimeMillis() - startTime;
				long seconds = timepassed / 1000;
				if(seconds == getDisplay()) {
					x = false;
	                System.out.println("timer stop in: " + seconds + " seconds");
	                frame.setVisible(true);
				} 
//				else {
//					System.out.println("timer: " + seconds);
//				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
