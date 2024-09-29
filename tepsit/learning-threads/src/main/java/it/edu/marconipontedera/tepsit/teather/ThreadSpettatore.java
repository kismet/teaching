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
package it.edu.marconipontedera.tepsit.teather;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGeneratorFactory;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class ThreadSpettatore implements Runnable {

	private Biglietteria teatro;
	private boolean end = false;
	
	public ThreadSpettatore(Biglietteria teatro) {
		this.teatro = teatro;
	}

	public synchronized void termina() {
		end = true;
	}
	
	@Override
	public void run() {
		int cx = teatro.getNFile()/2;
		int cy = teatro.getNPosti()/2;
		long tid = Thread.currentThread().getId();
		while (!isEnd()) {
			Posto p = teatro.prenota(cx, cy);
			if( p == null ) {
				System.out.println(tid+": TUTTO ESAURITO");
				return;
			}
			System.out.println(tid+": Prenotato "+p);
			try {
				int n=ThreadLocalRandom.current().nextInt(19)+1;
				Thread.sleep(n*100);
			} catch (InterruptedException e) {
			}
		}

	}

	private synchronized boolean isEnd() {
		return end;
	}

}
