package pro.lenzi.tepsit.arraysort.algorithm;

public class OrderingThread implements Runnable {

	private String[] data;
	private int from;
	private int to;
	
	public OrderingThread(String[] data, int from, int to) {
		super();
		this.data = data;
		this.from = from;
		this.to = to;
	}

	
	private void bublesort() {
		for (int j = from; j < to; j++) {
			for (int i = j+1; i < to; i++) {
				if(data[j].compareTo(data[i])>0) {
					String aux = data[i];
					data[i] = data[j];
					data[j] = aux;
				}
			}
		}
	}	
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		long start = System.currentTimeMillis();
		System.out.printf("%20s: Started ordering\n",name);
		bublesort();
		long delta= System.currentTimeMillis()-start;
		System.out.printf("%20s: Ordering completed in %5d ms\n",name, delta);
	}

}
