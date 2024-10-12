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
package it.edu.marconipontedera.tepsit.two;

import java.util.ArrayList;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Persona implements Runnable {

	Pizza pizza;
	String gruppo;
	
	public Persona(Pizza pizza, String gruppo) {
		this.pizza = pizza; 
		this.gruppo = gruppo;
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
		tid = Thread.currentThread().getId();	 //Java 18 o inferiore
		int mangiati = 0;
		while( pizza.finita() == false ) {
			int pezzo = pizza.magia();
			if( pezzo > 0) {
				mangiati++;
				System.out.println(nome+"("+tid+"): Ha mangiato il pezzo "+pezzo+" del gruppo "+gruppo);
			}
		}
		System.out.println(nome+"("+tid+"): Ho mangiato "+mangiati+" ma pizza finita per il gruppo "+gruppo);
	}

}
