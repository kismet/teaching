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

import java.time.Duration;
import java.util.Scanner;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Main {

	private static final int N_THREAD = 20;

	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);

			
		Thread[] threads = new Thread[N_THREAD];
		Biglietteria teatro = new Biglietteria();
		ThreadSpettatore[] pubblico = new ThreadSpettatore[N_THREAD];
		for (int i = 0; i < threads.length; i++) {
			pubblico[i] = new ThreadSpettatore(teatro);
			threads[i] = new Thread(pubblico[i]);
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
		}
		
		for (int i = 0; i < threads.length; i++) {
			pubblico[i].termina();			
		}
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}			
		}
	}

}
