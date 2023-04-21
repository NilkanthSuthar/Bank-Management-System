import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import com.sun.net.httpserver.*;

public class Server {
    static int Login(String usern, String upass) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_main", "root", "");
        Scanner sc = new Scanner(System.in);
        Statement st = conn.createStatement();
        // Fetch user_name and pass from DB
        int auth = 0;
        while (auth != 1) {
            ResultSet rs = st.executeQuery("select * from userpass where user_name= '" + usern + "'");
            while (rs.next()) {
                String fpass = rs.getString(3);
                if (upass.equals(fpass)) {
                    auth = 1;
                }
            }
        }
        conn.close();
        return auth;
    }

    public static void main(String args[]) throws Exception {

        ServerSocket server = new ServerSocket(8080);
        System.out.println("Server started on port 8080...");
        while (true) {
            Socket s = null;

            Socket client = server
                    .accept();
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
            DataInputStream in = new DataInputStream(client.getInputStream());
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            // Greeting the Client
            String glog = "Welcome to Bank Management System (V1)!\nIn order to continue, please log-in.";
            String y = "Welcome to Bank Management System (V1)!\nPlease enter the corresponding number for desired action:\n1.Deposit\n2.Withdraw\n3.Transfer\n4.Contact CS";
            out.writeUTF(glog);
            int auth = 0;
            while (auth == 0) {
                out.writeUTF("Enter the username:");
                out.writeUTF("");
                String userna = in.readUTF();
                System.out.println("Received message from client: " + userna);
                out.writeUTF("Enter the password:");
                out.writeUTF("");
                String upa = in.readUTF();
                System.out.println("Received message from client: " + upa);
                auth = Login(userna, upa);
                if (auth == 0) {
                    System.out.println("Please re-enter your username and password.");
                }
            }
            System.out.println("You've succesfully logged in!");

            /*
             * String response;
             * if (message.equals("1")) {
             * response = "We will redirect you to our Deposit page...";
             * } else if (message.equals("2")) {
             * response = "We will redirect you to our Withdrawl page...";
             * } else if (message.equals("3")) {
             * response = "We will redirect you to our Transfer page...";
             * } else if (message.equals("4")) {
             * response = "We will redirect you to our CS page...";
             * } else {
             * response = "I don't understand what you mean.";
             * }
             * 
             * out.writeUTF(response);
             * System.out.println("Sent response to client: " + response);
             */
            in.close();
            out.close();
            client.close();
        }
    }
}