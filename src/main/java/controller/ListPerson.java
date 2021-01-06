package controller;

import lombok.SneakyThrows;
import service.PersonDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/persons")
public class ListPerson extends HttpServlet {


//    @Inject
//    PersonDAO personService;
    PersonDAO personService = new PersonDAO();

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
            personService.getAllPerson();
    }
}
