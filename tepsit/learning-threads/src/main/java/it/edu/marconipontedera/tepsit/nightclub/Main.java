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

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Main {

	private static final long WATCHER_DELAY = 1000;
	private static final int DURATA_SIMULAZIONE = 10;

	public static void main(String[] args) {
		int t, n;
		Scanner cin = new Scanner(System.in);

		int mode = -1;
		do {
			System.out.print("Quale modalità: gruppi o singoli? (0-Singoli/1-Gruppi)\n -> ");
			mode = cin.nextInt();
		}while(mode < 0 || mode > 1);
		
		System.out.print("Quanti ballerini o gruppi di ballerini vuoi simulare?\n -> ");
		t = cin.nextInt();

		System.out.print("Qual'è la capienza della discoteca?\n -> ");
		n = cin.nextInt();

		
		DiscoClub nightclub = new DiscoClub(n);
		Thread[] threads = new Thread[t];
		Terminable[] ballerini = new Terminable[t];
		
		for (int i = 0; i < threads.length; i++) {
			ballerini[i] = null;
			if(mode == 0) {
				ballerini[i] = new Dancer(nightclub);
			}else if(mode == 1){
				int group = ThreadLocalRandom.current().nextInt(20)+1;
				ballerini[i] = new DancerGruop(nightclub,group);
			}
			threads[i] = new Thread(ballerini[i]);
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
		for (int i = 0; i < DURATA_SIMULAZIONE; i++) {
			System.out.println(" SIM-DISCO ");
			System.out.println("-----------");
			System.out.println(nightclub.ballerini()+"/"+n);
			System.out.println();
			try {
				Thread.sleep(WATCHER_DELAY);
			} catch (InterruptedException e) {
			}
		}
		
		for (int i = 0; i < threads.length; i++) {
			ballerini[i].termina();
		}
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println(" SIM-DISCO ");
		System.out.println("-----------");
		System.out.println(nightclub.ballerini()+"/"+n);
		System.out.println();

	}

}
