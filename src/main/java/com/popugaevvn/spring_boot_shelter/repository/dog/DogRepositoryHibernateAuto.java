package com.popugaevvn.spring_boot_shelter.repository.dog;

import com.popugaevvn.spring_boot_shelter.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepositoryHibernateAuto extends JpaRepository<Dog, Integer> {

    List<Dog> findDogsByAgeLessThan(int age);

}
