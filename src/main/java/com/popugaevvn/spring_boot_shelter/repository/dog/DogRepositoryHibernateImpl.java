package com.popugaevvn.spring_boot_shelter.repository.dog;

import com.popugaevvn.spring_boot_shelter.exceptions.NotFoundEntityException;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepositoryHibernateImpl implements DogRepository {

    private final SessionFactory sessionFactory =
            new Configuration()
                    .configure()
                    .addAnnotatedClass(Dog.class)
                    .buildSessionFactory();

    @Override
    public List<Dog> index() {
        try (Session session = sessionFactory.openSession()) {
            Query<Dog> query = session.createQuery("from Dog", Dog.class);

            return query.list();
        }
    }

    @Override
    public Dog getDogById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Dog returnDog = session.find(Dog.class, id);

            if (returnDog == null) throw new NotFoundEntityException("Not found Dog with id = " + id);

            transaction.commit();

            return returnDog;
        }
    }

    @Override
    public void save(Dog dog) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(dog);
            transaction.commit();
        }
    }

    @Override
    public Dog updateDog(Dog dogForUpdating) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Dog updatedDog = session.merge(dogForUpdating);
            transaction.commit();

            return updatedDog;
        }
    }

    @Override
    public List<Dog> getYoungerDog(byte maxAge) {
        try (Session session = sessionFactory.openSession()) {
            Query<Dog> query = session.createQuery("from Dog where age < :maxAge", Dog.class);
            query.setParameter("maxAge", maxAge);
            return query.list();
        }
    }

    @Override
    public void deleteDog(int dogId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery("delete from Dog where id = :dogId", null);
            query.setParameter("dogId", dogId);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
