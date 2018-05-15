package bank;

 
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.*;
import java.rmi.server.*; 

public class BillingServiceImpl extends UnicastRemoteObject implements BillingService {
  private   Hashtable hash;   // хэш-таблица для хранения карт
  // инициализация сервера
  public BillingServiceImpl() throws RemoteException{
  super();
  hash = new Hashtable();
  }

  // реализация метода addNewCard интерфейса BillingService
    @Override
  public void addNewCard(String personName, String card)
  throws RemoteException {

  hash.put(card, new Double(0.0));
  }

 // реализация метода addMoney интерфейса BillingService
    @Override
  public void addMoney(String card, double money) throws RemoteException {
  Double d = (Double)hash.get(card);

  if (d!=null) {hash.put(card,new Double(d.doubleValue()+money));}
  else {throw new NotExistsCardOperation();}
  }

  // реализация метода subMoney интерфейса BillingService
    @Override
  public void subMoney(String card, double money) throws RemoteException {
  Double d = (Double)hash.get(card);

  if (d!=null) hash.put(card,new Double(d.doubleValue()-money));
  else throw new NotExistsCardOperation();
  }

  // реализация метода getCardBalance интерфейса BillingService
  public double getCardBalance(String card) throws RemoteException {
  Double d = (Double)hash.get(card);
  if (d!=null) return d.doubleValue();
  else throw new NotExistsCardOperation();
  };

  // запуск удаленного объекта BillingService
  public static void main (String[] args) throws Exception {
  System.out.println("Initializing BillingService...");

  // создание удаленного объекта
  BillingService service = new BillingServiceImpl();
  
  String ip = "localhost";
  int port = 1151;
  //задание имени удаленного объекта
  String serviceName = "rmi://"+ip+":"+port+"/BillingService";
  // регистрация удаленного объекта BillingService в реестре rmiregistry
  LocateRegistry.createRegistry(port);
  Naming.rebind(serviceName, service);
  }  
}
