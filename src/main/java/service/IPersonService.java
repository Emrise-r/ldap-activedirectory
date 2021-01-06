package service;

import model.Person;

import javax.naming.NamingException;

public interface IPersonService {
    void getAllPerson() throws NamingException;
    void createPerson(Person person) throws NamingException;
    void deletePerson(String distinguishedName) throws NamingException;
    void updatePerson(Person person) throws NamingException;
}
