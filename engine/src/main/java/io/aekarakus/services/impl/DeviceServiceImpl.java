package io.aekarakus.services.impl;

import io.aekarakus.domain.dtos.DeviceDto;
import io.aekarakus.domain.mappers.DeviceMapper;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.repositories.DeviceRepository;
import io.aekarakus.exceptions.DeviceNotFoundException;
import io.aekarakus.exceptions.DeviceNotReachableException;
import io.aekarakus.services.DeviceService;
import io.aekarakus.utils.DeviceUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceUtils deviceUtils;
    private final DeviceMapper deviceMapper;

    @Override
    public DeviceDto registerDevice(Device device) throws IOException {
        if(!deviceUtils.isDeviceReachable(device)){
            throw new DeviceNotReachableException();
        }
        Device savedDevice = deviceRepository.save(device);
        return deviceMapper.deviceToDeviceDto(savedDevice);
    }

    @Transactional
    @Override
    public void deregisterDevice(String address) {
        Device device = deviceRepository.findDeviceByAddress(address).orElseThrow(DeviceNotFoundException::new);
        deviceRepository.delete(device);
    }

    @Override
    public List<DeviceDto> listRegisteredDevices() {
        return deviceRepository.findAll().stream().map(deviceMapper::deviceToDeviceDto).toList();
    }

    @Override
    public List<DeviceDto> getDevicesByProfile(Long id) {

        List<Device> devicesByProfileId = deviceRepository.findDevicesByProfileId(id);
        return devicesByProfileId.stream().map(deviceMapper::deviceToDeviceDto).toList();
    }
}
