package com.daintree.serverhostapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;
    
    public ClientHandler(Socket socket, List<ClientHandler> clients) throws IOException {
        this.clientSocket = socket;
        this.clients = clients;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
        public void run() {
            try {
                String inputLine;
                
                //only returns null when in inpustream is closed aka in.close()
                while ((inputLine = in.readLine()) != null) {
                    for (ClientHandler aClient : clients) {
                        aClient.out.println(inputLine);
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } finally {
            try {
                in.close(); // idea to add button to close server -> not in alpha
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }   
        }
    }
}
