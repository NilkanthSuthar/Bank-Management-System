import java.io.*;
import java.net.*;
import javax.se;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080); // Create a client socket to connect to the server
        System.out.println("Connected to server on port 8080");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String message;
        int a = 0;
        int b = 0;
        String c = "a";
        while (a == 0) {
            while (!c.equals("")) {
                c = in.readUTF();
                System.out.println(c);
            }
            message = console.readLine(); // Read a message from the console input
            output.writeUTF(message); // Send the message to the server
            String garb=in.readUTF();
        }
        socket.close(); // Close the connection with the server
    }
}