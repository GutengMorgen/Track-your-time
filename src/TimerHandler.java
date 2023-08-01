package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import src.Frames.Popup;

public class TimerHandler {
	//value in seconds
	private int period = 0;
	private Timer timer;
	private JLabel out;
	private String format = "Time to appear: %02d: %02d";

	public int getPeriod() {
		return period;
	}
	
	public void setPeriod(int periodMins) {
//		TODO: this.period = periodMins * 60;
		this.period = periodMins;
	}
	
	public void setLabel(JLabel out) {
		this.out = out;
	}
	
	private Timer timer() {
		if(timer == null) {
			timer = new Timer(1000, new ActionListener() {
				int seconds = getPeriod();
				int minutes = seconds / 60;
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(minutes == 0 && seconds == 0) {
						timer.stop();
						timer = null;
						runPopup();
					}
					
					else {
	                    if (seconds == 0) {
	                        minutes--;
	                        seconds = 59;
	                    }
	                    else
	                        seconds--;
	                }
					
					out.setText(String.format(format, minutes, seconds));
				}
			});
		}
		return timer;
	}
	
	public void start() {
		if(getPeriod() == 0) {
			runPopup();
			return;
		}
		
		if(!timer().isRunning())
			timer().start();
	}
	
	public void stop() {
		if(timer != null && timer.isRunning()) {

			timer().stop();
			timer = null; // to reset the timer
			out.setText(String.format(format, 0,0));
		}
	}
	
	private void runPopup() {
		new Popup().setVisible(true);
	}
}
