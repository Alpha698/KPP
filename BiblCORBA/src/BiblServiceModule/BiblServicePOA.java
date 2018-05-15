package BiblServiceModule;


public abstract class BiblServicePOA extends org.omg.PortableServer.Servant
 implements BiblServiceModule.BiblServiceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addNewBook", new java.lang.Integer (0));
    _methods.put ("addNewReader", new java.lang.Integer (1));
    _methods.put ("takeBook", new java.lang.Integer (2));
    _methods.put ("returnBook", new java.lang.Integer (3));
    _methods.put ("getReaderInfo", new java.lang.Integer (4));
    _methods.put ("getFullInfo", new java.lang.Integer (5));
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

  // U W! � ? � � �  Q � CORBA-! U  X �!! Q X U V U ! �!  Q! �
       case 0:  // BiblServiceModule/BiblService/addNewBook
       {
         String newISBN = in.read_string ();
         String newBookName = in.read_string ();
         this.addNewBook (newISBN, newBookName);
         out = $rh.createReply();
         break;
       }

       case 1:  // BiblServiceModule/BiblService/addNewReader
       {
         String bilet = in.read_string ();
         String Name = in.read_string ();
         this.addNewReader (bilet, Name);
         out = $rh.createReply();
         break;
       }

       case 2:  // BiblServiceModule/BiblService/takeBook
       {
         String Name = in.read_string ();
         String Book = in.read_string ();
         this.takeBook (Name, Book);
         out = $rh.createReply();
         break;
       }

       case 3:  // BiblServiceModule/BiblService/returnBook
       {
         String rName = in.read_string ();
         String rBook = in.read_string ();
         this.returnBook (rName, rBook);
         out = $rh.createReply();
         break;
       }

       case 4:  // BiblServiceModule/BiblService/getReaderInfo
       {
         String reader = in.read_string ();
         String $result = null;
         $result = this.getReaderInfo (reader);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 5:  // BiblServiceModule/BiblService/getFullInfo
       {
         String $result = null;
         $result = this.getFullInfo ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:BiblServiceModule/BiblService:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public BiblService _this() 
  {
    return BiblServiceHelper.narrow(
    super._this_object());
  }

  public BiblService _this(org.omg.CORBA.ORB orb) 
  {
    return BiblServiceHelper.narrow(
    super._this_object(orb));
  }


} // class BiblServicePOA
