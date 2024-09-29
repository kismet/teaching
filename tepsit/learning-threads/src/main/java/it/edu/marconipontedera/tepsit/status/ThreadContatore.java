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
package it.edu.marconipontedera.tepsit.status;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class ThreadContatore implements Runnable {

	private static final int COUNT_DELAY = 120;
	private int limit;
	private ValareCondiviso generatore;
	
	public ThreadContatore(int l, ValareCondiviso g) {
		this.limit = l;
		this.generatore = g;
	}
	
	@Override
	public void run() {
		
		int i = generatore.next();
		while (i <= limit) {
			try {
				Thread.sleep(COUNT_DELAY);
			} catch (InterruptedException e) {
			}
			i = generatore.next();
		}

	}

}
