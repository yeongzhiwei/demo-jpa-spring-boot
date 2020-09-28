package com.yeongzhiwei.demojpa.domain;

import static java.util.stream.Collectors.joining;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"spouse", "emails", "addresses"})
public class Person extends AbstractEntity {
    
    @Column(nullable = false)
    private String name;

    @OneToOne
    private Person spouse;

    @OneToMany(mappedBy = "owner")
    private Set<Email> emails; 

    @ManyToMany(mappedBy = "occupiers")
    private Set<Address> addresses;

    public String toString() {
        return "Person(id=" + getId() 
            + ", name=" + getName() 
            + ", spouse_id=" + (getSpouse() == null ? "null" : getSpouse().getId())
            + ", emails=" + (getEmails() == null ? "null" : ("[" + getEmails().stream().map(Email::getId).map(String::valueOf).collect(joining(", ")) + "]"))
            + ", addresses=" + (getAddresses() == null ? "null" : ("[" + getAddresses().stream().map(Address::getId).map(String::valueOf).collect(joining(", ")) + "]"))
            + ")";
    }

}
