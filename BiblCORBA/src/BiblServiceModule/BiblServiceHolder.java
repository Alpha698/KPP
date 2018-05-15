package BiblServiceModule;


public final class BiblServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public BiblServiceModule.BiblService value = null;

  public BiblServiceHolder ()
  {
  }

  public BiblServiceHolder (BiblServiceModule.BiblService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = BiblServiceModule.BiblServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    BiblServiceModule.BiblServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return BiblServiceModule.BiblServiceHelper.type ();
  }

}
