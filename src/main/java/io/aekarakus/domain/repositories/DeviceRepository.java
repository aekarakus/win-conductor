package io.aekarakus.domain.repositories;

import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findDeviceByAddress(String address);
    List<Device> findDevicesByProfileId(Long id);
}