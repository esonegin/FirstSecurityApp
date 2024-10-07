package ru.onegin.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onegin.springcourse.FirstSecurityApp.models.Person;

import java.util.Optional;

/**
 * @author onegines
 * @date 07.10.2024
 */
@Repository
public interface PeopleRapository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
