package BillingServiceModule;



public interface BillingServiceOperations 
{
  void addNewCard (String personName, String card);
  void addMoney (String card, double money);
  void subMoney (String card, double money);
  double getCardBalance (String card);
} // interface BillingServiceOperations
