package io.aekarakus.domain.repositories;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
