package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.Consumer;

public class ChatClient {
    private Socket socket = null;
    private BufferedReader inputConsole = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Consumer<String> onMessageReceived;
    
    //dont know what this does tbh
    public ChatClient(String serverAddress, int serverPort, Consumer<String> onMessageReceived) throws IOException {
        this.socket = new Socket(serverAddress, serverPort);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.onMessageReceived = onMessageReceived;
    }
    
    public void sendMessage(String msg) {
        out.println(msg);
    }
    
    public void startClient() {
        new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    onMessageReceived.accept(line); // uses consumer interface aka lambda
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    //constructor
    public ChatClient(String address, int port) {
        try {
            socket = new Socket(address, port); // if .accept() connects
            System.out.println("Connected to chat server");
            
            inputConsole = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String line = "";
            while (!line.equals("exit")) {
                line = inputConsole.readLine();
                out.println(line);
                System.out.println(in.readLine());                
             
            } 
            
            socket.close();
            inputConsole.close();
            out.close();
            in.close();
        } catch (UnknownHostException u) {
            System.out.println("Host unknown: " + u.getMessage());
        } catch (IOException i) {
            System.out.println("Unexpected exception: " + i.getMessage());
        }
    }
}
