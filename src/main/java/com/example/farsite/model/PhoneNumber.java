package com.example.farsite.model;

import com.example.farsite.util.SerialVersionUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data @ToString(exclude="owner") @EqualsAndHashCode(exclude="owner")
@Entity
@Table(name = "PHONE")
@IdClass(PhoneNumber.ID.class)
public class PhoneNumber extends AbstractModel<Long> {
    private static final long serialVersionUID = SerialVersionUID.compute(PhoneNumber.class);

    @Id @Column(name = "EMP_ID", updatable = false, insertable = false)
    private Long id;
    @Id @Column(updatable = false)
    private String type;

    private String number;

    @ManyToOne @JoinColumn(name = "EMP_ID")
    private Employee owner;

    public PhoneNumber() {
    }

    public PhoneNumber(String type, String number) {
        this();
        setType(type);
        setNumber(number);
    }

    protected void setOwner(Employee employee) {
        this.owner = employee;
        if (employee != null) {
            this.id = employee.getId();
        }
    }

    @Data
    public static class ID implements Serializable {
        private static final long serialVersionUID = SerialVersionUID.compute(PhoneNumber.ID.class);

        public Long id;
        public String type;
    }

}
