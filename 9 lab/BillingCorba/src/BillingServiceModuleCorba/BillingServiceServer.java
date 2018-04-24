/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillingServiceModuleCorba;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.POA;
import BillingServiceModule.*;
import org.omg.PortableServer.POAHelper;

public class BillingServiceServer {
    public static void main(String args[]){
        try{
            ORB orb=ORB.init(args,null);
            POA rootpoa=POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            BillingServiceImpl BSImpl=new BillingServiceImpl();
            BSImpl.setORB(orb);
            org.omg.CORBA.Object ref=rootpoa.servant_to_reference(BSImpl);
            BillingService href=BillingServiceHelper.narrow(ref);
            org.omg.CORBA.Object objRef=orb.resolve_initial_references("NameService");
            NamingContextExt ncRef=NamingContextExtHelper.narrow(objRef);
            String name="BillingService";
            NameComponent path[]=ncRef.to_name(name);
            ncRef.rebind(path, href);
            System.out.println("BillingServiceServer ready and waiting ...");
            orb.run();
        }
        catch(Exception e){
            System.err.println("ERROR: "+e);
            e.printStackTrace(System.out);
        }
        System.out.println("BillingService Exiting ..");
    }
}
