package model;

import java.io.Serializable;

public class PersonDTO implements Serializable {
    private String sAMAccountName;
    private String givenName;

    public PersonDTO(String sAMAccountName, String givenName) {
        this.sAMAccountName = sAMAccountName;
        this.givenName = givenName;
    }

    public PersonDTO() {
    }

    public String getsAMAccountName() {
        return sAMAccountName;
    }

    public void setsAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
}
