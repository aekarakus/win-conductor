package io.aekarakus.winconductor.engine.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
