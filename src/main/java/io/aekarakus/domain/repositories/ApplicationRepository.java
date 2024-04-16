package io.aekarakus.domain.repositories;

import io.aekarakus.domain.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    void deleteByChocolateyName(String chocolateyName);
}
