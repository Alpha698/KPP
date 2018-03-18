/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.net.*;
import java.io.*;

public class TCPClient {
public static void main (String args[]) {
Socket s = null;
try {
int serverPort = 7896;
s = new Socket(args[1], serverPort);
DataInputStream in = new DataInputStream(s.getInputStream()); DataOutputStream out =new DataOutputStream(s.getOutputStream()); out.writeUTF(args[0]);

String data = in.readUTF(); 
}
catch (UnknownHostException e) { System.out.println("Сокет:" + e.getMessage());

}
catch (EOFException e) { System.out.println("EOF:" +e.getMessage());

}
catch (IOException e) {
System.out.println("readline:" +e.getMessage());

}
finally { if(s!=null)

try { s.close();
 
}
catch (IOException e){
System.out.println("close:" + e.getMessage());
}
}
}
}

