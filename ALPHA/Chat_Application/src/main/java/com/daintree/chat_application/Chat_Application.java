/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.daintree.chat_application;

/**
 *
 * @author nasa
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//essa classe contém o código para inicializar o GUI do cliente
public class Chat_Application extends JFrame {
    //informações da class
  
    private JTextArea messageArea;
    private JTextField textField;
    private JButton exitButton;
  
        //client(serverAddress, serverPort, onMessageReceived)
        //esse é o cliente que invoca o chatclient para conecatar 
        //com o servidor
    private ChatClient client;

    public Chat_Application() {
        //definições do frame
        super("Chat Application");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //bloco de código que define "presets" para futuras decisões estilísticas
        Color backgroundColor = new Color(240, 240, 240);
        Color buttonColor = new Color(75, 75, 75);
        Color textColor = new Color(50, 50, 50);
        Font textFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);

        //bloco de código responsável pela área onde as mensagens aparecem
        messageArea = new JTextArea();
        messageArea.setEditable(false); //impede o usuário de editar a area
        messageArea.setBackground(backgroundColor);
        messageArea.setForeground(textColor);
        messageArea.setFont(textFont);
        JScrollPane scrollPane = new JScrollPane(messageArea); //adiciona a area de mensagem
        //ao scroll e adiciona o scroll no frame
        add(scrollPane, BorderLayout.CENTER);

        //Pede o nome do usuário, atribui esse nome ao título e atribui esse nome a uma variável
        String name = JOptionPane.showInputDialog(this, "Enter your name:", "Name Entry", JOptionPane.PLAIN_MESSAGE);
        this.setTitle("Chat Application - " + name);

        //bloco de código responsável pela área de enviar mensagens
        textField = new JTextField();
        textField.setFont(textFont);
        textField.setForeground(textColor);
        textField.setBackground(backgroundColor);

          //bloco de código responsável por resgatar a mensagem escrita no textField, formatar e atribuir
          // a uma variável e mandar isso 
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + name + ": "
                        + textField.getText();
                //invoca método de ChatClient .sendMessage(String msg) que faz:out.println(msg)
                //que envia a mensagem pelo socket, já que out é um printwriter connectado a um socket
                //por meio de socketl.getOutoputStream()
                

                client.sendMessage(message);
                textField.setText("");
            }
        });
        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> {
            String departureMessage = name + " has left the chat.";
            client.sendMessage(departureMessage);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            System.exit(0);
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(exitButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        try {
            this.client = new ChatClient("127.0.0.1", 5000, this::onMessageReceived);
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the server", "Connection error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void onMessageReceived(String message) {
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Chat_Application().setVisible(true);
        });
    }
}
