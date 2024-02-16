package rw.ac.rca.webapp.dao.impl;

import org.hibernate.query.Query;
import rw.ac.rca.webapp.dao.AddressDAO;
import rw.ac.rca.webapp.orm.Address;
import rw.ac.rca.webapp.orm.Student;

import java.util.List;

public class AddressDAOImpl extends DAO implements AddressDAO {
    public static AddressDAOImpl instance;

    public static AddressDAOImpl getInstance(){
        if(instance == null){
            return new AddressDAOImpl();
        }else {
            return instance;
        }
    }
    @Override
    public Address saveAddress(Address address) {
        try{
            begin();
            getSession().save(address);
            commit();
            return address;

        }catch(Exception e){
            rollback();
            return  null;
        }
    }

    @Override
    public Address updateAddress(Address address) {
        try{
            begin();
            getSession().update(address);
            commit();
            return address;

        }catch(Exception e){
            rollback();
            return  null;
        }
    }

    @Override
    public Address saveOrUpdateAddress(Address address) {
        try{
            begin();
            getSession().saveOrUpdate(address);
            commit();
            return address;

        }catch(Exception e){
            rollback();
            return  null;
        }
    }

    @Override
    public boolean deleteAddress(Address address) {
        try{
            begin();
            getSession().delete(address);
            commit();
            return true;

        }catch(Exception e){
            rollback();
            return  false;
        }
    }

    @Override
    public Address getAddressById(int addressId) {
        try{
            begin();
            Query<Address> query = getSession().createQuery("from Address where id=: ref");
            query.setParameter("ref", addressId);
            Address address = query.uniqueResult();
            commit();
            return address ;

        }catch(Exception e){
            rollback();
            return  null;
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        try{
            begin();
            Query<Address> query = getSession().createQuery("FROM Address ");
            List<Address> addresses = query.list();
            commit();
            return addresses ;

        }catch(Exception e){
            rollback();
            return  null;
        }
    }
}
