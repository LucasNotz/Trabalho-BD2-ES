package com.daintree.chatapplication;


import Client.ChatGUI;
import Client.ChatLoginGUI;

public class ChatApplication {
    
    public static void main(String[] args) {
        ChatGUI chatPage = new ChatGUI();
        chatPage.frame.setVisible(true);
        
        ChatLoginGUI chatLogin = new ChatLoginGUI();
        chatLogin.frame.setVisible(true);
    }
}
