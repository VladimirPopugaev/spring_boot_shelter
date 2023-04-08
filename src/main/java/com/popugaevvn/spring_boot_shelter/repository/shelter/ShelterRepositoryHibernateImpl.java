package com.popugaevvn.spring_boot_shelter.repository.shelter;

import com.popugaevvn.spring_boot_shelter.exceptions.NotFoundEntityException;
import com.popugaevvn.spring_boot_shelter.models.Dog;
import com.popugaevvn.spring_boot_shelter.models.Shelter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShelterRepositoryHibernateImpl implements ShelterRepository {

    private final SessionFactory sessionFactory =
            new Configuration()
                    .configure()
                    .addAnnotatedClass(Shelter.class)
                    .addAnnotatedClass(Dog.class)
                    .buildSessionFactory();

    @Override
    public List<Shelter> index() {
        try (Session session = sessionFactory.openSession()) {
            Query<Shelter> query = session.createQuery("from Shelter", Shelter.class);

            return query.list();
        }
    }

    @Override
    public Shelter getShelterById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Shelter returnShelter = session.find(Shelter.class, id);

            if (returnShelter == null) throw new NotFoundEntityException("Not found Shelter with id = " + id);

            transaction.commit();

            return returnShelter;
        }
    }

    @Override
    public void save(Shelter shelter) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(shelter);
            transaction.commit();
        }
    }

    @Override
    public Shelter updateShelter(Shelter shelterForUpdating) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Shelter updatedShelter = session.merge(shelterForUpdating);
            transaction.commit();

            return updatedShelter;
        }
    }

    // TODO: implement dog relation
    @Override
    public List<Dog> getDogsFromShelter(int shelterId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Shelter returnShelter = session.find(Shelter.class, shelterId);

            if (returnShelter == null) throw new NotFoundEntityException("Not found Shelter with id = " + shelterId);

            transaction.commit();

            return returnShelter.getDogs();
        }
    }

    // TODO: implement dog relation
    @Override
    public Shelter addDogInShelter(int shelterId, Dog dog) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Shelter returnShelter = session.find(Shelter.class, shelterId);

            if (returnShelter == null) throw new NotFoundEntityException("Not found Shelter with id = " + shelterId);

            returnShelter.addDog(dog);
            session.merge(returnShelter);

            transaction.commit();

            return returnShelter;
        }
    }

    @Override
    public void deleteShelter(int shelterId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery("delete from Shelter where id = :shelterId", null);
            query.setParameter("shelterId", shelterId);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
