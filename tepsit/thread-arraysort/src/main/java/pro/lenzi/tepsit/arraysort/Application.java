package pro.lenzi.tepsit.arraysort;

import pro.lenzi.tepsit.arraysort.algorithm.NativeSort;
import pro.lenzi.tepsit.arraysort.algorithm.SingleBubleSort;
import pro.lenzi.tepsit.arraysort.algorithm.ThreadBubleSort;

public class Application {


	public static void main(String[] args) {
		String[] params = new String[] {"random-grades.csv"}; 
		NativeSort.main(params);
		SingleBubleSort.main(params);
		ThreadBubleSort.main(params);
	}

}









