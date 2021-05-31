package MyChatRoom;

import java.io.*;
import java.net.Socket;

//TODO:两个线程并行有点冲突，怎么让其中一个sleep()一下；

public class Client {
    Socket socket;
    InputStreamReader streamReader;
    PrintWriter writer;
    BufferedReader reader;
    Send sender;

    String UserIP;
    int UserPort;
    String Username;

    public Client(String IP, int port) {
        // UserIP = IP;
        // UserPort = port;
        try {
            socket = new Socket(IP, port);
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
    public void go() {
        Thread getMessageThread = new Thread(new GettingMessage(socket));
        Thread sendMessageThread = new Thread(new SendingMessage(socket));
        getMessageThread.start();
        sendMessageThread.start();
    }

    public static void main(String[] args) {
        String IP = "127.0.0.1";
        int port = 5000;
        Client client = new Client(IP, port);
        client.go();
    }

    //一直接受的发送线程，比如一边聊天一边传文件
    public class SendingMessage implements Runnable{

        Socket s;
        Send sender;

        public SendingMessage(Socket socket){
            s=socket;
            sender = new Send(s);
        }

        public void run(){
            while (true) {
                sender.sendMessage();
            }
        }
    }

    //从服务器收数据的线程，需要一直运行
    public class GettingMessage implements Runnable {

        Socket s;
        InputStreamReader streamReader;
        BufferedReader reader;

        public GettingMessage(Socket socket) {
            s = socket;
            try {
                streamReader = new InputStreamReader(s.getInputStream());
                reader = new BufferedReader(streamReader);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            while (true) {
                try {
                    if (true) {
                        String message = reader.readLine();
                        System.out.println(message);
                    } else {
                        System.out.println("not ready???");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public String getUsername() {
            return Username;
        }

        public void setUsername(String username) {
            Username = username;
        }
    }

}
