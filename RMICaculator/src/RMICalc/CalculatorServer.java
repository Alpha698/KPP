/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMICalc;

import java.rmi.Naming;

public class CalculatorServer {
  public CalculatorServer(){
      try{
      Calculator c=new CalculatorImpl();
          Naming.rebind("rmi://127.0.0.1:1099/CalculatorService", c);         
  }catch(Exception e){
      System.out.println("Ошибка"+e);
  }
  }
    public static void main(String[] args) {
        new CalculatorServer();
        
    }
}
