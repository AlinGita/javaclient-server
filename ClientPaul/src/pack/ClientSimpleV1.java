package pack;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientSimpleV1 {
    public static void main(String args[]) {
        Scanner keyboard_in = new Scanner(System.in);
        Socket s1;
        InputStream is;
        DataInputStream dis;
        OutputStream os;
        DataOutputStream dos;

        try {
            // Open your connection to a server, at port 5432
            // localhost used here
            s1 = new Socket("127.0.0.1", 5432);
            // Get an input stream from the socket
            
            is = s1.getInputStream();
            os = s1.getOutputStream();

            // Decorate it with a "data" input stream
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);

            while (true) {
                System.out.print("Diametru mediu d2 din solicitarea de contact a spirelor\n ");
                //read a line from the keyboard
                System.out.println("The serve said: " + dis.readUTF());
                
                String codeline = keyboard_in.nextLine();
    
                if (codeline.equals("bye")) {
                    System.out.println("Client terminated by bye<Enter> character");
                    dos.writeUTF(codeline);
                    // When done, just close the stream and connection
                    dis.close();
                    dos.close();
                    s1.close();
                    break;
                } else {
                    dos.writeUTF(codeline);
                    System.out.println("The serve said: " + dis.readUTF());
                    codeline = keyboard_in.nextLine();
                    dos.writeUTF(codeline);
                    System.out.println("The serve said: " + dis.readUTF());
                    
                }
            }
        } catch (ConnectException connExc) {
            System.err.println("Could not connect to the server.");

        } catch (IOException e) {
            // ignore
        }
    }
}




