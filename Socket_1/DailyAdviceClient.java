

import java.io.*;
import java.net.*;

public class DailyAdviceClient{

    public void go(){
        try {
            Socket s = new Socket("127.0.0.1",5050);

            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String advice = reader.readLine();
            System.out.println("Today you Should:"+advice);

            reader.close();
        
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DailyAdviceClient client = new DailyAdviceClient();
        client.go();
    }
}

