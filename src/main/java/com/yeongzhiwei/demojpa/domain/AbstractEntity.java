package com.yeongzhiwei.demojpa.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@MappedSuperclass
@EqualsAndHashCode
@Getter
public abstract class AbstractEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private Date created;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.updated = this.created;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
    }

}
