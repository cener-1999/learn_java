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
    }

    //传递消息的方法
    public void sendMessage(){
        String message;
        //get user input:
        System.out.print("请输入");
        Scanner scanner = new Scanner(System.in);
        message = scanner.nextLine();
        //System.out.println(message);

        try{
            writer = new PrintWriter(s.getOutputStream());
            writer.println(message);
            writer.flush();

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}