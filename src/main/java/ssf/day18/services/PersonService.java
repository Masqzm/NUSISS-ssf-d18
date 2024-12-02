package ssf.day18.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssf.day18.models.Person;
import ssf.day18.repo.ListRepo;

@Service
public class PersonService {
    @Autowired
    ListRepo listRepo;

    public void createPerson(Person person) {
        person.setId(Person.getNewId());

        listRepo.rightPush("PERSONS", person.toCSV());
    }
}
