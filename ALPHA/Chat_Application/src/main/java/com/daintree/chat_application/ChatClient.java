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
import java.util.function.Consumer;

public class ChatClient {
    //varriáveis da classe
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    //interface que espera um string e fará alguma coisa
        //recebe um string e faz alguma coisa
        //no chat application onMessageReceived mostra a imagem na tela
    private Consumer<String> onMessageReceived;

    //constructor of the class
    public ChatClient(String serverAddress, int serverPort, Consumer<String> onMessageReceived) throws IOException {
        //socket object that will be referenced when client is made
        this.socket = new Socket(serverAddress, serverPort);
        //bufferedreader object that will be used to receive socket information
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //pritnwriter object sends information to socket
        this.out = new PrintWriter(socket.getOutputStream(), true);
        //this interface will end up displaying all messages sent
        this.onMessageReceived = onMessageReceived;
    }
    
    //uses out printwriter to send information to socket
    public void sendMessage(String msg) {
        out.println(msg);
    }

    //starts client
    public void startClient() {
        //new tread
        new Thread(() -> {
            try {
                //while in object is open it accepts and diplays information on screen
                String line;
                while ((line = in.readLine()) != null) {
                    onMessageReceived.accept(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //starts thread for client to display messages on screen
        }).start();
    }
}