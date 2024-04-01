package io.aekarakus.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "profile")
    private List<Device> deviceList;

    @ManyToMany(mappedBy = "profileList")
    private List<Application> applicationList;

}
