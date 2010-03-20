package com.twowire;

import com.twowire.create.Ticket;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(Ticket ticket);
}
