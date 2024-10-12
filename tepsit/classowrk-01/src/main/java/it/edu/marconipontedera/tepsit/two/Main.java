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
package it.edu.marconipontedera.tepsit.two;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Main {

	private static final int SLICE = 8;


	public static void contaCrescente(int t, int n) {
		Thread[] persone = new Thread[t];

		//Nota: questa soluzione nel caso in cui ci sono piu' pizze che persone
		//assegna una sola pizza a persona, si lascia al lettore pensare a come 
		//risolvere usando questo modello la possibilità di mangiare più pizze
		int dimGruppi = t / n + 1;
		
		int nPizze=1;
		Pizza generatore = new Pizza(SLICE);
		for (int i = 0; i < persone.length; i++) {
			//Se scrivo questo qui GeneraNumeri generatore = new GeneraNumeri(), 
			//e tolgo la riga 10 cosa succede?	
			if(i % dimGruppi == 0) {
				System.out.println("Pizza "+nPizze+" assegnata");
				generatore = new Pizza(SLICE);
				nPizze++;
			}
			Persona c = new Persona(generatore, "Pizza-"+nPizze);
			persone[i] = new Thread(c);
		}

		for (int i = 0; i < persone.length; i++) {
			persone[i].start();
		}
	}
	

	public static void main(String[] args) {
		int t, n;
		Scanner cin = new Scanner(System.in);
		System.out.println("Quante pizze abbiamo a disposizione?\n -> ");
		n = cin.nextInt();

		System.out.println("Quante persone a cena?\n -> ");
		t = cin.nextInt();


		contaCrescente(t, n);

	}

}
