package io.aekarakus.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "applications_profiles",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<Profile> profileList;
}
