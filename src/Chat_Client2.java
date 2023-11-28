import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Chat_Client2 {

    private static final int port = 4096;
    private static final String IP = "127.0.0.1";
    Scanner in = new Scanner(System.in);

    public void run(String IP, int port) {
        try {
            Socket cSocket = new Socket(IP, port);

            do {
                PrintWriter pw = new PrintWriter(cSocket.getOutputStream(), true);
                InputStreamReader isr = new InputStreamReader(cSocket.getInputStream());
                BufferedReader br = new BufferedReader(isr);

                sendString(pw, cSocket);
                System.out.println("Message sent to the Client 1");
                String receivedMessage = br.readLine();
                System.out.println("Message Received from C1: " + receivedMessage);

            } while (cSocket.isConnected());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendString(PrintWriter pw, Socket cSocket) throws IOException {
        System.out.println("Message for the Client 2: ");
        String message = in.nextLine();
        pw.println(message);

        if ("/exit".equalsIgnoreCase(message)) {
            pw.println("GoodBye");
            cSocket.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Chat_Client2 chatClient2 = new Chat_Client2();
        chatClient2.run(IP, port);
    }
}
