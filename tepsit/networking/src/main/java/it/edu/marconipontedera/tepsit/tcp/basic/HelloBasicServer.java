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
package it.edu.marconipontedera.tepsit.tcp.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class HelloBasicServer {

	public static void main(String[] args) {
        int port = 12345; // Porta su cui il server ascolta
        String message = null;

        ServerSocket server;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
        System.out.println("Server avviato. In attesa di connessioni...");
		System.out.println("Server in ascolto su "+server.getLocalSocketAddress());
		System.out.println("Server in ascolto su "+server.getInetAddress()+":"+server.getLocalPort());

        try {
            	
            while (true) {
                // Accetta e aspetta la connessione di un client una nuova connessione dal client
                Socket link = server.accept();
                System.out.println("Nuovo client connesso.");
                
				BufferedReader reader = new BufferedReader(new InputStreamReader(link.getInputStream()));
				PrintWriter writer = new PrintWriter(link.getOutputStream(), true);

                message = reader.readLine();
				
	            if ("Hello".equalsIgnoreCase(message)) {
	                // Calcola un tempo casuale basato sul numero di client attivi
	            	long delay = 1000;
	                System.out.println("Tempo di attesa per rispondere: " + delay + " ms");
	
	                Thread.sleep(delay);
	
	                // Invia la risposta al client
	                writer.println("World");
	                System.out.println("Risposta inviata al client: World");                    
	            }
            }
            
        } catch (Exception e) {
            System.err.println("Errore del server: " + e.getMessage());
            e.printStackTrace();
        }        
    }
}
