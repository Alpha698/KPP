package BillingServiceModule;


public final class BillingServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public BillingServiceModule.BillingService value = null;

  public BillingServiceHolder ()
  {
  }

  public BillingServiceHolder (BillingServiceModule.BillingService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BillingServiceModule.BillingServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BillingServiceModule.BillingServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BillingServiceModule.BillingServiceHelper.type ();
  }

}
