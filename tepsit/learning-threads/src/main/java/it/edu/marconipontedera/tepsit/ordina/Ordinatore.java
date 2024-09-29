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

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public interface Ordinatore {
	
	/**
	 * Ordina i dati contenuti nell'array data che sono compresi tra la posizione
	 * all'indice from e la posizione all'indice to end (escluso)
	 * 
	 * @param data
	 * @param from
	 * @param end
	 * @return il tempo impiegato per ordinare
	 */
	public long ordina(int[] data, int from, int end);

}
