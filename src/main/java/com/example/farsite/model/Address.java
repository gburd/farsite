package com.example.farsite.model;

import javax.persistence.*;

import com.example.farsite.util.SerialVersionUID;
import lombok.Data;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.ReturnInsert;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Cache(
        refreshOnlyIfNewer = true
)
public class Address extends AbstractModel<Long> {
    private static final long serialVersionUID = SerialVersionUID.compute(Address.class);

    // PRIMARY KEY
//    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) //@ReturnInsert(returnOnly=true)
//    @Column(unique=true, nullable=false)
    @Id @GeneratedValue(generator = "flake-seq")
    @Column(unique = true, nullable = false)
    private Long id;

    // FIELDS
    private String city;
    private String country;
    @Basic(fetch=LAZY)
    private String province;
    private String postalCode;
    private String street;

    // OPTIMISTIC CONCURRENCY CONTROL
    @Version
    private long version;

    public Address() {
    }

    public Address(String city, String country, String province, String postalCode, String street) {
        this.city = city;
        this.country = country;
        this.province = province;
        this.postalCode = postalCode;
        this.street = street;
    }
}
