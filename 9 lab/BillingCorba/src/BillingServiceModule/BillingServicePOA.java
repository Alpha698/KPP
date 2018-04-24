package BillingServiceModule;



public abstract class BillingServicePOA extends org.omg.PortableServer.Servant
 implements BillingServiceModule.BillingServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addNewCard", new java.lang.Integer (0));
    _methods.put ("addMoney", new java.lang.Integer (1));
    _methods.put ("subMoney", new java.lang.Integer (2));
    _methods.put ("getCardBalance", new java.lang.Integer (3));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // BillingServiceModule/BillingService/addNewCard
       {
         String personName = in.read_string ();
         String card = in.read_string ();
         this.addNewCard (personName, card);
         out = $rh.createReply();
         break;
       }

       case 1:  // BillingServiceModule/BillingService/addMoney
       {
         String card = in.read_string ();
         double money = in.read_double ();
         this.addMoney (card, money);
         out = $rh.createReply();
         break;
       }

       case 2:  // BillingServiceModule/BillingService/subMoney
       {
         String card = in.read_string ();
         double money = in.read_double ();
         this.subMoney (card, money);
         out = $rh.createReply();
         break;
       }

       case 3:  // BillingServiceModule/BillingService/getCardBalance
       {
         String card = in.read_string ();
         double $result = (double)0;
         $result = this.getCardBalance (card);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BillingServiceModule/BillingService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public BillingService _this() 
  {
    return BillingServiceHelper.narrow(
    super._this_object());
  }

  public BillingService _this(org.omg.CORBA.ORB orb) 
  {
    return BillingServiceHelper.narrow(
    super._this_object(orb));
  }


} // class BillingServicePOA
