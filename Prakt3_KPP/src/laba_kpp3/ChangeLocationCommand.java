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
public class ChangeLocationCommand implements UndoableCommand{ private Appointment appointment;
private Location oldLocation; private Location newLocation; private LocationEditor editor;

public Appointment getAppointment(){ return appointment;
}
 
public void setAppointment(Appointment appointment){ this.appointment=appointment;
}
public void setLocationEditor(LocationEditor locationEditor){ editor = locationEditor;
}
@Override
public void execute(){
oldLocation = appointment.getLocation(); newLocation = editor.getNewLocation(); appointment.setLocation(newLocation);
}
@Override
public void undo(){ appointment.setLocation(oldLocation);
}
@Override
public void redo(){ appointment.setLocation(newLocation);
}
}

