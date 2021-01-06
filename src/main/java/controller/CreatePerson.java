package controller;

import model.Person;
import service.PersonDAO;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/create")
public class CreatePerson extends HttpServlet {

    PersonDAO personService = new PersonDAO();


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person("CN=Nguyen Van A,CN=Users,DC=test,DC=hivetech,DC=vn","chitx","Chi");
        try {
            personService.createPerson(person);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
