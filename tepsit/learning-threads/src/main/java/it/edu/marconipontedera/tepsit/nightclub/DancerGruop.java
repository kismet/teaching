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
public class DancerGruop implements Terminable {

	private static final int COUNT_DELAY = 150;
	private DiscoClub disco;
	private boolean run = true;
	private boolean inside = false;
	private int size;
	
	public DancerGruop(DiscoClub night, int size) {
		this.disco = night;
		this.size = size;
	}
	
	/*
	 * Un implementazione corretta di questa funzione sarebbe stata quella
	 * di estendere la classe DiscoClub per fornire dei metodi atomici
	 * entra(int n) ed esci(int n)
	 */
	private boolean entriamo() {
		/*
		 * Il codice che segue NON è una buona pratica, perchè stiamo
		 * delegando il controllo dell'integrità della struttura (la
		 * discoteca) a chi utilizza la discoteca, tuttavia è un esempio 
		 * per comprendere meglio i MONITOR (il blocco synchronized)
		 * 
		 * Infatti abbiamo:
		 * 1 - L'uso del metodi sincronizzati disco.free() e disco.entra() 
		 *     non generano deadlock perchè il Thread l'accesso esclusivo 
		 *     all'oggetto *disco* è stato fornito dal monitor all'ingresso
		 *     del monitor (riga #58)
		 * 2 - Il codice non fa uso di wait() e notify() perchè si accetta
		 *     che l'operazione non vada a buon fine, è il Thread che invoca
		 *     il metodo "entriamo()" che ritenterà ed eseguire l'operazione
		 *     sarà cura del programmatore evitare di utilizzare attesa-attiva
		 */
		synchronized (disco) { 
			//Superando synchronized si ottiene l'accesso esclusivo allo
			//oggetto disco
			if(disco.free() > size) {
				for (int i = 0; i < size; i++) {
					disco.entra();
				}	
				return true;
			}else {
				return false;
			}
		}
	}
	
	private void tuttiFuori() {
		for (int i = 0; i < size; i++) {
			disco.esci();
		}		
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
				tuttiFuori();
				inside = false;
				System.out.println(tid+": Usciti in "+size);
			}
			if (n % 2 == 1 && !inside ) {
				if(!entriamo()) continue;
				inside  = true;
				System.out.println(tid+": Entrati in "+size);
			}
		}
		
		System.out.println(tid+": La disco chiude si va casa...");
	}


	public synchronized void termina() {
		run = false;
	}
	
	private synchronized boolean shouldRun() {
		return run ;
	}

}
