package com.daintree.serverhostapp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ServerHostApp {
    
    private static List<ClientHandler> clients = new ArrayList<>();
    private ServerSocket serverSocket;
    
    public static void main(String[] args) throws IOException {
        ServerHostApp server = new ServerHostApp();
        
    }
    
    public ServerHostApp() throws IOException {

        JFrame frame = new JFrame();
        
        frame.setLayout(new BorderLayout());
        
        frame.setTitle("Host Server");
        frame.setSize(400,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        
        JLabel socketLabel = new JLabel("Socket number: ");
        socketLabel.setBounds(50,50,200,20);
        panel.add(socketLabel);
        
        JTextField socketInput = new JTextField();
        socketInput.setBounds(50,90,200,20);
        panel.add(socketInput);
        
        JButton socketConfirm = new JButton("Start server on specified socket");
        socketConfirm.setBounds(50,120,300,20);
        panel.add(socketConfirm);
        
        
        
        
        socketConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                int port = Integer.parseInt(socketInput.getText());
                socketConfirm.setEnabled(false);                   
                
                new Thread(() -> {
                    try {
                        serverSocket = new ServerSocket(port);
                        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(frame, "Server Started on port " + port));
                        
                        while (true) {
                            Socket clientSocket = serverSocket.accept();
                            JOptionPane.showConfirmDialog(frame, "Client connected");
                            
                            ClientHandler clientThread = new ClientHandler(clientSocket, clients);
                            clients.add(clientThread);
                            new Thread(clientThread).start();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });
        
        JButton exit = new JButton("Exit and close server");
        exit.setBounds(50,150,200,20);
        panel.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    serverSocket.close();
                } catch (IOException io){
                    io.printStackTrace();
                }
                System.exit(0);
            }
        
        });
        frame.setVisible(true);
        
        System.out.println("hey");
    }
}

