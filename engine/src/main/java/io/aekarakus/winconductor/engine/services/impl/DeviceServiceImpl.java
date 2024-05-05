package io.aekarakus.winconductor.engine.services.impl;

import io.aekarakus.winconductor.engine.domain.dtos.DeviceDto;
import io.aekarakus.winconductor.engine.domain.mappers.DeviceMapper;
import io.aekarakus.winconductor.engine.domain.models.Device;
import io.aekarakus.winconductor.engine.domain.repositories.DeviceRepository;
import io.aekarakus.winconductor.engine.exceptions.DeviceNotFoundException;
import io.aekarakus.winconductor.engine.exceptions.DeviceNotReachableException;
import io.aekarakus.winconductor.engine.services.DeviceService;
import io.aekarakus.winconductor.engine.utils.DeviceUtils;
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
