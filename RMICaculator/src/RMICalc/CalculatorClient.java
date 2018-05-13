/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMICalc;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 *
 * @author Student
 */
public class CalculatorClient {
    public static void main(String[] args) throws RemoteException {
        try{
            Calculator c=(Calculator)Naming.lookup("rmi://127.0.0.1/CalculatorService");
            System.out.println(c.add(45, 16));
            System.out.println(c.div(34, 3));
        }
        catch(MalformedURLException murle){
            System.out.println();
            System.out.println("MalformedURLException");
            System.out.println(murle);
        }
        catch (RuntimeException re){
            System.out.println();
            System.out.println("RemoteException");
            System.out.println(re);
            
        }
        catch(NotBoundException nbe){
            System.out.println();
            System.out.println("NotBoundException");
            System.out.println(nbe);
        }
    }
}
