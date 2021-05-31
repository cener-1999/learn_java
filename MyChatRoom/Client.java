package MyChatRoom;

import java.io.IOException;
import java.net.Socket;

public class Client {
    

    Socket socket;

    String UserIP;
    int UserPort;
    String Username;

    public Client(String IP,int port){
        // UserIP = IP;
        // UserPort = port;
        try {
            socket = new Socket(IP,port);
            System.out.println("连接服务器···");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public void go(){
        while (true){

        }
    }

    public static void main(String[] args) {
        String IP="127.0.0.1";
        int port = 5000;
        Client client = new Client(IP, port);
        client.go();
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
    
}
