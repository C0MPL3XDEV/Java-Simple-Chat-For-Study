import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat_Server {
    private static final int port = 4096;
    public void run(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started at PORT: " + port);

            Socket client1 = serverSocket.accept();
            System.out.println("Client 1 Connected");
            Socket client2 = serverSocket.accept();
            System.out.println("Client 2 Connected");

            receive_send_message(client1, client2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receive_send_message(Socket client1, Socket client2) throws IOException {
        InputStreamReader isr1 = new InputStreamReader(client1.getInputStream());
        InputStreamReader isr2 = new InputStreamReader(client2.getInputStream());
        BufferedReader reader1 = new BufferedReader(isr1);
        BufferedReader reader2 = new BufferedReader(isr2);
        PrintWriter pw1 = new PrintWriter(client1.getOutputStream(), true);
        PrintWriter pw2 = new PrintWriter(client2.getOutputStream(), true);

        do {
            pw1.println(".");
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
