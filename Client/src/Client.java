
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;
    private static PrintStream os;
    public static String sentence;
    public static String modifiedSentence;
    public static BufferedReader inFromUser = null;
    public static DataOutputStream outToServer = null;
    public static BufferedReader inFromServer = null;

    public static void main(String[] args) throws IOException {
        try{
            Socket sock = new Socket("localhost", 4444);

            BufferedReader instream = new BufferedReader (new InputStreamReader(sock.getInputStream()));
            BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

            System.out.println("Sending Messages to the Server...");
            System.out.println("Connecting to "+ sock.getInetAddress()+ " and port "+sock.getPort());
            System.out.println("Local Address :"+sock.getLocalAddress()+" Port"+sock.getLocalPort());
            String strin, strout;

            Scanner in = new Scanner(System.in);
            
            do{
                System.out.print("Write what the client will send: ");
                strout = in.nextLine();
                outstream.write(strout+"\n");
                outstream.flush();
 
                strin = instream.readLine();
                System.out.println("The server says: "+strin);
            }
 
            while (!strin.equals("bye"));
            instream.close();
            outstream.close();
            sock.close();
            System.out.println("Connection Closing...");
        
        } catch (Exception ex){
            System.out.println("Connection Refused!!!");
        }
 }
}