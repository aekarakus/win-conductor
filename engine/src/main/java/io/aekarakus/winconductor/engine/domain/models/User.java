package io.aekarakus.winconductor.engine.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String userName;

    @ManyToOne
    private Device device;
}
