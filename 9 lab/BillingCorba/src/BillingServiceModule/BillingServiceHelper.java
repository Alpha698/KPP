package BillingServiceModule;



abstract public class BillingServiceHelper
{
  private static String  _id = "IDL:BillingServiceModule/BillingService:1.0";

  public static void insert (org.omg.CORBA.Any a, BillingServiceModule.BillingService that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static BillingServiceModule.BillingService extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (BillingServiceModule.BillingServiceHelper.id (), "BillingService");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static BillingServiceModule.BillingService read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_BillingServiceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, BillingServiceModule.BillingService value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static BillingServiceModule.BillingService narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof BillingServiceModule.BillingService)
      return (BillingServiceModule.BillingService)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      BillingServiceModule._BillingServiceStub stub = new BillingServiceModule._BillingServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static BillingServiceModule.BillingService unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof BillingServiceModule.BillingService)
      return (BillingServiceModule.BillingService)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      BillingServiceModule._BillingServiceStub stub = new BillingServiceModule._BillingServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
