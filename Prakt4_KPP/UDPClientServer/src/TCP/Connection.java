/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.net.*;
import java.io.*;

class Connection extends Thread {
DataInputStream in;
DataOutputStream out;
Socket clientSocket;
public Connection (Socket aClientSocket) {
try {
clientSocket = aClientSocket;
in = new DataInputStream( clientSocket.getInputStream());
out = new DataOutputStream( clientSocket.getOutputStream()); this.start();
}
catch(IOException e){
System.out.println ("Connection:"+e.getMessage());
}
}
public void run() { 
try {
String data = in.readUTF(); 
out.writeUTF(data); 
clientSocket.close();
}
catch (EOFException e){
System.out.println ("EOF:"+e.getMessage());
}
catch (IOException e) {
System.out.println ("readline:"+e.getMessage());
}
 
}
}
