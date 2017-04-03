package com.example.farsite.model;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        private int id;
        private double salery;
        private String jobDescr;
}
