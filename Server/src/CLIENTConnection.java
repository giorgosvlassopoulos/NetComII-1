import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CLIENTConnection implements Runnable {

    private Socket clientSocket;
    private BufferedReader in = null;
    private DataOutputStream out = null;
    private String clientSentence;
    private String capitalizedSentence;
    

    public CLIENTConnection(Socket client) {
        this.clientSocket = client;
    }

    @Override
    public void run() {
        BufferedReader instream = null;
        try {
            instream = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
            BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String strin = instream.readLine();
            System.out.println("The client says : " + strin);
            if (strin.equals("Hello")){ //following the protocol
                outstream.write("Welcome"+"\n");
                outstream.flush();
                do{
                    strin = instream.readLine();
                    System.out.println("The client says : " + strin);
                    outstream.write(strin+"\n");
                    outstream.flush();
                }while(!strin.equals("bye")); //bye = terminate the conversation
            }
            else { //not following the protocol
                outstream.write("Not welcomed..."+"\n");
                outstream.flush();
            }           instream.close();
            outstream.close();
            clientSocket.close();
            System.out.println("Connection Closing...");
        } catch (IOException ex) {
            Logger.getLogger(CLIENTConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                instream.close();
            } catch (IOException ex) {
                Logger.getLogger(CLIENTConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
} 
    

    
