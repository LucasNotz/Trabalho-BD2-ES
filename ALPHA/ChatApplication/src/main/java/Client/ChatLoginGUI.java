package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ChatLoginGUI {
    //Visual and layout
    public JFrame frame; // janela -> publica para poder ser acessado
    private JPanel panelTop; // panel onde tera o retangulo e welcom
    private JPanel panelBottom; // panel onde tera o login e register
    private JPanel desenho; // onde será desenhado o retangulo e o welcome message
    //login
    private JLabel loginName; 
    private JTextField loginNameInput;
    private JLabel loginPass;
    private JTextField loginPassInput;
    private JButton chat;
    //register page change
    private JLabel newUsers;
    private JButton register;
    
    private Runnable onLoginSuccess;
    private Runnable onRegisterChange;
    

    public void setOnLoginSuccess(Runnable onLoginSuccess) {
        this.onLoginSuccess = onLoginSuccess;
    }    

    public void setOnRegisterChange(Runnable onRegisterChange) {
        this.onRegisterChange = onRegisterChange;
    }
    
    public ChatLoginGUI() {

        
        frame = new JFrame(); // evita vincular classe a um JFrame
        
        //frame config -> deve ser igual em todos
        frame.setTitle("Chat Application Login");
        frame.setSize(400,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panelTop = new JPanel() {
            
            @Override //sobrescrever método existente
            public void paint(Graphics g) {
                super.paint(g);
                g.drawRect(10,15,370,190);
                g.setColor(Color.BLACK);
                Font fontBig = new Font("Arial", Font.BOLD, 30);
                Font fontExtraBig = new Font("Arial", Font.BOLD, 38);
                Font fontSmall = new Font("Arial", Font.BOLD, 20);
                g.setFont(fontBig);
                g.drawString("Welcome", 50, 70);
                g.setFont(fontSmall);
                g.drawString("to", 140, 100);
                g.setFont(fontExtraBig);
                g.drawString("ChatApp", 165, 140);
                
                
            }
        };
        panelTop.setPreferredSize(new Dimension(400,250)); // setPerferredSize -> use when parent layout manager exists aka panel in frame
        //setSize -> when parent layout manager does not exist aka frame
        
        //layout managers ignore setbounds but not preferredsize (get and set), get minimum size and get maximum size
        
        //login setup
        panelBottom = new JPanel(null); 
        panelBottom.setPreferredSize(new Dimension(400,250));    //for this to work pack() has to be here
        panelBottom.setBounds(0,250,400,500);
        
        loginName = new JLabel("Username: ");
        loginName.setBounds(30,20,130,20);
        panelBottom.add(loginName);
        
        loginNameInput = new JTextField(15);
        loginNameInput.setBounds(30,60,130,20);
        panelBottom.add(loginNameInput);
        
        loginPass = new JLabel("Password: ");
        loginPass.setBounds(30,100,130,20);
        panelBottom.add(loginPass); 
        
        loginPassInput = new JTextField(15);
        loginPassInput.setBounds(30,140,130,20);
        panelBottom.add(loginPassInput);
     
   
        chat = new JButton("Chat");
        chat.setBounds(60,190,70,20);
        panelBottom.add(chat);
        chat.addActionListener(e -> { 
            if (onLoginSuccess != null){
                onLoginSuccess.run();
                
            } 
            frame.dispose();
        });

        newUsers = new JLabel("For new users:");
        newUsers.setBounds(260, 150, 120, 20);
        panelBottom.add(newUsers);
        
        register = new JButton("Register");
        register.setBounds(260,180,110,20);
        register.addActionListener(e -> {
            if (onRegisterChange != null) {
                onRegisterChange.run();
            }
            frame.dispose();
        });
        
        panelBottom.add(register);
        
        frame.setLayout(new BorderLayout());
        frame.add(panelTop, BorderLayout.NORTH); 
        frame.add(panelBottom, BorderLayout.SOUTH); 
        frame.pack();
        frame.setVisible(true);


    }
}
