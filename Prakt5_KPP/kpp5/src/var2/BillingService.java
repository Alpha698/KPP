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
import java.net.*;
import java.util.Hashtable;
import java.io.*;
public class BillingService extends Thread{
private int serverPort = 7896;
private ServerSocket ss;
private Hashtable hash;

public static void main(String[] args) {
BillingService bs = new BillingService();
bs.start();
}
public BillingService(){
hash = new Hashtable();
}

public void run(){
try {
ss	= new ServerSocket(serverPort); System.out.println("Server started"); while(true){
System.out.println("new client waiting..."); Socket s = ss.accept(); System.out.println("Client accepted"); BillingClientService bcs = new BillingClientService(this,s); System.out.println("bcs created");

bcs.start();
}
} catch (IOException e) {
e.printStackTrace();
}

}

public void addNewCard(Card card) {
hash.put(card.cardNumber, card);
}
public void addMoney(String card, double money) { Card c = (Card)hash.get(card);
if (c==null) {
 
System.out.println("Bad Card number\n");
return;
};
c.balance+=money;
hash.put(card,c);
}
public Card getCard(String card){
return (Card)hash.get(card);
}
}

