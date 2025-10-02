package pro.lenzi.tepsit.thread.resource;

import pro.lenzi.thread.util.ThreadTool;

public class CounterActor implements Runnable {

	private static final long SLEEPY_MITIGATION = 21;
	
	private int fine;
	private int passo;
	
	private CounterResource contatore;
	
	public CounterActor(CounterResource c, int fine, int passo) {
		this.contatore = c;
		this.fine = fine;
		this.passo = passo;
	}
		
	public void conta() {
		final String whoAmI = Thread.currentThread().getName();
		int c = contatore.step(passo);
		while(c<fine) {
			System.out.printf("%-20s %4d\n",whoAmI, c);
			c = contatore.step(passo);
		}
	}

	
	
	@Override
	public void run() {
		conta();
	}
	
}
