/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMICalc;

import java.rmi.RemoteException;

/**
 *
 * @author Student
 */
public class CalculatorImpl extends java.rmi.server.UnicastRemoteObject implements Calculator {

    public CalculatorImpl() throws java.rmi.RemoteException {
        super();
    }

    public long add(long a, long b) throws RemoteException {
        return a + b;
    }

    public long sub(long a, long b) throws RemoteException {
        return a - b;
    }

    public long mul(long a, long b) throws RemoteException {
        return a * b;
    }

    public long div(long a, long b) throws RemoteException {
        return a / b;
    }

}
