package MyChatRoom;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Send{
    Socket s;
    PrintWriter writer;

    //绑定socket
    public Send(Socket socket){
        s = socket;
        try{
            writer = new PrintWriter(s.getOutputStream());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //传递消息的方法
    public void sendMessage(){
        String message;
        //get user input:
        System.out.println("请输入");
        Scanner scanner = new Scanner(System.in);
        message = scanner.nextLine();

        writer.println(message);
        writer.flush();
    }
}