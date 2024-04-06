package io.aekarakus;

import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.repositories.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class Bootstrapper implements CommandLineRunner {

    private final DeviceRepository deviceRepository;
    @Override
    public void run(String... args) throws Exception {

        Device windowsDeviceA = Device.builder()
                .id(1L)
                .name("Windows Device - A")
                .address("10.0.0.10")
                .build();

        Device windowsDeviceB = Device.builder()
                .id(2L)
                .name("Windows Device - B")
                .address("10.0.0.11")
                .build();

        deviceRepository.saveAll(List.of(windowsDeviceA, windowsDeviceB));
    }
}
