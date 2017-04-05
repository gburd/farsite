package com.example.farsite.model;

import com.example.farsite.util.SerialVersionUID;
import lombok.Data;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.annotations.ChangeTracking;
import org.eclipse.persistence.annotations.PrivateOwned;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.eclipse.persistence.annotations.CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS;
import static org.eclipse.persistence.annotations.ChangeTrackingType.OBJECT;
import static org.eclipse.persistence.config.CacheIsolationType.SHARED;

@Data
@Entity
@SecondaryTable(name = "SALARY")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e ORDER BY e.id"),
        @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.firstName LIKE :firstName AND e.lastName LIKE :lastName"),
        @NamedQuery(name = "Employee.count", query = "SELECT COUNT(e) FROM Employee e"),
        @NamedQuery(name = "Employee.countByName", query = "SELECT COUNT(e) FROM Employee e WHERE e.firstName LIKE :firstName AND e.lastName LIKE :lastName"),
        // Query used in {@link IdInPaging}
        @NamedQuery(name = "Employee.idsIn", query = "SELECT e FROM Employee e WHERE e.id IN :IDS ORDER BY e.id",
                hints = { @QueryHint(name = QueryHints.QUERY_RESULTS_CACHE, value = HintValues.TRUE) }) })
@Cache (type= CacheType.WEAK
       ,isolation=SHARED
       ,expiry=600000
       ,alwaysRefresh=true
       ,disableHits=true
       ,coordinationType=INVALIDATE_CHANGED_OBJECTS
)
//@ChangeTracking(OBJECT)
public class Employee extends AbstractModel<Long> {
    private static final long serialVersionUID = SerialVersionUID.compute(AbstractModel.class);

    // PRIMARY KEY
    @Id @GeneratedValue(generator = "flake-seq")
    @Column(unique = true, nullable = false)
    private Long id;

    // FIELDS
    private String firstName;
    private String lastName;

    /* NOTE:
     * Gender mapped using Basic with an ObjectTypeConverter to map between
     * single char code value in database to enum. JPA only supports mapping to
     * the full name of the enum or its ordinal value. */
    @Basic @Column(name = "GENDER") @Convert(converter = GenderConverter.class)
    private Gender gender = Gender.Male;

    @Column(table = "SALARY")
    private double salary;

    // OPTIMISTIC CONCURRENCY CONTROL
    @Version
    private Long version;

    // RELATIONS
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> managedEmployees = new ArrayList<Employee>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrivateOwned
    private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "startDate", column = @Column(name = "START_DATE")),
            @AttributeOverride(name = "endDate", column = @Column(name = "END_DATE")) })
    private EmploymentPeriod period;

    @ElementCollection
    @CollectionTable(name = "RESPONS")
    private List<String> responsibilities = new ArrayList<String>();

    public Employee addManagedEmployee(Employee employee) {
        getManagedEmployees().add(employee);
        employee.setManager(this);
        return employee;
    }

    public Employee removeManagedEmployee(Employee employee) {
        getManagedEmployees().remove(employee);
        employee.setManager(null);
        return employee;
    }

    public PhoneNumber addPhoneNumber(PhoneNumber phoneNumber) {
        getPhoneNumbers().add(phoneNumber);
        phoneNumber.setOwner(this);
        return phoneNumber;
    }

    public PhoneNumber addPhoneNumber(String type, String number) {
        PhoneNumber phoneNumber = new PhoneNumber(type, number);
        return addPhoneNumber(phoneNumber);
    }

    public PhoneNumber removePhoneNumber(PhoneNumber phoneNumber) {
        getPhoneNumbers().remove(phoneNumber);
        phoneNumber.setOwner(null);
        return phoneNumber;
    }

    public void addResponsibility(String responsibility) {
        getResponsibilities().add(responsibility);
    }

    public void removeResponsibility(String responsibility) {
        getResponsibilities().remove(responsibility);
    }

}
