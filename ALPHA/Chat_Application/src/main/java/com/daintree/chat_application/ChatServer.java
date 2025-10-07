/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daintree.chat_application;

/**
 *
 * @author nasa
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    //variáveis da classe
        //Lista que registra quantos clients existem
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //novo server socket -> aceita os clientes 
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for clients...");
        
        //loop até que o server socket for fechado
        while (true) {
            //aceita o pedido do cliente
                //abre tcp connection
            Socket clientSocket = serverSocket.accept(); // halter server accepting code, aka it stops here for ever accepted client
            //but it doesnt stop the threads already running
            System.out.println("Client connected: " + clientSocket);
            
            //cira um objeto do clienthandler para cada clientsocket
            //passa como argumento o novo socket e a lista clients para que todo client handler tenha acesso à list de 
            //clientes existentes e portanto permite que para cada thread o serv
            ClientHandler clientThread = new ClientHandler(clientSocket, clients);
            clients.add(clientThread);
            new Thread(clientThread).start(); // start() makes run() the "new psvm" of that thread
        }
    }
}

class ClientHandler implements Runnable {
    //variáveis da classe
    private Socket clientSocket;
    private List<ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;
    
    //cosntrutor da classe
        //
    public ClientHandler(Socket socket, List<ClientHandler> clients) throws IOException {
        this.clientSocket = socket;
        this.clients = clients;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    // runnable interface method
    //each thread will be responsible for broadcasting to all clients the messages from the client it is affiliated with
    public void run() {
        try {
            //envia todas as mensagens que recebe pelo in para todos os clientes pelo out
            String inputLine;
            //while in is open it accepts the messages from its own client and sends it out to all other clients
            //blocks code execution waiting for message
            //returns null when tcp connection closed
            while ((inputLine = in.readLine()) != null) {
                for (ClientHandler aClient : clients) {
                    aClient.out.println(inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                //closes thread, in & out for when a client leavs
                clients.remove(this);//removes this clientthread from clients list
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
