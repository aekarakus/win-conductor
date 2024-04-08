package io.aekarakus.domain.repositories;

import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findDeviceByAddress(String address);

    @Query(value = """
    select d from Device d
    join fetch d.profile
    """)
    List<Device> findDevicesByProfileId(Long id);
}