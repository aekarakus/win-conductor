package io.aekarakus.domain.repositories;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}