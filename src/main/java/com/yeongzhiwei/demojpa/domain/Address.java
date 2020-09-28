package com.yeongzhiwei.demojpa.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"occupiers"})
public class Address extends AbstractEntity {

    private Integer postalCode;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "address_person",
        joinColumns = { @JoinColumn(name = "address_id")},
        inverseJoinColumns = { @JoinColumn(name = "person_id")}
    )
    private Set<Person> occupiers;

    public void addOccupier(Person person) {
        if (occupiers == null) {
            occupiers = new HashSet<>();
        }

        occupiers.add(person);
    }

}
