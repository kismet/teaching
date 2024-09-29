/*
* Teaching material for High School
* 
* Copyright (C) 2024  Stefano Lenzi <stefano@lenzi.pro>
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package it.edu.marconipontedera.tepsit.contatori.condiviso;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class ThreadContaCondiviso implements Runnable {

	GeneraNumeri valori;
	int max = Integer.MAX_VALUE;
	
	public ThreadContaCondiviso(GeneraNumeri generatore) {
		//this.valori = new GeneraNumeri();
		this.valori = generatore; 
	}
	
	public void setLimit(int lim) {
		max = lim;
	}
	
	//Per una corretta esecuzione quali metodi è NECESSARIO invocare prima di mettere 
	//il Thread nello stato di pronto?
	@Override
	public void run() {
		String nome = Thread.currentThread().getName();
		long tid;
		/*
		 * Le linee di codice che seguono sono equivalenti 
		 * a seconda della versione di Java (del runtime)
		 * è necessario usare l'una o l'altra
		 */
		tid = Thread.currentThread().threadId(); //Java 19 o superiore
		tid = Thread.currentThread().getId();	 //Java 18 o inferiore

		int i = valori.numero();
		while( i <= max ) {
			System.out.printf("%5d (%5s): Sto contando %d\n", tid, nome, i);				
			i = valori.numero();
			//Non esiste i++, perchè?
		}
	}

}
