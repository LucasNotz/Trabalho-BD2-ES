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
    //variáveis da classe
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
        setResizable(false);

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
          
          // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            // java automatically fires actionlistener when enter is pressed
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + name + ": "
                        + textField.getText();
                //invoca método de ChatClient .sendMessage(String msg) que faz:out.println(msg)
                //que envia a mensagem pelo socket, já que out é um printwriter connectado a um socket
                //por meio de socketl.getOutoputStream()
                client.sendMessage(message);
                //limpa o textfield
                textField.setText("");
            }
        });
        //bloco de código do botão de sair
        exitButton = new JButton("Exit");
        exitButton.setFont(buttonFont);
        exitButton.setBackground(buttonColor);
        exitButton.setForeground(Color.WHITE);
        //funcianlidade do botão
        exitButton.addActionListener(e -> {
            String departureMessage = name + " has left the chat.";
            //envia para o servidor a mensagem de que saiu
            client.sendMessage(departureMessage);
            try {
                //atrasa o tempo de fechamento do thread a fim de permitir que a mensagem saia antes
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                //se o for fechado o "window" antes de enviar a mensagem o thread fecha automaticamente
                Thread.currentThread().interrupt();
            }
            //fecha o programa
            System.exit(0);
        });

        //bloco de código do panel, que contém o textfield e o exitbutton
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(backgroundColor);
        bottomPanel.add(textField, BorderLayout.CENTER);
        bottomPanel.add(exitButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        //inicialização do cliente
            //ip address, port & omMessageReceived
        try {
            //cria uma objeto do chatclient que é responsável por receber e enviar mensagens para e do servidor
                //this::onMessageReceived -> utilize esse método que recebe uma string e faz alguma coisa
            this.client = new ChatClient("127.0.0.1", 5000, this::onMessageReceived);
            //inicializa o cliente
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to the server", "Connection error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    //método da interface consumer
        //Mostra a mensagen no chat GUI
    private void onMessageReceived(String message) {
        //SwingUtilites -> utility class to deal with event handling aka updates on screen
        //uses Event Dispatch Thread -> one thread to handle all swing events
        //not thread safe, so invoke later asks it to do it as soon as possible as to not
        //crash the screen
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }

    public static void main(String[] args) {
        //thread safe way to create user on server
        SwingUtilities.invokeLater(() -> {
            //create user 
            new Chat_Application().setVisible(true);
        });
    }
}
