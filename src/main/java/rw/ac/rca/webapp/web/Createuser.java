package rw.ac.rca.webapp.web;

import rw.ac.rca.webapp.dao.UserDAO;
import rw.ac.rca.webapp.dao.impl.UserDAOImpl;
import rw.ac.rca.webapp.orm.User;
import rw.ac.rca.webapp.util.UserRole;
import rw.ac.rca.webapp.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Createuser extends HttpServlet {
    private static final long serialVersionUID = 102831973239L;
    private UserDAO userDAO = UserDAOImpl.getInstance();

    public Createuser(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        UserRole[] userRoles = UserRole.values();
        httpSession.setAttribute("userRoles",userRoles);
        request.getRequestDispatcher("WEB-INF/createuser.jsp").forward(request , response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        User user = new User();

        String usernameauth = request.getParameter("username");
        String passwordauth = request.getParameter("password");
        String userFullname = request.getParameter("userfullname");
        String email = request.getParameter("email");
        String userRole = request.getParameter("userRole");
        UserRole usrr = UserRole.valueOf(userRole);


        try {
            String hashedPsw = Util.generateHashed512(passwordauth);

            user.setUsername(usernameauth);
            user.setFullName(userFullname);
            user.setPassword(hashedPsw);
            user.setEmail(email);
            user.setUserRole(usrr);

            userDAO.saveOrUpdateUser(user);
            httpSession.setAttribute("success", "Created successfully");
            request.getRequestDispatcher("WEB-INF/createuser.jsp").forward(
                    request, response);
        }catch (Exception e) {
            httpSession.setAttribute("error", "Can't Create");
            request.getRequestDispatcher("WEB-INF/createuser.jsp").forward(
                    request, response);
        }
    }
}
