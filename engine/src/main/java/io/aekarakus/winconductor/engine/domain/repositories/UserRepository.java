package io.aekarakus.winconductor.engine.domain.repositories;

import io.aekarakus.winconductor.engine.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
