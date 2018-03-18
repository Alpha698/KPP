/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.net.*;
import java.io.*;

public class TCPServer {
public static void main (String args[]) {
try {
int serverPort = 7896; 
ServerSocket listenSocket = new ServerSocket (serverPort); 
while(true) {
Socket clientSocket = listenSocket.accept(); 
}
}
catch(IOException e) {
System.out.println("Listen socket:"+e.getMessage());
}
}
}

