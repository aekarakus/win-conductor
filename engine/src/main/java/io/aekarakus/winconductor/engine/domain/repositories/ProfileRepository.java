package io.aekarakus.winconductor.engine.domain.repositories;

import io.aekarakus.winconductor.engine.domain.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = """
    select p from Profile p
    join fetch p.deviceList
    """)
    Optional<Profile> findById(Long id);
}