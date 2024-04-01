package io.aekarakus.services;

import io.aekarakus.domain.models.Device;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

    void registerDevice(Device device);
    void deregisterDevice(Device device);
    List<Device> listRegisteredDevices();
}
