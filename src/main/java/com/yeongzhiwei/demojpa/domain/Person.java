package com.yeongzhiwei.demojpa.domain;

import static java.util.stream.Collectors.joining;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"emails"})
public class Person extends AbstractEntity {
    
    @Column(nullable = false)
    private String name;

    @OneToOne
    private Person spouse;

    @OneToMany(mappedBy = "owner")
    private Set<Email> emails; 

    public String toString() {
        return "Person(id=" + getId() 
            + ", name=" + getName() 
            + ", spouse_id=" + (getSpouse() == null ? "null" : getSpouse().getId())
            + ", emails=" + (getEmails() == null ? "null" : ("[" + getEmails().stream().map(Email::getId).map(String::valueOf).collect(joining(", ")) + "]"))
            + ")";
    }

}
