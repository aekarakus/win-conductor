package io.aekarakus.winconductor.engine.domain.repositories;

import io.aekarakus.winconductor.engine.domain.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    void deleteByChocolateyName(String chocolateyName);
}
