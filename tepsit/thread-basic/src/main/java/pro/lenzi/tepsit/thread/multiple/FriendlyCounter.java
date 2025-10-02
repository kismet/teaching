package pro.lenzi.tepsit.thread.multiple;

public class FriendlyCounter implements Runnable {

	private static final int NUMBER_PER_ROWS = 10;
	private static final long SLEEPY_MITIGATION = 21;
	
	//Che visibilità hanno questi attributi?
	int inizio;
	int fine;
	int passo;
	
	boolean friendly = false;
//	private int corrente;
	
	public FriendlyCounter(int inizio, int fine, int passo) {
		super();
		this.inizio = inizio;
		this.fine = fine;
		this.passo = passo;
	}
	
	public void beNice() {
		friendly = true;
	}
	
	public void conta() {
		int max = 0;
		for(int corrente=inizio;corrente<fine;corrente+=passo) {
			System.out.printf("%4d ",corrente);
			if(max % NUMBER_PER_ROWS == 0) {
				System.out.println();
			}
			if (friendly) {
				try {
					Thread.sleep(SLEEPY_MITIGATION);
				} catch (InterruptedException e) {
					//ignoring exception
				}
			}
			max++;
		}
	}

	@Override
	public void run() {
		conta();
	}
	
}
