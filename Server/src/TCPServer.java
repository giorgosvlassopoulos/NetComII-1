import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private static ServerSocket serverSocket;
    private static Socket clientSocket = null;

    public static void main(String[] args) throws IOException {
        //dokimazoume na syndethoume
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.err.println("Port already in use.");
            System.exit(1);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection : " + clientSocket);
                
                Thread t = new Thread(new CLIENTConnection(clientSocket));

                t.start();
                //trexoume to thread tou server

            } catch (Exception e) {
                System.err.println("Error in connection attempt.");
            }
        }
    }
}
