/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillingServiceModuleCorba;
import java.util.Hashtable;
import BillingServiceModule.*;
import org.omg.CORBA.*;
/**
 *
 * @author Student
 */
public class BillingServiceImpl extends BillingServicePOA{
private ORB orb;
private Hashtable hash=new Hashtable();
public void setORB(ORB orb_val){
    orb=orb_val;
}
    public void addNewCard(String personName, String card) {
   hash.put(card, new Double(0.0));
    }

    @Override
    public void addMoney(String card, double money) {
  Double d=(Double)hash.get(card);
  if(d!=null) hash.put(card,new Double(d.doubleValue()+money));
    }

    @Override
    public void subMoney(String card, double money) {
   Double d=(Double)hash.get(card);
    if(d!=null) hash.put(card,new Double(d.doubleValue()-money));
    }

    @Override
    public double getCardBalance(String card) {
     Double d=(Double)hash.get(card);
    if(d!=null) return d.doubleValue();
    else return 0;
    }
    
    
}
