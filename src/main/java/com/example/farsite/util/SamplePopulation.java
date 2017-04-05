package com.example.farsite.util;

import com.example.farsite.model.Address;
import com.example.farsite.model.Employee;
import com.example.farsite.model.Gender;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Random;

/**
 * Examples illustrating the use of JPA with the employee domain
 * com.example.farsite.model.
 * 
 * @see com.example.farsite.jpa.model.ExampleTest
 */
public class SamplePopulation {

    private static final Logger log = LoggerFactory.getLogger(SamplePopulation.class);

    Faker fake = new Faker();

    /**
     * Create the specified number of random sample employees.  
     */
    public void createNewEmployees(EntityManager em, int quantity) {
        for (int index = 0; index < quantity; index++) {
            em.persist(createRandomEmployee());
        }
    }

    public Employee createRandomEmployee() {
        Random r = new Random();

        Employee emp = new Employee();
        emp.setGender(Gender.values()[r.nextInt(2)]);
        emp.setFirstName(fake.name().firstName());
        emp.setLastName(fake.name().lastName());
        emp.addPhoneNumber("HOME", fake.phoneNumber().phoneNumber().toString());
        emp.addPhoneNumber("WORK", fake.phoneNumber().phoneNumber().toString());
        emp.addPhoneNumber("MOBILE", fake.phoneNumber().cellPhone().toString());

        emp.setAddress(new Address(fake.address().city(), fake.address().country(), "", fake.address().zipCode(), fake.address().streetAddress()));

        return emp;
    }
}
