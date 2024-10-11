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
import java.util.Arrays;
import java.util.Iterator;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class CodaPrimaPrimi implements Buffer {
	
	private static final int DEFAULT_SIZE = 10;
	private static final int RANGE = 1024;
	private static int[] primes = null;
	private ArrayList<Integer> buffer;
	private int lastPrime;

	public CodaPrimaPrimi() {
		buffer = new ArrayList<Integer>(DEFAULT_SIZE);
		lastPrime = 0;
	}

	@Override
	public void add(int v) {
		boolean prime = isPrime(v);
		synchronized (buffer) {
			while(buffer.size() == DEFAULT_SIZE) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
				}
			}
			if(!prime) {
				buffer.add(v);
			} else {
				buffer.add(lastPrime, v);
				lastPrime++;
			}
			buffer.notify();
		}
	}

	private boolean isPrime(int v) {
		int[] cache = CodaPrimaPrimi.getPrimes();
		int idx = Arrays.binarySearch(cache, v);
		return idx >= 0;
	}

	private synchronized static int[] getPrimes() {
		if(primes != null) {
			return primes;
		}
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(2); 
		for(int i=3;i<RANGE;i+=2) {
			int d = -1;
			int stop = (int) (Math.sqrt(i)+1);
			boolean found = true;
			for (Iterator<Integer> j = values.iterator(); j.hasNext();) {
				d = (int) j.next();
				if(i % d == 0) {
					found = false;
					break;
				}
				if(d>=stop) break;
			}
			if( found ) {
				System.out.println("Trovato un NUOVO primo "+i);
				values.add(i);
			}
		}
		primes = new int[values.size()];
		int idx = 0;
		for (Iterator i = values.iterator(); i.hasNext();) {
			primes[idx] = (int) i.next();
			idx++;
		}
		return primes;
	}

	@Override
	public int remove() {
		synchronized (buffer) {
			while(buffer.size() == 0) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
				}
			}
			if( lastPrime > 0 ) lastPrime--;
			return buffer.removeFirst();			
		}
	}

}
