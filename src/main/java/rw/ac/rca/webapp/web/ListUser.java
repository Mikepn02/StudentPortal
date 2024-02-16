package rw.ac.rca.webapp.web;

import rw.ac.rca.webapp.dao.UserDAO;
import rw.ac.rca.webapp.dao.impl.UserDAOImpl;
import rw.ac.rca.webapp.orm.User;
import rw.ac.rca.webapp.util.UserRole;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = UserDAOImpl.getInstance();

    public ListUser(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");

        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");
        System.out.println("User in session is: " + user);

        if(pageRedirect != null){
            if(pageRedirect.equals("users") && request.getParameter("action").equals("list")){
                List<User> users = userDAO.getAllUsers();
                httpSession.setAttribute("users", users);
                UserRole[] userRoles = UserRole.values();
                httpSession.setAttribute("userRoles", userRoles);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user.jsp");
                requestDispatcher.forward(request , response);

            }else{
                httpSession.setAttribute("user","Invalid User. Try Again!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/users.jsp");
                dispatcher.forward(request , response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
