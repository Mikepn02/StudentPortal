package rw.ac.rca.webapp.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import rw.ac.rca.webapp.util.SmisSessionFactory;

public class DAO {
    protected DAO() {
    }


    public static Session getSession() {
        return SmisSessionFactory.getSession();
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }
    /**
     * rolls back for failed transaction closes databases connection
     *
     * @throws HibernateException
     *
     */
    protected void rollback(){
        try{
            getSession().getTransaction().rollback();
        }catch (HibernateException e){
            System.out.println("Can not rollback: "+e.toString());
        }finally {
            close();
        }
    }

    protected void close() {
        try {
            getSession().close();
        }catch (HibernateException e){
            System.out.println("Can not close: "+ e.toString());
        }
    }

    protected void clear(){
        getSession().clear();
    }

}
