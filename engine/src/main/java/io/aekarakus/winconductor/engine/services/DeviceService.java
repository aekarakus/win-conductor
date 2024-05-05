package io.aekarakus.winconductor.engine.services;

import io.aekarakus.winconductor.engine.domain.dtos.DeviceDto;
import io.aekarakus.winconductor.engine.domain.models.Device;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface DeviceService {

    DeviceDto registerDevice(Device device) throws IOException;

    void deregisterDevice(String address);
    List<DeviceDto> listRegisteredDevices();
    List<DeviceDto> getDevicesByProfile(Long profileId);
}
