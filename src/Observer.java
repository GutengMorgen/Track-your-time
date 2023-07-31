package src;

import java.util.ArrayList;
import java.util.List;

interface Observer {
	void update(Boolean status);
}

class Subject {
	private Boolean status;
	private List<Observer> observers = new ArrayList<>();
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void setBoolean(Boolean newStatus) {
		if(status != newStatus) {
			status = newStatus;
			notifyObservers();
		}
	}
	
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(status);
		}
	}
}