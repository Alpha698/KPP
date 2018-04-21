/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package var2;

/**
 *
 * @author Alpha
 */
import java.io.*;
import java.net.*;

public class BillingClientService extends Thread {
ObjectInputStream ois;
ObjectOutputStream oos;
BillingService bs;
Socket s;
public BillingClientService(BillingService bs,Socket s){ System.out.println("Constructor BillingClientService\n"); this.bs = bs;
this.s = s;
try {
this.oos = new ObjectOutputStream(s.getOutputStream()); this.ois = new ObjectInputStream(s.getInputStream());
} catch (IOException e) {
//	TODO Auto-generated catch block e.printStackTrace();
}

System.out.println("Stream`s done \n socket="+s);
}
public void run(){
System.out.println("ClientService thread started\n");
boolean work = true;
while (work) {
int command;
Object o;
try {
 
o = ois.readObject();
if (o instanceof Card[]) {
Card[] cards = (Card[])o;
for (int i=0;i<cards.length;i++){
bs.addNewCard(cards[i]);
}
}else if (o instanceof CardOperation[]){
CardOperation[] co = (CardOperation[])o;
for (int i=0;i<co.length;i++){
bs.addMoney(co[i].card,co[i].amount);
}
}else if (o instanceof String){
oos.writeObject(bs.getCard((String)o));
}else System.out.println("Bad operation");
} catch (IOException e) { e.printStackTrace();
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
}
}
}

