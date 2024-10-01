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

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class DiscoClub {
	private int capienza;
	private int inside;
	
	public DiscoClub(int cap) {
		capienza = cap;
		inside = 0;
	}
	
	public synchronized int ballerini() {
		return inside;
	}
	
	public synchronized int free() {
		return capienza-inside;
	}
	
	public synchronized boolean entra() {		
		if(inside>=capienza) return false;
		inside++;
		return true;
	}

	public synchronized boolean esci() {		
		if(inside<0) return false;
		inside--;
		return true;
	}
}
