package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerHandler {
	//value in seconds
	private int period =  30;

	public int getPeriod() {
		return period;
	}
	
	public void setPeriod(int displayMin) {
		int displayseconds = displayMin * 60;
		this.period = displayseconds;
	}

	public void getSeconds(JLabel out) {
		Timer timer = new Timer(1000, new ActionListener() {
			
			int seconds = getPeriod();
			int minutes = seconds / 60;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(minutes == 0 && seconds == 0)
					((Timer) e.getSource()).stop();
				
				else {
                    if (seconds == 0) {
                        minutes--;
                        seconds = 59;
                    }
                    else {
                        seconds--;
                    }
                }
				
				out.setText(String.format("%02d: %02d", minutes, seconds));
			}
		});
		
		timer.start();
	}
	
	public void start(Popup frame) {
		boolean x = true;
		long startTime = System.currentTimeMillis();
		
		try {
			while (x) {
				TimeUnit.SECONDS.sleep(1);
				long timepassed = System.currentTimeMillis() - startTime;
				long seconds = timepassed / 1000;
				if(seconds == getPeriod()) {
					x = false;
//	                System.out.println("timer stop in: " + seconds + " seconds");
	                frame.setVisible(true);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
