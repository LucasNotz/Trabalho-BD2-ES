/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.daintree.chat_application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author alliedmastercomputer
 */
public class Chat_Application_Login extends JFrame{
    //class variables
    
    //campos de inserir dados : txt
    private JTextField txtUsername;
    private JTextField txtPassword;
    
    //labels : lbl
    private JLabel lblUsername;
    private JLabel lblPassword;
    
    private JLabel lblLogin;
    private JLabel lblRegister;
    
    //buttons : btn
    private JButton btnLogin;
    private JButton btnRegister;
    
    private JButton btnExit;
    
    public static void main(String[] args) {
        new Chat_Application_Login().setVisible(true);
    }
    
    public Chat_Application_Login() {
        //config janela
        setTitle("Login");
        setSize(400,500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        //config user
        lblUsername = new JLabel("Enter username");
        txtUsername = new JTextField();
        
        lblUsername.setBounds(30,20,200,20);
        txtUsername.setBounds(30,40,300,20);
        
        add(lblUsername);
        add(txtUsername);
        
        //config pass
        
        lblPassword = new JLabel("Enter password");
        txtPassword = new JTextField();
        
        lblPassword.setBounds(30,60,200,20);
        txtPassword.setBounds(30,80,300,20);
        
        add(lblPassword);
        add(txtPassword);
        
        //config buttons/btn labels
        
        //config btn login
        
        lblLogin = new JLabel("For existing users");
        btnLogin = new JButton("Login");
        
        lblLogin.setBounds(30,120,200,20);
        btnLogin.setBounds(30,150,100,30);
        
        add(lblLogin);
        add(btnLogin);
        
        //config btn register
        
        lblRegister = new JLabel("For new users");
        btnRegister = new JButton("Register");
        
        lblRegister.setBounds(30,190,200,20);
        btnRegister.setBounds(30,220,100,30);
        
        add(lblRegister);
        add(btnRegister);
        
           
        
        
        
    }
}
