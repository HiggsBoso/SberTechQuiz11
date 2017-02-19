package com.sbt.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Someone on 18.02.2017.
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }
}
