package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

//Estruturação dessa classe está muito diferente de ChatLoginGui

public class ChatRegisterGUI {
    public JFrame frame; //public to be accessible
    private JPanel panelTop;
    private JPanel panelBottom;
    
    private JLabel registerName;
    private JTextField registerNameInput;
    private JLabel registerPass;
    private JTextField registerPassInput;
    private JButton registerButton;
    
    private JLabel toLogin;
    private JButton loginButton;
    
    
    public ChatRegisterGUI() {
        frame = new JFrame(); //base frame
        frame.setTitle("Chat Register");
        frame.setSize(400,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        panelTop = new JPanel() {
            @Override //input own methods
            public void paint(Graphics g) {
                super.paint(g);
                g.drawRect(15, 10, 370, 210);
                g.setColor(Color.BLACK);
                Font fontBig = new Font("Arial", Font.BOLD, 25);
                Font fontExtraBig = new Font("Arial", Font.BOLD, 38);
                Font fontSmall = new Font("Arial", Font.BOLD, 14);
                g.setFont(fontBig);
                g.drawString("Creating a new account",30, 50);
                g.setFont(fontSmall);
                g.drawString("When creating a new account be ", 50, 80);
                g.drawString("sure that you remember", 50, 105);
                g.drawString("username and password", 50, 130);
                g.drawString("because in alpha 1.0 we still do", 50, 155);
                g.drawString("not have account recovery ", 50, 180);
                g.drawString("features!", 50, 205);

            }
        };
        panelTop.setPreferredSize(new Dimension(400,250));
        panelTop.setBounds(0,0,400,250);
            
        panelBottom = new JPanel(null); //maeks setbounds possible
        panelBottom.setBounds(0,250,400,500);
        panelBottom.setPreferredSize(new Dimension(400,250));
        
        registerName =  new JLabel("New Username:");
        registerName.setBounds(30,0, 200, 20);
        panelBottom.add(registerName);
        
        registerNameInput =  new JTextField();
        registerNameInput.setBounds(30,40, 200, 20);
        panelBottom.add(registerNameInput);
        registerNameInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("");
            }
        });
        
        panelBottom.add(registerNameInput);
        
        registerPass =  new JLabel("New Password:");
        registerPass.setBounds(30, 80, 200, 20);
        panelBottom.add(registerPass);
        
        registerPassInput =  new JTextField();
        registerPassInput.setBounds(30, 120, 200, 20);
        panelBottom.add(registerPassInput);
        
        registerButton = new JButton("Register");
        registerButton.setBounds(80,160,100,20);
        panelBottom.add(registerButton);
        
        toLogin = new JLabel("When finished:");
        toLogin.setBounds(250,160,120,20);
        panelBottom.add(toLogin);
        
        loginButton = new JButton("Go to login");
        loginButton.setBounds(240,200,120,20);
        panelBottom.add(loginButton);
        
        frame.add(panelTop, BorderLayout.CENTER);
        frame.add(panelBottom, BorderLayout.SOUTH);
        frame.pack();
    }
}
