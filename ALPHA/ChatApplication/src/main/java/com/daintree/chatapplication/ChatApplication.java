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
    
    private static ChatLoginGUI chatLogin;
    private static ChatGUI chatPage;
    private static ChatRegisterGUI chatRegister;
    
    private Runnable onLoginRegister;
    
    public void setOnLoginRegister(Runnable onLoginRegister) {
        this.onLoginRegister = onLoginRegister;
    }
    
    public static void main(String[] args) throws IOException{
        
        //String ipAddress = JOptionPane.showInputDialog(chatLogin.frame,"Enter your ip: ", "ip entry", JOptionPane.PLAIN_MESSAGE);
        //int port = Integer.parseInt((JOptionPane.showInputDialog(chatLogin.frame,"Enter the port: ", "port entry", JOptionPane.PLAIN_MESSAGE)));
        
        String ipAddress = "127.0.0.1";
        int port = 5000;
        
        chatLogin = new ChatLoginGUI();
        //chatPage = new ChatGUI();
        //chatRegister = new ChatRegisterGUI();
        
        chatLogin.setOnLoginSuccess(() -> {
            chatPage = new ChatGUI();
            chatPage.frame.setVisible(true);
            System.out.println("login to chat");
        });
        chatLogin.setOnRegisterChange(() -> {    
            chatRegister = new ChatRegisterGUI();
            chatRegister.frame.setVisible(true);
            System.out.println("login to register");
            
        });
        //have to fix, cant go from register back to login
        chatRegister.frame.setVisible(true);
        chatRegister.setOnLoginChange(() -> {
            System.out.println("dafasdf");
        });
        
        
        
        /*while (chatLogin.returnState() == 1) {
            chatLogin.frame.setVisible(true);
        } */ //crashed my computer 
        

       
        
        try {
            ChatClient client = new ChatClient(ipAddress, port);
            client.startClient();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(chatLogin.frame, "Error connecting to server", "Connection error", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
