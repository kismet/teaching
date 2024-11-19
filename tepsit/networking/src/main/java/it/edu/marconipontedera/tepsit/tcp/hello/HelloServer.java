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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloServer {
	
	
	public static final int CLIENT_DELAY = 200;

	public static void attendi(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
	
	
	static class NClients {
		int i = 0;
		
		synchronized void inc() {
			i++;
		}
		
		synchronized void dec() {
			i--;
		}
		
		synchronized int get() {
			return i;
		}
	}
	
	static class ClientHandler implements Runnable {

		Socket link;
		NClients counter;
		
		public ClientHandler(Socket s, NClients v) {
			this.link = s;
			this.counter = v;
		}
		
		public void cleanup() {
			try {
				link.close();
			} catch (IOException e) {
			}
			counter.dec();
		}
		
		@Override
		public void run() {
			BufferedReader reader = null; 
			PrintWriter writer = null;
			String message = null;
			try {
				reader = new BufferedReader(new InputStreamReader(link.getInputStream()));
				writer = new PrintWriter(link.getOutputStream(), true);
			}catch(IOException ex) {
				ex.printStackTrace();
				System.out.println("Client died too early, cleaning up the mess!");
				cleanup();
				return;
			}
			
			while("".equalsIgnoreCase(message) == false) {
	            try {
					message = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
	            // Legge il messaggio dal client
	            System.out.println("Messaggio ricevuto dal client: " + message);
	
	            //Verifico se il messaggio è valido e rispondo solo in quel caso
	            if ("Hello".equalsIgnoreCase(message)) {
	                // Calcola un tempo casuale basato sul numero di client attivi
	                int delay = counter.get() * CLIENT_DELAY; // Millisecondi
	                System.out.println("Tempo di attesa per rispondere: " + delay + " ms");
	
	                // Attende per il tempo calcolato
	                attendi(delay);
	
	                // Invia la risposta al client
	                writer.println("World");
	                System.out.println("Risposta inviata al client: World");
	            } else {
	            	//Tentativo di hacking - chiudo la connessione
	            	writer.println("You are trying to hack into private system");
	            	writer.println("All your data are logged and they will sent to the authority");
	            	writer.println("for investigation.");
	            	
	            	writer.println("You are:"+link.getRemoteSocketAddress());
	            }
			}
        	try {
				reader.close();
	        	writer.close();
			} catch (IOException e) {
			}
        	cleanup();
        	
		}
	}
	
    public static void main(String[] args) {
        int port = 12345; // Porta su cui il server ascolta
        NClients clients = new NClients();

        try {
        	ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server avviato. In attesa di connessioni...");
        	
            while (true) {
                // Accetta e aspetta la connessione di un client una nuova connessione dal client
                Socket client = serverSocket.accept();
                System.out.println("Nuovo client connesso.");
                clients.inc();
                Thread handler = new Thread(new ClientHandler(client,clients));
                handler.start();
            }
        
        } catch (Exception e) {
            System.err.println("Errore del server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
