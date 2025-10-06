package Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ChatGUI {
    private ChatClient client;
    
    public JFrame frame; // janela -> publica para poder ser acessada
    private JPanel panel; // tudo é escrito nesse panel ao invés do frame
    private JTextArea messageBox; // onde as mensagens aparecem
    private JPanel messageFieldPanel; // onde o message field e botao de exit ficam
    private JButton exitButton; // botao de sair
    private JTextField messageField; // onde escreve mensagens
    
    
    public ChatGUI() {
        frame = new JFrame(); // evita vincular classe a um JFrame
        //frame config -> deve ser igual em todos
        frame.setTitle("Chat Application");
        frame.setSize(400,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panel = new JPanel(new BorderLayout());
        
        frame.add(panel); //ŧudo será feito nesse container
        
        messageBox = new JTextArea(); // cria area para visualizar mensagem
        messageBox.setEditable(false); // impede o usuário de usar 
        panel.add(new JScrollPane(messageBox), BorderLayout.CENTER); // adiciona scroll no centro e adiciona message box no scroll
        
        messageFieldPanel = new JPanel(new BorderLayout()); // cria painel para mensagens
        panel.add(messageFieldPanel, BorderLayout.SOUTH); // adiciona painel ao frame       
               
        messageField = new JTextField(); // cria area para mandar mensagens
        messageFieldPanel.add(messageField, BorderLayout.CENTER); //adiciona message field ao painel
        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + ": " + messageField.getText();
                client.sendMessage(message);
                messageField.setText("");
            }
        });
        
        exitButton = new JButton("Exit");
        messageFieldPanel.add(exitButton, BorderLayout.EAST);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                //send exit message
                System.exit(0);
            }
        });
        
    }
    
    private void onMessageReceived(String msg) {
        SwingUtilities.invokeLater(() -> messageBox.append(messageBox + "\n"));
    }
    
}
