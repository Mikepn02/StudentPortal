package rw.ac.rca.webapp.web;

import org.apache.log4j.Logger;
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

public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final Logger LOG = Logger.getLogger(Login.class);
    private UserDAO userDAO = UserDAOImpl.getInstance();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("logout") != null) {
            request.getSession().invalidate();
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        } else {
            String username = request.getParameter("username");
            System.out.println("Session ID in welcome page >>>>>>>>: " + request.getSession().getId()
                    + " User is >>>>: " + username);

            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String errorMessage = null;

        System.out.println("The username is: " + username);
        System.out.println("The password is: " + password);

        if (username == null || username.equals("")) {
            errorMessage = "Username can't be null or empty";
        }
        if (password == null || password.equals("")) {
            errorMessage = "Password can't be null or empty";
        }
        if (password == null || password.equals("") && username == null || username.equals("")) {
            errorMessage = "Username & Password  can't be null or empty";
        }
        if (errorMessage != null) {
            httpSession.setAttribute("error", errorMessage);
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        } else {
            try {
                User authenticatedUser = userDAO.getUserByDetails(username, username, Util.generateHashed512(password));
                System.out.println(authenticatedUser);

                if (authenticatedUser != null) {
                    System.out.println(authenticatedUser.getFullName());
                    UserRole privilege = authenticatedUser.getUserRole();

                    LOG.info("The user [ " + privilege + " ] with privilege [ "
                            + authenticatedUser.getUserRole().getRoleDescription() + " ] successfuly logged in");
                    httpSession.setAttribute("authenticatedUser", authenticatedUser);
                    httpSession.setAttribute("privilege", privilege);

                    if (privilege.equals(UserRole.ADMINISTRATOR)) {
                        request.getRequestDispatcher("WEB-INF/homeadmin.jsp").forward(request, response);
                    } else if(authenticatedUser != null && privilege.equals(UserRole.GUEST)){
                        request.getRequestDispatcher("WEB-INF/homeguest.jsp").forward(request, response);
                    } else if (privilege.equals(UserRole.EMPLOYEE)) {
                        request.getRequestDispatcher("WEB-INF/homeemployee.jsp").forward(request, response);
                    }
                } else {
                    System.out.println("We have failed to fetch the user");
                    errorMessage = "Invalid user. Try again!";
                    LOG.info("Authentication failed. Username: " + username + " >>> pwd: " + password);
                    httpSession.setAttribute("error", errorMessage);
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.out.println("We have failed to fetch the user");
                errorMessage = "Invalid user. Try again!";
                LOG.info("Authentication failed. Username: " + username + " >>> pwd: " + password);
                httpSession.setAttribute("error", errorMessage);
                request.getSession().invalidate();
                doGet(request , response);
            }
        }
    }
}
