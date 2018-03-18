/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;
import java.net.*;
import java.io.*;

public class UDPServer{
    
public static void main(String args[]) {
DatagramSocket aSocket = null;

try{
    aSocket = new DatagramSocket(6789); 
    byte[] buffer = new byte[1000]; while(true){

    DatagramPacket request = new DatagramPacket(buffer, buffer.length);
    aSocket.receive(request);
    DatagramPacket reply = new DatagramPacket(request.getData(),request.getLength(), request.getAddress(), request.getPort());

aSocket.send(reply);
}
}
catch (SocketException e) {System.out.println("Socket: " + e.getMessage());

}
catch (IOException e) {System.out.println("IO: " + e.getMessage());
}
finally {if(aSocket != null) aSocket.close();}
}
}
 
