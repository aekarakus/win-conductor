package io.aekarakus.winconductor.engine.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "applications")
public class Application {

    @Id
    private Long id;
    private String name;

    @Column(unique = true)
    private String chocolateyName;

    @ManyToMany
    @JoinTable(
            name = "applications_profiles",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<Profile> profileList;
    private String path;
}
