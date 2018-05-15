package BiblServiceModule;



abstract public class BiblServiceHelper
{
  private static String  _id = "IDL:BiblServiceModule/BiblService:1.0";

  public static void insert (org.omg.CORBA.Any a, BiblServiceModule.BiblService that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static BiblServiceModule.BiblService extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (BiblServiceModule.BiblServiceHelper.id (), "BiblService");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static BiblServiceModule.BiblService read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_BiblServiceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, BiblServiceModule.BiblService value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static BiblServiceModule.BiblService narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof BiblServiceModule.BiblService)
      return (BiblServiceModule.BiblService)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      BiblServiceModule._BiblServiceStub stub = new BiblServiceModule._BiblServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static BiblServiceModule.BiblService unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof BiblServiceModule.BiblService)
      return (BiblServiceModule.BiblService)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      BiblServiceModule._BiblServiceStub stub = new BiblServiceModule._BiblServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
