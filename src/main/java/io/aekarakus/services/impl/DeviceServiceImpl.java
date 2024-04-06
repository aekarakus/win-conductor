package io.aekarakus.services.impl;

import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.repositories.DeviceRepository;
import io.aekarakus.exceptions.DeviceNotFoundException;
import io.aekarakus.exceptions.DeviceNotReachableException;
import io.aekarakus.services.DeviceService;
import io.aekarakus.utils.DeviceUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceUtils deviceUtils;

    @Override
    public Device registerDevice(Device device) throws IOException {
        if(!deviceUtils.isDeviceReachable(device)){
            throw new DeviceNotReachableException();
        }
        return deviceRepository.save(device);
    }

    @Override
    public void deregisterDevice(String address) {
        Device device = deviceRepository.findDeviceByAddress(address).orElseThrow(DeviceNotFoundException::new);
        deviceRepository.delete(device);
    }

    @Override
    public List<Device> listRegisteredDevices() {
        return deviceRepository.findAll();
    }
}
