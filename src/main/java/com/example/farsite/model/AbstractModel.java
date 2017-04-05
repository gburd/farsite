package com.example.farsite.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.farsite.util.SerialVersionUID;
//import org.eclipse.persistence.descriptors.changetracking.ChangeTracker;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode
@MappedSuperclass
//EntityListeners(AuditingEntityListener.class)
public abstract class AbstractModel <T extends Serializable> implements Model<T> { //, ChangeTracker {
    private static final long serialVersionUID = SerialVersionUID.compute(AbstractModel.class);

    //@Getter @CreatedDate Date createdDate;
    //@Getter @LastModifiedDate Date modifiedDate;
}
