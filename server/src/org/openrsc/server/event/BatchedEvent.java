package org.openrsc.server.event;

import org.openrsc.server.model.Player;

public abstract class BatchedEvent extends DelayedEvent {

	private long maximumAttempts = calculateActionAttempts();
	
	private int attempts = 0;
	
	public BatchedEvent(Player owner, int delay) {
		super(owner, delay);
	}
	
	public final void action() {
		if (attempts > maximumAttempts) {
			stop();
			return;
		}
		
		attempts += 1;
		run();
	}

	protected abstract long calculateActionAttempts();

	protected abstract void doAction();

}
