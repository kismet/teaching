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
package it.edu.marconipontedera.tepsit.contatori;

import java.util.Scanner;

/**
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Main {

	public static void contaCrescente(int t, int n) {
		Thread[] contatori = new Thread[t];

		for (int i = 0; i < contatori.length; i++) {
			ThreadContatore c = new ThreadContatore(n);
			contatori[i] = new Thread(c);
		}

		for (int i = 0; i < contatori.length; i++) {
			contatori[i].start();
		}
	}
	
	public static void contaMultipli(int t, int n) {
		Thread[] contatori = new Thread[t];

		for (int i = 0; i < contatori.length; i++) {
			ThreadContatoreMultipli c = new ThreadContatoreMultipli(n);
			contatori[i] = new Thread(c);
		}

		for (int i = 0; i < contatori.length; i++) {
			contatori[i].start();
		}
	}

	public static void contaMetaCrescenteMetaDecrescente(int t, int n) {
		Thread[] contatori = new Thread[t];

		for (int i = 0; i < contatori.length; i++) {
			if (i < contatori.length / 2) {
				ThreadContatore c = new ThreadContatore(n);
				contatori[i] = new Thread(c);
			} else {
				ThreadDecramentatore c = new ThreadDecramentatore(0);
				contatori[i] = new Thread(c);
				c.setLimit(n);
			}
		}

		for (int i = 0; i < contatori.length; i++) {
			contatori[i].start();
		}
	}

	public static void main(String[] args) {
		int t, n;
		Scanner cin = new Scanner(System.in);

		System.out.println("Quanti thread vuoi creare?\n -> ");
		t = cin.nextInt();

		System.out.println("Qual'è il valore massimo a cui contare?\n -> ");
		n = cin.nextInt();

		contaCrescente(t, n);
		contaMetaCrescenteMetaDecrescente(t, n);
		contaMultipli(t, n);

	}

}
