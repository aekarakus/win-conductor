package io.aekarakus.domain.mappers;

import io.aekarakus.domain.DeviceInfo;
import io.aekarakus.domain.dtos.DeviceDto;
import io.aekarakus.domain.dtos.ProfileDto;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import org.mapstruct.Mapper;

@Mapper
public interface DeviceMapper {
    DeviceDto deviceToDeviceDto(Device device);
    DeviceInfo deviceToDeviceInfo(Device device);
}
