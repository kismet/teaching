package pro.lenzi.tepsit.thread.single;

public class Contatore {

	private static final int NUMBER_PER_ROWS = 10;
	
	//Che visibilità hanno questi attributi?
	private int inizio;
	private int fine;
	private int passo;
	
	public Contatore(int inizio, int fine, int passo) {
		this.inizio = inizio;
		this.fine = fine;
		this.passo = passo;
	}
	
	public void conta() {
		int max = 0;
		for(int i=inizio;i<fine;i+=passo) {
			System.out.printf("%4d ",i);
			if(max % NUMBER_PER_ROWS == 0) {
				System.out.println();
			}
			max++;
		}
	}
	
}
