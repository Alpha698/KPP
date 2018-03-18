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
public class LocationImpl implements Location{ private String location;
public LocationImpl(){}
public LocationImpl(String newLocation){ location= newLocation;
}
@Override
public String getLocation(){ return location;
}
@Override
public void setLocation(String newLocation){ location=newLocation;
}
@Override
public String toString(){ return location;
}
}
