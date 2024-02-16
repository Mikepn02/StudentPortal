package rw.ac.rca.webapp.dao;

import rw.ac.rca.webapp.orm.User;
import rw.ac.rca.webapp.util.UserRole;

import java.util.List;

public interface UserDAO {
    public User saveUser(User user);
    public User updateUser(User user);
    public User saveOrUpdateUser(User user);
    public  boolean deleteUser(User user);
    public User getUserById(int id);
    public List<User> getAllUsers();
    public List<User> getUserByUserName(String username);
    public List<User> getUserByFullName(String fullName);

    public User getUserByUserNameAndPassword(String username, String password);

    public User getUserByDetails(String username, String email, String password);

    public List<User> getUserByUserRoleAndApprovalStatus(UserRole userRole);
}
