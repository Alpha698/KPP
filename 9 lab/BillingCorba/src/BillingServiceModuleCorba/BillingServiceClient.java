
package BillingServiceModuleCorba;
import BillingServiceModule.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class BillingServiceClient {
static BillingService BSImpl;
public static void main (String args[]){
    try{
        ORB orb=ORB.init(args,null);
        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef=NamingContextExtHelper.narrow(objRef);
        String name="BillingService";
        BSImpl = BillingServiceHelper.narrow(ncRef.resolve_str(name));
        BSImpl.addMoney("1",1234);
        System.out.println(BSImpl.getCardBalance("1"));
    }
    catch(Exception e){
        System.out.println("ERROR: "+e);
        e.printStackTrace(System.out);
    }
}
}
