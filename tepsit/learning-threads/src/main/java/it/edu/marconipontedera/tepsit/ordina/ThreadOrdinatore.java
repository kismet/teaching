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
package it.edu.marconipontedera.tepsit.ordina;

/**
 * Questa classe ordina un array in-place, la classe fa uso del pattern
 * <b>Delegator</b> per rendere personalizzabile l'algoritmo di ordinamento
 * da usare.
 * L'algoritmo d'ordinamento predefinito è {@link OrdinamentoSemplice}, ma 
 * si può modificare usando il metodo {@link #setAlgoritmoOrdinamento(Ordinatore)}
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class ThreadOrdinatore implements Runnable {

	int[] target;
	int s, e;
	Ordinatore algoritmo = new OrdinamentoSemplice();
	
	public ThreadOrdinatore(int[] v, int s, int e) {
		this.target = v;
		this.s = s;
		this.e = e;
	}
	
	public void setAlgoritmoOrdinamento(Ordinatore o) {
		this.algoritmo = o;
	}
	
	public int getStart() {
		return s;
	}
	
	public int getEnd() {
		return e;
	}
	
	public int getSize() {
		return e-s;
	}
	
	public int getValue(int idx) {
		if (idx >= getSize() || idx < 0) {
			throw new IndexOutOfBoundsException(idx);
		}
		return target[s+idx];
	}
	
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

		System.out.println(nome+"("+tid+"): Started");
		System.out.println(nome+"("+tid+"): Ordering data from "+s+" to "+e);
		long tempo = algoritmo.ordina(target,s,e);
		System.out.println(nome+"("+tid+"): Ordering completed. It took "+tempo+"ms");
	}

}
