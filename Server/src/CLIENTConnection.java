
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CLIENTConnection implements Runnable {

    private Socket clientSocket;
    private ServerSocket serverSocket;

    public CLIENTConnection(Socket client, ServerSocket server) {
        this.clientSocket = client;
        this.serverSocket = server;
        //we have Socket and ServerSocket in order to be able to get informations for both
    }

    @Override
    public void run() {
        BufferedReader instream = null;
        BufferedWriter outstream = null;
        //initialize the BufferedReader and Writer
        try {
            instream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outstream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String strout = "220" + " " + serverSocket.getInetAddress().getHostName() + " " + "Service Ready" + "\r\n";
            outstream.write(strout);
            outstream.flush();
            //1st sent message of the server
            String strin = instream.readLine();
            System.out.println(strin);
            //1st received message of the server 
            strout = "250" + " " + "Requested mail action okay, completed" + "\r\n";
            outstream.write(strout);
            outstream.flush();
            //2d sent message of the server
            strin = instream.readLine();
            System.out.println(strin);
            //2d received message of the server
            strout = "250" + " " + "OK" + "\r\n";
            outstream.write(strout);
            outstream.flush();
            //3d sent message of the server 
            strin = instream.readLine();
            System.out.println(strin);
            //3d received message of the server
            strout = "250" + " " + "OK" + "\r\n";
            outstream.write(strout);
            outstream.flush();
            //4th sent message of the server
            strin = instream.readLine();
            System.out.println(strin);
            //4th received message of the server
            strout = "354" + " " + "Start mail input; end with <CRLF>.<CRLF>";
            outstream.write(strout);
            outstream.flush();
                    //5th sent message of the server

            /**
             * ****************************
             ************PROBLEM************
                    ******************************
             */
                                  //do{
                 /*   strin = instream.readLine();
             System.out.println( strin);
             strin = instream.readLine();
             System.out.println( strin);
             strin = instream.readLine();
             System.out.println( strin);
             strout="250"+" " +"OK" + "\r\n";
             outstream.write(strout);
             outstream.flush();*/
            // }while(!strin.equals("QUIT \r\n")); //bye = terminate the conversation
            instream.close();
            outstream.close();
            clientSocket.close();
            //close the BufferedReader,Writer and the Socket connection
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
