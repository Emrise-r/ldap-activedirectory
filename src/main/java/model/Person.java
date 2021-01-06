package model;

import lombok.Data;

import javax.naming.Name;
import java.io.Serializable;

@Data
public class Person implements Serializable {

    private String distinguishedName;
    private String sAMAccountName;
    private String givenName;
//    private String objectCategory;
//    private String password;

    public Person(String distinguishedName, String sAMAccountName, String givenName){
        this.distinguishedName = distinguishedName;
        this.sAMAccountName = sAMAccountName;
        this.givenName = givenName;
    }

//    public Person(String s, String vinhtl, String vinh) {}

    public Person() {

    }
}
