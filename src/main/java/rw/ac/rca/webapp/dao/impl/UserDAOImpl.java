package rw.ac.rca.webapp.dao.impl;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import rw.ac.rca.webapp.dao.UserDAO;
import rw.ac.rca.webapp.orm.User;
import rw.ac.rca.webapp.util.UserRole;

import java.util.List;

public class UserDAOImpl extends DAO implements UserDAO {
    public static final Logger LOG = Logger.getLogger(UserDAOImpl.class);
    public static UserDAOImpl instance;

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            return new UserDAOImpl();
        } else {
            return instance;
        }
    }

    private UserDAOImpl() {
    }

    @Override
    public User saveUser(User user) {
        try {
            begin();
            User usr = (User) getSession().save(user);
            commit();

            return usr;
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            begin();
            getSession().update(user);
            commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
            return null;
        }
    }

    @Override
    public User saveOrUpdateUser(User user) {
        try {
            begin();
            getSession().saveOrUpdate(user);
            commit();
            return user;
        } catch (HibernateException e) {
//            LOG.error("Error while saving or updating user", e.printStackTrace());
            e.printStackTrace();
            rollback();
            return null;
        }
    }


    @Override
    public boolean deleteUser(User user) {
        try {
            begin();
            getSession().delete(user);
            commit();
            return true;
        } catch (Exception e) {
            rollback();
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            begin();
            Query<User> query = getSession().createQuery("from User where id= :id", User.class);
            query.setParameter("id", id);
            User user = query.uniqueResult();
            commit();
            return user;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            begin();
            Query<User> query = getSession().createQuery("FROM User");
            List<User> users = query.list();
            commit();
            return users;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public List<User> getUserByUserName(String username) {
        try {
            begin();
            Query<User> query = getSession().createQuery("from User  where username= :ref");
            query.setParameter("ref", username);
            List<User> users = query.list();
            commit();
            return users;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public List<User> getUserByFullName(String fullName) {
        try {
            begin();
            Query<User> query = getSession().createQuery("from User " +
                    "where fullName= :ref");
            query.setParameter("ref", fullName);
            List<User> users = query.list();
            commit();
            return users;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public User getUserByUserNameAndPassword(String username, String password) {
        try {
            begin();
            Query query = getSession().createQuery("FROM User u WHERE u.username = :usrn AND " + "u.password = :pswd");
            query.setParameter("usrn", username);
            query.setParameter("pswd", password);
            User user = (User) query.uniqueResult();
            commit();
            return user;
        } catch (Exception e) {
            rollback();
            return null;
        }
    }

    @Override
    public User getUserByDetails(String username, String email, String password) {
        String name = "";
        User user = null;
        try {
            begin();
            CriteriaBuilder builder = getSession().getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);

            Predicate usernamePredicate = builder.equal(root.get("username"), username);
            Predicate emailPredicate = builder.equal(root.get("email"), email);
            Predicate passwordPredicate = builder.equal(root.get("password"), password);

            query.select(root)
                    .where(builder.or(usernamePredicate, emailPredicate), passwordPredicate);

            user = getSession().createQuery(query).uniqueResult();
            commit();

            if (user != null) {
                name = user.getFullName();
                LOG.info("The user full name  is --> " + name);
            } else {
                LOG.info("The user [ " + username + " ] with password [ " + password + " ] failed to login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Type of exception occured in getUserByDetails() is --> " + e);
            rollback();
        }
        return user;
    }

    @Override
    public List<User> getUserByUserRoleAndApprovalStatus(UserRole userRole) {
        try {
            begin();
            Query<User> query = getSession().createQuery("from User usr where usr.userRole =" + userRole.ordinal());
            List<User> items = query.list();
            commit();
            return items;

        } catch (Exception e) {
            rollback();
            return null;
        }
    }
}
