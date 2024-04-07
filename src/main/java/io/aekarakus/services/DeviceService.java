package io.aekarakus.services;

import io.aekarakus.domain.dtos.DeviceDto;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface DeviceService {

    Device registerDevice(Device device) throws IOException;

    void deregisterDevice(String address);
    List<DeviceDto> listRegisteredDevices();
    List<Device> getDevicesThatHaveProfile(Long profileId);
}
