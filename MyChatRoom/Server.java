package MyChatRoom;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

//TODO:1.完成服务器和客户端的连接； -完成
//TODO:2.完成信息的收发；
//TODO:3.完成群聊的线程管理

public class Server{
    ServerSocket serverSocket;
    Socket socket;
    ArrayList<Socket>userList;
    String[] nameList = {"张三","李四","王五","钱老六"};
    int flag = 0;

    public Server() {
        userList = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("服务器开启···");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void go(){

        while(true){
            try {
                socket = serverSocket.accept();
                //这是出错点,之前没有给userList初始化，它也是个类的实例，需要new;
                userList.add(socket);
                System.out.println("收到连接");

                Thread t = new Thread(new ChatRoom());
                flag++;
                //TODO:怎么获取并设置用户名？
                t.setName(nameList[flag]);
                t.start();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
           
        }
       
    }

    //运行
    public static void main(String[] args) {
        Server server = new Server();
        server.go();
    }

    //开启线程
    public class ChatRoom implements Runnable{
        public void run(){
            //信息收发
            System.out.println(Thread.currentThread().getName());
        }
    }
}