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
package it.edu.marconipontedera.tepsit.one;

/**
 * Un interfaccia per poter realizzare dei Buffer di communicazione tra Thread
 * sarà l'implementazione a definire l'ordine in cui i valori vengono inseriti
 * e rimossi dal buffer.<br>
 * 
 * L'implementazione deve garantire che la clase sia <b><i>Thread-safe</i></>: 
 * quindi senza <b>deadlock</b> o <b>race-condition</b>
 * 
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public interface Buffer {
	
	/**
	 * Aggiunge un valore al buffer
	 * 
	 * @param v
	 */
	public void add(int v);
	
	/**
	 * 
	 * @return estrae (e rimuove) un valore dal buffer
	 */
	public int remove();

}
