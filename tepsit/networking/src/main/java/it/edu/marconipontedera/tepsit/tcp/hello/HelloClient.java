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
package it.edu.marconipontedera.tepsit.tcp.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class HelloClient {

	private static final int MAX_TRY = 3;
	
	public static void attendi(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) {
		// Configura l'indirizzo IP e la porta del server
		String serverAddress = "127.0.0.1"; // IP del server (localhost)
		int port = 12345; // Porta del server

		Socket link = null;
		int i = 0;
		
		while( link == null && i < MAX_TRY ) {
			try {
				// Crea una connessione al server
				link = new Socket(serverAddress, port);
			}catch(IOException ex) {
				ex.printStackTrace();
				attendi((long) (1000*Math.pow(2, i)));
			}
			i++;
		}
		
		if( link == null ) {
			System.out.println("Impossibile collegarsi al server");
			return;
		}
		
		try {

				// Ottiene l'output stream per inviare dati
				OutputStream out = link.getOutputStream();

				// Ottiene l'output stream per ricevere dati
				InputStream in = link.getInputStream();

				// Usa un PrintWriter per inviare RIGHE di testo al server
				PrintWriter writer = new PrintWriter(out, true);
				// Usa un BufferReader per leggere RIGHE di testo al server
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				for (int j = 0; j < 5; j++) {
					long send = System.currentTimeMillis();
					writer.println("Hello");				
					System.out.println("Messaggio inviato al server: Hello");
					
					
					System.out.println(reader.readLine());
					long receive = System.currentTimeMillis();
					
					System.out.println("Ricevuta risposta dopo:"+(receive-send)+"ms");				
					attendi((long) (250*Math.pow(2, j)));
				}
				
				//Inviamo linea vuota per indicare la fine di comunicazione
				writer.println();
				
		} catch (Exception e) {
			// Gestisce eventuali errori
			System.err.println("Errore: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
