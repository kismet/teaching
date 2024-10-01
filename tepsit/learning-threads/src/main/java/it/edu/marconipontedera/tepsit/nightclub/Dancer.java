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
package it.edu.marconipontedera.tepsit.nightclub;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Dancer implements Terminable {

	private static final int COUNT_DELAY = 150;
	private DiscoClub disco;
	private boolean run = true;
	private boolean inside = false;
	
	public Dancer(DiscoClub night) {
		this.disco = night;
	}
	
	@Override
	public void run() {
		long tid = Thread.currentThread().getId();
		System.out.println(tid+": Iniziano le danze!");
		
		while (shouldRun()) {
			int n = ThreadLocalRandom.current().nextInt(5)+1;
			try {
				Thread.sleep(COUNT_DELAY*n);
			} catch (InterruptedException e) {
			}
			if (n % 2 == 1 && inside ) {
				disco.esci();
				inside = false;
				System.out.println(tid+": Uscito");
			}
			if (n % 2 == 1 && !inside ) {
				if( disco.entra() ) {
					inside  = true;
					System.out.println(tid+": Entrato");
				}
			}
		}
		
		System.out.println(tid+": La disco chiude si va casa...");
	}

	@Override
	public synchronized void termina() {
		run = false;
	}
	
	private synchronized boolean shouldRun() {
		return run ;
	}

}
