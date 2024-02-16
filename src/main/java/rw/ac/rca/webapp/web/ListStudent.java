package rw.ac.rca.webapp.web;

import rw.ac.rca.webapp.dao.StudentDAO;
import rw.ac.rca.webapp.dao.impl.StudentDAOImpl;
import rw.ac.rca.webapp.orm.Student;
import rw.ac.rca.webapp.util.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListStudent extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = StudentDAOImpl.getInstance();

    public ListStudent() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        String pageRedirectRole = request.getParameter("user_role");

        HttpSession httpSession = request.getSession();
        Object user = request.getAttribute("authenticatedUser");
        System.out.println("The user in session is: "+ user);

        if(pageRedirect != null){
           if(pageRedirect.equals("students") && request.getParameter("action").equals("list") && pageRedirectRole.equals("adm")){
               List<Student> students = studentDAO.getAllStudent();
               httpSession.setAttribute("students",students);
               UserRole[] userRoles = UserRole.values();
               httpSession.setAttribute("userRoles",userRoles);
               request.getRequestDispatcher("WEB-INF/students.jsp").forward(request, response);
           }
        } else {
            httpSession.setAttribute("error", "Invalid User. Try again!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
