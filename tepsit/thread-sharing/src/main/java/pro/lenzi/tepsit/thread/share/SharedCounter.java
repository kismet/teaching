package pro.lenzi.tepsit.thread.share;

import pro.lenzi.thread.util.ThreadTool;

public class SharedCounter implements Runnable {

	private static final long SLEEPY_MITIGATION = 21;
	
	private int inizio;
	private int fine;
	private int passo;
	
	private int corrente;
	
	public SharedCounter(int inizio, int fine, int passo) {
		super();
		this.inizio = inizio;
		this.fine = fine;
		this.passo = passo;
		this.corrente = inizio;
	}
		
	public void conta() {
		final String whoAmI = Thread.currentThread().getName();
		synchronized (this) {
			corrente = inizio;
		}
		while(corrente<fine) {
			System.out.printf("%-20s %4d\n",whoAmI, corrente);
			ThreadTool.realSleep(SLEEPY_MITIGATION);
			synchronized (this) {
				corrente += passo;
			}
		}
	}

	public void contaFixStart() {
		final String whoAmI = Thread.currentThread().getName();
		while(corrente<fine) {
			System.out.printf("%-20s %4d\n",whoAmI, corrente);
			ThreadTool.realSleep(SLEEPY_MITIGATION);
			synchronized (this) {
				corrente += passo;
			}
		}
	}

	public void contaFixStartPrintAll() {
		final String whoAmI = Thread.currentThread().getName();
		while(corrente<fine) {
			int mine;
			synchronized (this) {
				mine = corrente;
				corrente += passo;				
			}
			System.out.printf("%-20s %4d\n",whoAmI, mine);
			ThreadTool.realSleep(SLEEPY_MITIGATION);
		}
	}
	
	
	@Override
	public void run() {
		conta();
//		contaFixStart();
//		contaFixStartPrintAll();
	}
	
}
