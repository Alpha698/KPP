package biblcorba;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA.*;
import BiblServiceModule.*;

public class BiblServer {

    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args, null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            BiblServiceImpl BSImpl = new BiblServiceImpl();
            BSImpl.setOrb(orb);
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(BSImpl);
            BiblService href = BiblServiceHelper.narrow(ref);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            String name = "BiblService";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);
            System.out.println("Сервер BIBLSERVICE запущен...");
            orb.run();
        } catch (Exception e) {
            System.out.println(" Error "+ e);
            e.printStackTrace(System.err);
        }
         System.out.println("Сервер BIBLSERVICE остановлен...");
    }     
}
