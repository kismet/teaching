/*
* 
* Teaching material for High School of ITI 'G.Marconi'
* locate in Pontedera, Pisa, Italy 
*
* Material for Computer Science educational path 
*
* Copyright (C) 2024 Stefano Lenzi
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
package it.edu.marconipontedera.tepsit.buffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * Ci sono varie modalità di implementazione, sicuramente la più semplice è quella con un numero 
 * costante di Consumatori, che devono essere registrati al momento della creazione e riutilizzare
 * {@link CodaSemplice} per implementare tanti buffer separati ogniungo per ogni consumatore
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class CodaSempliceMulticast implements Buffer {
	
	private HashMap<Thread, CodaSemplice> codeByThread = new HashMap<Thread, CodaSemplice>();

	public CodaSempliceMulticast(int size, Thread[] c) {
		for (int i = 0; i < c.length; i++) {
			codeByThread.put(c[i], new CodaSemplice(size));
		}
	}
	
	
	@Override
	public void add(int v) {
		ArrayList<CodaSemplice> todos = new ArrayList<CodaSemplice>(codeByThread.values());
		while(todos.isEmpty() == false) {
			boolean allFull = true;
			for (Iterator iter = todos.iterator(); iter.hasNext();) {
				CodaSemplice coda = (CodaSemplice) iter.next();
				if(coda.isFull() == false) {
					coda.add(v);
					allFull = false;
					iter.remove();
				}
			}
			if(allFull) todos.remove(0).add(v);
		}
	}

	
	//RIFLESSIONI:
	//Cosa succede in questo caso se aggiungiamo un Thread che non è parte di quelli registrati al momento 
	//della creazione ?
	@Override
	public int remove() {
		CodaSemplice coda = codeByThread.get(Thread.currentThread());
		if(coda == null) {
			throw new IllegalCallerException(Thread.currentThread()+" was not declared as consumer of this data");
		}
		return coda.remove();
	}

}
