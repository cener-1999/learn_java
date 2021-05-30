
import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer {
    ArrayList clientOutputStreams;

    public class ClientHandler implements Runnable{
        BufferedReader reader;
        Socket socket;
        
        public ClientHandler(Socket clientSocket){
            try{
                socket = clientSocket;
                InputStreamReader streamReader= new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(streamReader);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        public void run(){
            String message;
            try{
                while((message=reader.readLine())!=null){
                    System.out.println(message);
                    tellEveryone(message);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }

    public void go(){
        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSocket= new ServerSocket(5000);
            
            while(true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("got a connettion");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void tellEveryone(String message){
        Iterator it = clientOutputStreams.iterator();
        while(it.hasNext()){
            try {
                PrintWriter writer =(PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
