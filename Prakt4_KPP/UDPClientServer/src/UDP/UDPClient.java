/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.net.*;
import java.io.*;
public class UDPClient{

public static void main(String args[]){

   try {
        DatagramSocket aSocket = new DatagramSocket(); 

        byte[] message = args[0].getBytes();
        InetAddress aHost = InetAddress.getByName(args[1]); 
        int serverPort = 6789;

        DatagramPacket request =new DatagramPacket(message, args[0].length(), aHost, serverPort);
        aSocket.send(request); 
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer,buffer.length);

        aSocket.receive(reply);

        System.out.println("Ответ: " + new String(reply.getData())); aSocket.close();
        }

catch (SocketException e){ System.out.println("Socket: " + e.getMessage());

}
catch (IOException e){ System.out.println("IO: " + e.getMessage());

}
}
}


