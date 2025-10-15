package pro.lenzi.util;

public class Threads {

	/**
	 * Un metodo di utilità per far aspettare "esattamente" il tempo
	 * indicato, senza preoccuparsi di interruzioni
	 * 
	 * @param ms
	 */
	public static void realSleep(long ms) {
		long end=System.currentTimeMillis()+ms;
		do {
			long delta = end - System.currentTimeMillis();
			try {
				if(delta > 0) Thread.sleep(delta);
			} catch (InterruptedException e) {
				//Ignoring interrupt we MUST wait the time specified
			}
		}while(end > System.currentTimeMillis());
	}

	/**
	 * @see #realSleep(long)
	 */
	public static void realSleep(int ms) {
		realSleep(ms);
	}
	
	public static void joinAll(Thread[] threads) {
		for (int j = 0; j < threads.length; j++) {
			try {
				if(threads[j].isAlive()) {
					threads[j].join();
				}
			} catch (InterruptedException e) {
				//ignoring exception but restaring to check
				j = 0;
			}
		}				
	}
}
