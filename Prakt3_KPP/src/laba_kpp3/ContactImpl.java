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
public class ContactImpl implements Contact { private String firstName;
private String lastName; private String title;
private String organization;
public static final String EOL=System.getProperty("line.separator");

public ContactImpl(){}
public ContactImpl(String newFirstName, String newLastName, String newTitle, String newOrganization){
firstName = newFirstName; lastName= newLastName; title=newTitle; organization = newOrganization;
}
@Override
 
public String getFirstName(){ return firstName;
}
@Override
public String getLastName(){ return lastName;
}
@Override
public String getTitle(){ return title;
}
@Override
public String getOrganization(){ return organization;
}
@Override
public void setFirstName(String newFirstName){ firstName=newFirstName;
}
@Override
public void setLastName(String newLastName){ lastName=newLastName;
}
@Override
public void setTitle(String newTitle){ title=newTitle;
}
@Override
public void setOrganization(String newOrganization){ organization=newOrganization;
}
@Override
public String toString(){
return firstName+EOL+lastName;
}
}
