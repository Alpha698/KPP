
package bank;

import java.rmi.*;


public interface BillingService extends Remote {
   // определение новой карты
  public void addNewCard(String personName, String card) throws RemoteException;
  // добавить денежные средства на карту
  public void addMoney(String card, double money) throws RemoteException;
  // снять денежные средства с карты
  public void subMoney(String card, double money) throws RemoteException;
  // получение баланса карты
  public double getCardBalance(String card) throws RemoteException; 
}
