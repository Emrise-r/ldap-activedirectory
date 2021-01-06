package controller;

import model.Person;
import service.PersonDAO;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdatePerson extends HttpServlet {

    PersonDAO service = new PersonDAO();

    Person person = new Person("CN=Tran Lam Vinh,CN=Users,DC=test,DC=hivetech,DC=vn","vinhtl","aaaaa");

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            service.updatePerson(person);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
