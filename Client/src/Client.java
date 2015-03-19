
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static Socket sock;

    public static void main(String[] args) throws IOException {
        try {
            sock = new Socket("localhost", 25);

            BufferedReader instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedWriter outstream = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            //initialize the BufferedReader and Writer

            String strin, strout;

            //Scanner in = new Scanner(System.in);
            strin = instream.readLine();
            System.out.println(strin);
            //1st received message of the client
            strout = "HELO" + " " + sock.getInetAddress().getHostName() + "\r\n";
            outstream.write(strout);
            outstream.flush();
            //1st sent message of the client
            strin = instream.readLine();
            System.out.println(strin);
            //2d received message of the client
            //Starts the protocol for email
            String username = "sender";
            String username2 = "receiver";
            String subject = "test";
            String message = "message";
            //   do{
            strout = "MAIL" + " " + "FROM:" + " " + username + "@" + sock.getInetAddress() + "\r\n";
            outstream.write(strout);
            outstream.flush();
            //2d sent message of the client
            strin = instream.readLine();
            System.out.println(strin);
            //3d received message of the client
            strout = "RCPT" + " " + "TO:" + " " + username2 + "@" + sock.getInetAddress() + "\r\n";
            outstream.write(strout);
            outstream.flush();
            //3d sent message of the client
            strin = instream.readLine();
            System.out.println(strin);
            //4th received message of the client
            strout = "DATA \r\n";
            outstream.write(strout);
            outstream.flush();
            //4th sent message of the client
            strin = instream.readLine();
            System.out.println(strin);
                    //5th received message of the client

            /* strout="Subject:"+subject+"\r\n"+"From:"+username+"\r\n"+"To:"+username2+"\r\n";
             outstream.write(strout);
             outstream.flush();
             strout="\n";
             outstream.write(strout);
             outstream.flush();
             strout=message+"\r\n"+"."+"\r\n";
             outstream.write(strout);
             outstream.flush();
             strin = instream.readLine();
             System.out.println(strin);
             */
            //} while (!strin.equals("QUIT \r\n"));
            instream.close();
            outstream.close();
            sock.close();
            System.out.println("Connection Closing...");

        } catch (Exception ex) {
            System.out.println("Connection Refused!!!");
        }
    }
}
