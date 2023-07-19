package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class CarDaoImpl implements CarDao {
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }
    @Override
    public Car get(Long id) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("select c from Car c where c.id =: id");
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
