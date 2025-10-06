package com.daintree.chatapplication;


import Client.ChatClient;
import Client.ChatGUI;
import Client.ChatLoginGUI_NU;
import Client.ChatRegisterGUI_NU;
import java.io.IOException;
import javax.swing.JOptionPane;

public class ChatApplication {
    private static String ipAddress;
    private static int port;
    
    private static ChatLoginGUI_NU chatLogin;
    private static ChatGUI chatPage;
    private static ChatRegisterGUI_NU chatRegister;
    
    private Runnable onLoginRegister;
    
    public void setOnLoginRegister(Runnable onLoginRegister) {
        this.onLoginRegister = onLoginRegister;
    }
    
    public static void main(String[] args) throws IOException{
        
        //String ipAddress = JOptionPane.showInputDialog(chatLogin.frame,"Enter your ip: ", "ip entry", JOptionPane.PLAIN_MESSAGE);
        //int port = Integer.parseInt((JOptionPane.showInputDialog(chatLogin.frame,"Enter the port: ", "port entry", JOptionPane.PLAIN_MESSAGE)));
        
        String ipAddress = "127.0.0.1";
        int port = 5000;
        
        chatLogin = new ChatLoginGUI_NU();
        //chatPage = new ChatGUI();
        //chatRegister = new ChatRegisterGUI();
        
        chatLogin.setOnLoginSuccess(() -> {
            chatPage = new ChatGUI();
            chatPage.frame.setVisible(true);
            System.out.println("login to chat");
        });
        /*chatLogin.setOnRegisterChange(() -> {    
            chatRegister = new ChatRegisterGUI();
            chatRegister.frame.setVisible(true);
            System.out.println("login to register");
            
        });
        */

        //register gui not working fuck this
        
        
        
        /*while (chatLogin.returnState() == 1) {
            chatLogin.frame.setVisible(true);
        } */ //crashed my computer 
        

       
        
        try {
            ChatClient client = new ChatClient(ipAddress, port);
            client.startClient();
            System.out.println("Connected to server");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(chatLogin.frame, "Error connecting to server", "Connection error", JOptionPane.PLAIN_MESSAGE);
        }

        
    }
}
