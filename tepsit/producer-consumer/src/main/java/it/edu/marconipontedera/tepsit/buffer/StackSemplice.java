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

import java.util.Arrays;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class StackSemplice implements Buffer {
	
	private static final int DEFAULT_SIZE = 10;
	private int[] buffer;
	private int last;

	public StackSemplice() {
		buffer = new int[DEFAULT_SIZE];
		last = 0;
	}

	@Override
	public void add(int v) {
		int pos = -1;
		synchronized (buffer) {
			while(last == buffer.length) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
				}
			}
			pos = last;
			last++;
		}
		buffer[pos] = v;
		synchronized (buffer) {
			buffer.notify();			
		}
	}

	@Override
	public int remove() {
		int v = -1;
		synchronized (buffer) {
			while(last == 0) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
				}
			}
//			System.out.println(			Arrays.toString(buffer));
			last--;
			v = buffer[last];
//			System.out.println(v+" "+last);
			buffer.notify();			
		}
		return v;
	}

}
