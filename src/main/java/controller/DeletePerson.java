package controller;

import lombok.SneakyThrows;
import service.PersonDAO;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class DeletePerson extends HttpServlet {

    PersonDAO service = new PersonDAO();

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
            service.deletePerson("CN=Tran Aaa An,CN=Users,DC=test,DC=hivetech,DC=vn");
    }

}
