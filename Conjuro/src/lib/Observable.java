package lib;
import java.util.*;

public class Observable {
	private ArrayList<IObserver> observers = new ArrayList<IObserver>();
	
	public void addObserver(IObserver pObserver) {
		observers.add(pObserver);
	}
	
	public void notifyObservers(Object pData) {
		for(IObserver observer: observers) {
			observer.notify(pData);
		}
	}
}
