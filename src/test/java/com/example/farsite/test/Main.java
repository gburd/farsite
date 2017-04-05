package com.example.farsite.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.example.farsite.model.Employee;
import com.example.farsite.model.Gender;
import com.example.farsite.util.SamplePopulation;

/**
 * Examples illustrating the use of JPA with the employee domain
 * com.example.farsite.jpa.model.
 *
 * @see ExampleTest
 */
public class Main {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = PersistenceTesting.createEMF(true);
        Main example = new Main();

        try {
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            new SamplePopulation().createNewEmployees(em, 1000);
            em.getTransaction().commit();
            em.clear();

            // Add employee with 555 area code to satisfy a test query
            em.getTransaction().begin();
            Employee e = new Employee();
            e.setFirstName("John");
            e.setLastName("Doe");
            e.setGender(Gender.Male);
            e.addPhoneNumber("HOME", "555-5552222");
            em.persist(e);
            Long id = e.getId();

            em.getTransaction().commit();
            em.clear();

            example.queryAllEmployees(em);
            em.clear();

            example.queryEmployeeLikeAreaCode55(em);
            em.clear();

            example.modifyEmployee(em, id);
            em.clear();

            example.deleteEmployee(em, id);
            em.clear();

            em.close();

        } finally {
            emf.close();
        }
    }

    public void queryAllEmployees(EntityManager em) {
        List<Employee> results = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        System.out.println("Query All Results: " + results.size());

        results.forEach(e -> System.out.println("\t>" + e));
    }

    public void queryEmployeeLikeAreaCode55(EntityManager em) {
        System.out.println("\n\n --- Query Employee.phoneNumbers.areaCode LIKE '55%' ---");

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e JOIN e.phoneNumbers phones WHERE phones.number LIKE '55%'", Employee.class);
        List<Employee> emps = query.getResultList();

        emps.forEach(e -> System.out.println("> " + e));
    }

    public void modifyEmployee(EntityManager em, Long id) {
        System.out.println("\n\n --- Modify Employee ---");
        em.getTransaction().begin();

        Employee emp = em.find(Employee.class, id);
        emp.setSalary(1);

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.id = :ID AND e.firstName = :FNAME", Employee.class);
        query.setParameter("ID", id);
        query.setParameter("FNAME", emp.getFirstName());
        emp = query.getSingleResult();

        em.getTransaction().commit();

    }

    public void deleteEmployee(EntityManager em, Long id) {
        em.getTransaction().begin();

        em.remove(em.find(Employee.class, id));
        em.flush();

        //em.getTransaction().rollback();
        em.getTransaction().commit();
    }

}
