package MyChatRoom;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

//TODO:1.完成服务器和客户端的连接； -完成
//TODO:2.完成信息的收发；
//TODO:3.完成群聊的线程管理,退出时杀死线程；杀线程时同时从list中删除；考虑数据结构是否得当；
//TODO:4.群聊 telltoeveryone ,私聊需要输入两个人？例如 tell(sender A,reciver B) 如果这样实现是不是需要socket和userID的键值对;

public class Server{
    ServerSocket serverSocket;
    Socket socket;
    PrintWriter writer;
    InputStreamReader streamReader;
    BufferedReader reader;

    ArrayList<Socket>usersList;
    ArrayList<Thread>threadsList;
    int flag = 0;

    String welcome = "欢迎您进入聊天室，";

    //TODO 需要修改，sokect连接时获取用户信息
    String[] nameList = {"张三","李四","王五","钱老六"};


    public Server() {
        usersList = new ArrayList<>();
        threadsList = new ArrayList<>();
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
                usersList.add(socket);
                System.out.println("收到连接");

                Thread t = new Thread(new ChatRoom(socket));
                threadsList.add(t);
                flag++;
                //TODO:怎么获取并设置用户名？
                t.setName(nameList[flag]);
                System.out.println("开启线程："+t.getName());
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


    //群发消息，暂时是socket列表里的所有
    public void tellEveryone(String message){
        System.out.println(usersList);
        for(Socket socket:usersList){
            try{
                writer = new PrintWriter(socket.getOutputStream());
            }catch (IOException ex){
                ex.printStackTrace();
            }

            writer.println(message);
            writer.flush();
            //writer.close();
        }
    }

    //线程任务：客户端与服务器传递消息
    //
    public class ChatRoom implements Runnable{
        Socket s;

        public ChatRoom(Socket socket){
            //这玩意好像不好使
            s = socket;
            try{
                writer = new PrintWriter(s.getOutputStream());
                streamReader = new InputStreamReader(s.getInputStream());
                reader = new BufferedReader(streamReader);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
        public void run(){
            //信息收发，示例：欢迎语
           String welcomeMessage=(welcome+Thread.currentThread().getName()+"!");
           //System.out.println("send"+welcomeMessage);
           writer.println(welcomeMessage);
           writer.flush();


           //一直接受消息···
           while (true){
               try{
                   if (reader.ready()){
                       String message = reader.readLine();
                       System.out.println(message);
                       tellEveryone(message);
                   }

               }catch (IOException ex){
                   System.out.println("error here！");
                   //ex.printStackTrace();
               }


           }
        }
    }
}