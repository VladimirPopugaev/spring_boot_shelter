package com.popugaevvn.spring_boot_shelter.repository.shelter;

import com.popugaevvn.spring_boot_shelter.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepositoryHibernateAuto extends JpaRepository<Shelter,Integer> {

}
