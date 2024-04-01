package io.aekarakus.domain.repositories;

import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Long, Device> {
}