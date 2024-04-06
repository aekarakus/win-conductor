package io.aekarakus.domain.repositories;

import io.aekarakus.domain.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findDeviceByAddress(String address);
}