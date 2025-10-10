package pro.lenzi.util;

public class Threads {

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

	public static void realSleep(int ms) {
		realSleep(ms);
	}
	
}
