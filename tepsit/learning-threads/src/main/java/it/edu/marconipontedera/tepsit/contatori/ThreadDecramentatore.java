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
package it.edu.marconipontedera.tepsit.contatori;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class ThreadDecramentatore implements Runnable {

	private int limit;
	
	public ThreadDecramentatore(int l) {
		this.limit = l;
	}
	
	public void setLimit(int l) {
		this.limit = l;
	}
	
	public int getLimit() {
		return limit;
	}
	
	@Override
	public void run() {
		long tid;
		for (int i = limit; i >= 1; i--) {
			String nome = Thread.currentThread().getName();
			/*
			 * Le linee di codice che seguono sono equivalenti 
			 * a seconda della versione di Java (del runtime)
			 * è necessario usare l'una o l'altra
			 */
			tid = Thread.currentThread().threadId(); //Java 19 o superiore
			tid = Thread.currentThread().getId();	 //Java 18 o inferiore
			
			System.out.printf("%5d (%5s): Sto contando %d\n", tid, nome, i);
		}
		

	}

}
