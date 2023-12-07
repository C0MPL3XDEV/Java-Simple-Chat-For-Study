import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat_Server {
    private static final int port = 4096;
    public void run(int port) { // Method to run the server
        ServerSocket serverSocket;
        try {
            //noinspection resource
            serverSocket = new ServerSocket(port); // Start the server with the port chosen
            System.out.println("Server Started at PORT: " + port);

            Socket client1 = serverSocket.accept(); // Accept the request of the first client
            System.out.println("Client 1 Connected");
            Socket client2 = serverSocket.accept();
            System.out.println("Client 2 Connected"); // Accept the request of the second client

            receive_send_message(client1, client2); // Call the method for store the messages from clients and writes on the clients
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    private void receive_send_message(Socket client1, Socket client2) throws IOException {
        InputStreamReader isr1 = new InputStreamReader(client1.getInputStream()); // Get the 
        InputStreamReader isr2 = new InputStreamReader(client2.getInputStream()); //
        BufferedReader reader1 = new BufferedReader(isr1); // Blocks of chars from first isr
        BufferedReader reader2 = new BufferedReader(isr2);
        PrintWriter pw1 = new PrintWriter(client1.getOutputStream(), true);
        PrintWriter pw2 = new PrintWriter(client2.getOutputStream(), true);

        pw1.println(".");

        do {
            String message1 = reader1.readLine();
            System.out.println(message1);
            pw2.println(message1);
            String message2 = reader2.readLine();
            System.out.println(message2);
            pw1.println(message2);
        } while (client1.isConnected() && client2.isConnected());

    }

    public static void main(String[] args) {
        Chat_Server chatServer = new Chat_Server();
        chatServer.run(port);
    }
}
