/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba_kpp3;

/**
 *
 * @author Student
 */
import java.util.Calendar; import java.util.Date;

public class RunPattern {
private static Calendar dateCreator = Calendar.getInstance(); 
public static Date createDate(int year, int month, int day){
dateCreator.set(year, month, day); return dateCreator.getTime();
}

public static void main(String[] args) { 
    System.out.println("Creating Appointment ");
    Contact[] people= {new ContactImpl(),new ContactImpl()}; 
    Appointment appointment = new Appointment("Test", people,new LocationImpl(""),createDate(2012,02,05), createDate(2012,03,12)); 
    ChangeLocationCommand cmd = new ChangeLocationCommand(); 
    cmd.setAppointment(appointment);
    CommandGUI application = new CommandGUI(cmd);
 
application.setAppointment(appointment); 
cmd.setLocationEditor(application); 
application.GreateGUI();
}
}
