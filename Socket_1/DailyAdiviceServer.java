import java.io.*;
import java.net.*;
import java.security.cert.TrustAnchor;

public class DailyAdiviceServer {
    String[] adviceList = {"For man is man and master of his fate.","Our destiny offers not the cup of despair, but the chalice of opportunity" 
    +"So let us seize it, not in fear, but in gladness.","While there is life there is hope.",
    "You have to believe in yourself. That's the secret of success.","We must accept finite disappointment, but we must never lose infinite hope"
    ,"A man can fail many times, but he isn't a failure until he begins to blame somebody else"};

    public void go(){
        try{
            ServerSocket serverSocket = new ServerSocket(5050);
            System.out.println("启动服务器....");

            while(true){
                Socket socket = serverSocket.accept();

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();

                printWriter.println(advice);
                printWriter.close();
                System.out.println(advice);

            }
        }catch(IOException ex){
                ex.printStackTrace();
        }
        
    }

    public String getAdvice(){
        int random =(int) (Math.random()*adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdiviceServer dailyAdiviceServer = new DailyAdiviceServer();
        dailyAdiviceServer.go();
    }
}
