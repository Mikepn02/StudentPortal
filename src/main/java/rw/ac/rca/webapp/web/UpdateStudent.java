package rw.ac.rca.webapp.web;

import rw.ac.rca.webapp.dao.AddressDAO;
import rw.ac.rca.webapp.dao.StudentDAO;
import rw.ac.rca.webapp.dao.impl.AddressDAOImpl;
import rw.ac.rca.webapp.dao.impl.StudentDAOImpl;
import rw.ac.rca.webapp.orm.Address;
import rw.ac.rca.webapp.orm.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UpdateStudent extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = StudentDAOImpl.getInstance();
    private AddressDAO addressDAO = AddressDAOImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");
        System.out.println("The user in session: " + user);

        if (pageRedirect != null && pageRedirect.equals("updatestudent")) {
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            Student student = studentDAO.getStudentById(studentId);

            if (student != null) {
                List<Address> addressList = addressDAO.getAllAddresses();
                request.setAttribute("address", addressList);
                request.setAttribute("student", student);
                request.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(request, response);
            } else {
                request.setAttribute("UpdateStudentError", "Student not found");
                request.getRequestDispatcher("/WEB-INF/updateStudent.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("UpdateStudentError", "Invalid page");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageRedirect = request.getParameter("page");
        HttpSession httpSession = request.getSession();
        Object user = httpSession.getAttribute("authenticatedUser");
        System.out.println("The user in session: " + user);

        if (pageRedirect != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (pageRedirect.equals("updatestudent")) {
                int studentId = Integer.parseInt(request.getParameter("studentId"));
                System.out.println("the id of user to be updated: "+ studentId);
                Student studentToUpdate = studentDAO.getStudentById(studentId);
                System.out.println("the student data to update:  "+studentToUpdate);

                if (studentToUpdate != null) {
                    Address address = addressDAO.getAddressById(Integer.parseInt(request.getParameter("address")));
                    request.setAttribute("student", studentToUpdate);
                    request.setAttribute("address", address);
                    try {
                        boolean isInternational = (Integer.parseInt(request.getParameter("internation")) == 1);
                        boolean isPartTime = (Integer.parseInt(request.getParameter("partTime")) == 1);
                        boolean isRepeating = (Integer.parseInt(request.getParameter("repeating")) == 1);
                        studentToUpdate.setFirstName(request.getParameter("firstName"));
                        studentToUpdate.setLastName(request.getParameter("lastName"));
                        studentToUpdate.setPhoneNumber(request.getParameter("phone"));
                        studentToUpdate.setInternational(isInternational);
                        studentToUpdate.setPartTime(isPartTime);
                        studentToUpdate.setRepeating(isRepeating);
                        studentToUpdate.setDateOfBirth(simpleDateFormat.parse(request.getParameter("birth")));
                        studentToUpdate.setAddress(address);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        studentDAO.updateStudent(studentToUpdate);
                        request.setAttribute("successStudent", "Successfully updated the student");
                        request.getRequestDispatcher("WEB-INF/updateStudent.jsp").forward(request, response);
                    } catch (Exception e) {
                        request.setAttribute("UpdateStudentError", "Failed to update the student");
                        request.getRequestDispatcher("WEB-INF/updateStudent.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("UpdateStudentError", "Student not found");
                    request.getRequestDispatcher("WEB-INF/updateStudent.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }




}
