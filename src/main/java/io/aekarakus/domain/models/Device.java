package io.aekarakus.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Profile profile;

    @OneToMany(mappedBy = "device")
    private List<User> userList;
}
