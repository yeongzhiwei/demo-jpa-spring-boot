package com.yeongzhiwei.demojpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"owner"})
public class Email extends AbstractEntity {
    
    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;

}
