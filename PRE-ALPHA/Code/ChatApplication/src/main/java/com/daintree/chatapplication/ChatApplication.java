package com.daintree.chatapplication;


import Client.ChatGUI;
import Client.ChatLoginGUI;
import Client.ChatRegisterGUI;

public class ChatApplication {
    
    public static void main(String[] args) {
        ChatGUI chatPage = new ChatGUI();
        chatPage.frame.setVisible(false);
        
        ChatLoginGUI chatLogin = new ChatLoginGUI();
        chatLogin.frame.setVisible(false);
        
        ChatRegisterGUI chatRegister = new ChatRegisterGUI();
        chatRegister.frame.setVisible(true);
        
    }
}
