package MyChatRoom;

import java.io.*;
import java.net.Socket;
import MyChatRoom.*;

public class Client {
    Socket socket;
    InputStreamReader streamReader;
    PrintWriter writer;
    BufferedReader reader;
    Send sender;

    String UserIP;
    int UserPort;
    String Username;

    public Client(String IP,int port){
        // UserIP = IP;
        // UserPort = port;
        try {
            socket = new Socket(IP,port);
            streamReader = new InputStreamReader(socket.getInputStream());
            sender = new Send(socket);
            System.out.println("连接服务器···");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    //收数据和发送数据是什么搞。
    //收数据是一直？发送点击Button才发送吗？
    //没有GUI时呢，定义键盘事件？
    public void go(){
        send();
        while (true){
            try {
                //从服务器收数据
                reader = new BufferedReader(streamReader);
                if(true){
                    String message = reader.readLine();
                    System.out.println(message);
                }
                else {
                    System.out.println("not ready???");
                }
                String message = reader.readLine();
                System.out.println(message);
            }catch (IOException ex){
                ex.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        String IP="127.0.0.1";
        int port = 5000;
        Client client = new Client(IP, port);
        client.go();
    }

    //TODO:定义发送事件
    public void send(){
        sender.sendMessage();
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

}
