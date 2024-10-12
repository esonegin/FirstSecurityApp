package ru.onegin.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.onegin.springcourse.FirstSecurityApp.models.Person;
import ru.onegin.springcourse.FirstSecurityApp.repositories.PeopleRepository;
import ru.onegin.springcourse.FirstSecurityApp.security.PersonDetails;

import java.util.Optional;

/**
 * @author onegines
 * @date 07.10.2024
 */
@Service
public class PersonDetailService implements UserDetailsService {
    private final PeopleRepository peopleRapository;

    @Autowired
    public PersonDetailService(PeopleRepository peopleRapository) {
        this.peopleRapository = peopleRapository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRapository.findByUsername(username);
        if(person.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        return new PersonDetails(person.get());
    }
}
