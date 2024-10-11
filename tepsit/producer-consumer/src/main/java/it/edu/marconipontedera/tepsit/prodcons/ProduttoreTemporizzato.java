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
package it.edu.marconipontedera.tepsit.prodcons;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import it.edu.marconipontedera.tepsit.buffer.Buffer;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class ProduttoreTemporizzato implements Runnable {

	private static final int RANGE = 1024;
	private static final int[] DEFAULT_DELAYS = 
			new int[] {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

	private int limit;
	private Buffer buffer;
	private int[] delays;
	
	
	public ProduttoreTemporizzato(Buffer b, int l) {
		this.limit = l;
		this.buffer = b;
		this.delays = DEFAULT_DELAYS;
	}
	
	public void setDelays(int[] delays) {
		this.delays = Arrays.copyOf(delays, delays.length);
	}
	
	public int[] getDelays() {
		return Arrays.copyOf(delays, delays.length);
	}
	
	@Override
	public void run() {
		int i = 0;
		ThreadLocalRandom random = ThreadLocalRandom.current();
		while (i < limit) {
			int value = random.nextInt(RANGE);
			buffer.add(value);
			i++;
			long time = getDelayTime();
			sleepAtLeast(time);
		}

	}

	private void sleepAtLeast(long time) {
		long now = System.currentTimeMillis();
		long end=now+time;
		while(now<end) {
			try {
				Thread.sleep(end-now);
			} catch (InterruptedException e) {
			}
			now = System.currentTimeMillis();
		}		
	}

	private long getDelayTime() {
		ThreadLocalRandom randomGenerator = ThreadLocalRandom.current();
		int max = delays.length;
		int idx = randomGenerator.nextInt(max);
		return delays[idx];
	}

}
