package com.example.farsite.model;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@Data
public class Person {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        private String id;
        private String firstName;
        private String lastName;

        @ManyToOne
        private Family family;

        @Transient
        private String nonsenseField = "";

        @OneToMany
        private List<Job> jobList = new ArrayList<Job>();
}
