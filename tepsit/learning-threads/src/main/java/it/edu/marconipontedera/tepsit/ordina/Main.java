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
package it.edu.marconipontedera.tepsit.ordina;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class Main {

	private static final int N_THREAD = 4;
	private static final int DATA_SIZE = 313;
	private static final int POLLING_TIME = 250;
	

	public static void main(String[] args) {
		int t, n;
		Scanner cin = new Scanner(System.in);

		System.out.println("Quanti elementi generare?\n -> ");
		n = cin.nextInt();
		
		int[] vet = new int[DATA_SIZE];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = ThreadLocalRandom.current().nextInt(DATA_SIZE*5);
		}
		System.out.println(Arrays.toString(vet));
		
		Thread[] threads = new Thread[N_THREAD];
		ThreadOrdinatore[] ordinatori = new ThreadOrdinatore[N_THREAD]; 
		int len = vet.length / N_THREAD;
		int i;
		
		Ordinatore alg = new OrdinamentoSemplice();
		for (i = 0; i < threads.length-1; i++) {
			ordinatori[i] = new ThreadOrdinatore(vet, i*len, len);
			threads[i] = new Thread(ordinatori[i]);
			ordinatori[i].setAlgoritmoOrdinamento(alg);			
		}
		ordinatori[i] = new ThreadOrdinatore(vet, i*len, vet.length);
		ordinatori[i].setAlgoritmoOrdinamento(alg);
		
		threads[i] = new Thread(ordinatori[i]);
		
		for (int j = 0; j < threads.length; j++) {
			threads[j].start();
		}

		/*
		 * NOTA:
		 * Questa parte è inefficiente, l'algoritmo corretto dovrebbe iniziare
		 * a fare il merge appena finisce "un thread"
		 */
		
		for (int j = 0; j < threads.length; j++) {
			try {
				threads[j].join();
			} catch (InterruptedException e) {
			}
		}
		vet = mergeAll(vet,ordinatori);
		System.out.println(Arrays.toString(vet));
		
	}


	// Questo 
	private static int[] mergeAll(int[] vet, ThreadOrdinatore[] ordinatori) {
		int[] dst = new int[vet.length];
		//indice della prima posizione libera dentro dst
		int idx = 0;
		
		//array con tutti gli indici delle "sotto-array"
		int[] indici = new int[ordinatori.length];
		//inizializzo tutti gli indici a 0
		for (int i = 0; i < indici.length; i++) {
			indici[i] = 0;
		}
		
		//fino a quando non ho riempito tutta la destinazione
		while(idx<dst.length) {
			
			//Cerco in quale "sotto-array" si trova il minimo
			
			//idxMin contiene l'indice del sotto-array che ha il primo elemento
			//		minimo da estrarre (quindi può avere un valore tra 0 e 3)
			int idxMin = -1;
			for (int i = 0; i < indici.length; i++) {
				if(indici[i]<ordinatori[i].getSize()) {
					//Se non è finito
					idxMin = i;
					i = indici.length; //Equivale a fare un break
				}
			}
			for (int i = 0; i < indici.length; i++) {
				if(indici[i]<ordinatori[i].getSize()) {
					//Se non è finito
					if(ordinatori[i].getValue(indici[i]) < ordinatori[idxMin].getValue(indici[idxMin])) {
						//Ho trovato un nuovo minimo
						idxMin = i;
					}					
				}				
			}
			
			
			//Selezione il thread che contiene il valore più piccolo
			ThreadOrdinatore parte = ordinatori[idxMin];
			//Indice da cui leggere
			int idxParte = indici[idxMin];
			dst[idx] = parte.getValue(idxParte);
			
			//consumo la posizione idxParte
			indici[idxMin]++;
			
			//preparo la posizione per il prossimo libero in dst
			idx++;
		}
		
		return dst;
	}

}
