package pack1;

import java.net.*;
import java.io.*;

public class ServerSimpleV1 {
    public static void main(String args[]) throws IOException {
        int i = 0;
        ServerSocket s = null;
        Socket s1 = null;
        OutputStream s1out = null;
        DataOutputStream dos = null;
        InputStream s1in = null;
        DataInputStream din = null;
        double f,p;
        // Register your service on port 5432
        try {
            s = new ServerSocket(5432);
            s1 = s.accept();
            // Get output stream associated with the socket
            s1out = s1.getOutputStream();
            dos = new DataOutputStream(s1out);
            s1in = s1.getInputStream();
            din = new DataInputStream(s1in);
            s.close();
        } catch (IOException e) {
            // ignore
        }

        System.out.println("Server is UP!");
        // Run the listen/accept loop forever
        while (true) {
            try {
                // Wait here and listen for a connection
            	dos.writeUTF(" > Introduceti Forta Axiala in N " );
                dos.flush();
                String sin = din.readUTF();
                f=Double.parseDouble(sin);
                if (sin.equals("bye")) {
                    s = new ServerSocket(5432);
                    s1 = s.accept();
                    s1out = s1.getOutputStream();
                    dos = new DataOutputStream(s1out);
                    s1in = s1.getInputStream();
                    din = new DataInputStream(s1in);
                    s.close();
                    i=1;
                    System.out.println(i + " > Connection reopened ");
                    continue;
                }
                // Send your string!
               
                dos.writeUTF(" > Introduceti presiunea de contact admisibila " );
                dos.flush();
                 sin = din.readUTF();
                p=Double.parseDouble(sin);
                if (sin.equals("bye")) {
                    s = new ServerSocket(5432);
                    s1 = s.accept();
                    s1out = s1.getOutputStream();
                    dos = new DataOutputStream(s1out);
                    s1in = s1.getInputStream();
                    din = new DataInputStream(s1in);
                    s.close();
                    i=1;
                    System.out.println(i + " > Connection reopened ");
                    continue;
                }
                
                dos.writeUTF(++i + " > Rezultat Server > " +Math.sqrt((f/(Math.PI*0.5*2*p))) + " at: " + s1.getInetAddress());
                dos.flush();
                System.out.println(i + " > Hello from Server over the Net! > " + sin);
            } catch (IOException e) {
                // Close the connection, but not the server socket
                dos.close();
                din.close();
                s1.close();
                // ignore
            }
        }    
    }
}
