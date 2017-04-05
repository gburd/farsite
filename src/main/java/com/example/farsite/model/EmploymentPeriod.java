package com.example.farsite.model;

import lombok.Data;

import static javax.persistence.TemporalType.DATE;

import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 * Represents the period of time an employee has worked for the company. A null
 * endDate indicates that the employee is current.
 */
@Data
@Embeddable
public class EmploymentPeriod {

    @Temporal(DATE)
    private Calendar startDate;
    @Temporal(DATE)
    private Calendar endDate;

    public void setStartDate(int year, int month, int date) {
        if (this.startDate == null) {
            setStartDate(Calendar.getInstance());
        }
        getStartDate().set(year, month, date);
    }

    public void setEndDate(int year, int month, int date) {
        if (this.endDate == null) {
            setEndDate(Calendar.getInstance());
        }
        getEndDate().set(year, month, date);
    }
}
