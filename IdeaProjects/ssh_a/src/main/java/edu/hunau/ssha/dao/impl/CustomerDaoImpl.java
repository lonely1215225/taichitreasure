package edu.hunau.ssha.dao.impl;

import edu.hunau.ssha.dao.CustomerDao;
import edu.hunau.ssha.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> queryAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Session currentSession1 = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("from Customer");
        return query.list();
    }
}
