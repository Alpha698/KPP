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
import java.awt.Container; import java.awt.event.*; import javax.swing.*;
public class CommandGUI implements ActionListener, LocationEditor { private JFrame mainFrame;
private JTextArea display;
private JTextField updatedLocation; private JButton update, undo,redo,exit;
private JPanel controlPanel, displayPanel, editorPanel; private UndoableCommand command;
private Appointment appointment;

public CommandGUI(UndoableCommand newCommand){ command = newCommand;
}
public void setAppointment(Appointment newAppointment){ appointment = newAppointment;
}
public void GreateGUI(){
mainFrame = new JFrame("Command Pattern Example "); Container content = mainFrame.getContentPane(); content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));

editorPanel = new JPanel(); editorPanel.add(new JLabel("Location")); updatedLocation = new JTextField(20); editorPanel.add(updatedLocation); content.add(editorPanel);
 
displayPanel = new JPanel(); display = new JTextArea(10,40); display.setEditable(false); displayPanel.add(display); content.add(displayPanel);

controlPanel = new JPanel();
update = new JButton("Update Location"); undo			= new JButton("Undo Location"); redo		= new JButton("Redo Location"); exit	= new JButton("Exit"); controlPanel.add(update); controlPanel.add(undo); controlPanel.add(redo); controlPanel.add(exit); content.add(controlPanel);

update.addActionListener(this); undo.addActionListener(this); redo.addActionListener(this); exit.addActionListener(this);

refreshDisplay();
mainFrame.addWindowListener(new WindowCloseManager()); mainFrame.pack();
mainFrame.setVisible(true);
}
@Override
public void actionPerformed(ActionEvent evt){ Object sender = evt.getSource(); if(sender==update){
executeCommand();
}
if(sender==undo){ undoCommand();
}
if(sender==redo){ redoCommand();
}
if(sender==exit){ exitApplication();
}
}
private class WindowCloseManager extends WindowAdapter{ @Override
public void windowClosing(WindowEvent evt){ exitApplication();
}
}

@Override
public Location getNewLocation(){
return new LocationImpl(updatedLocation.getText());
 
}

private void exitApplication(){ System.exit(0);
}

private void refreshDisplay(){ display.setText(appointment.toString());
}

private void executeCommand(){ command.execute(); refreshDisplay();
}
private void undoCommand(){ command.undo(); refreshDisplay();
}
private void redoCommand(){ command.redo(); refreshDisplay();
}

}
