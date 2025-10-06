package com.daintree.chatapplication;


import Client.ChatClient;
import Client.ChatGUI;
import Client.ChatLoginGUI;
import Client.ChatRegisterGUI;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ChatApplication {
    private static String ipAddress;
    private static int port;
    
    public static void main(String[] args) throws IOException{
        
        ChatLoginGUI chatLogin = new ChatLoginGUI();
        while (chatLogin.returnState() == 1) {
            chatLogin.frame.setVisible(true);
        } 
        if (chatLogin.returnState() == 0){
            String ipAddress = JOptionPane.showInputDialog(chatLogin.frame,"Enter your ip: ", "ip entry", JOptionPane.PLAIN_MESSAGE);
            int port = Integer.parseInt((JOptionPane.showInputDialog(chatLogin.frame,"Enter the port: ", "port entry", JOptionPane.PLAIN_MESSAGE)));
        }
        
        try {
            ChatClient client = new ChatClient(ipAddress, port);
            client.startClient();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(chatLogin.frame, "Error connecting to server", "Connection error", JOptionPane.PLAIN_MESSAGE);
        }
        
        
        ChatGUI chatPage = new ChatGUI();
        

        
        ChatRegisterGUI chatRegister = new ChatRegisterGUI();

        

    }
}
