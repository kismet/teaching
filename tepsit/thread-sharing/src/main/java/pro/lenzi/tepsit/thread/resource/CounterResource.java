package pro.lenzi.tepsit.thread.resource;

public class CounterResource {
	
	private int value;
	private int start;
	
	public CounterResource(int n) {
		value = n;
		start = n;
	}
	
	public synchronized void reset() {
		value = start;
	}
	
	public synchronized int getValue() {
		return value;
	}
	
	public synchronized int step(int n) {
		if(n<=0) throw new IllegalArgumentException();
		value += n;
		return value;
	}
}
