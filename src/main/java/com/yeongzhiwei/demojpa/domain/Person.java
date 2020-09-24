package com.yeongzhiwei.demojpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
@Setter
public class Person extends AbstractEntity {
    
    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn
    private Person spouse;

    public String toString() {
        return "Person(id=" + getId() 
            + ", name=" + getName() 
            + ", spouse_id=" + (getSpouse() == null ? "null" : getSpouse().getId())
            + ")";
    }

}
