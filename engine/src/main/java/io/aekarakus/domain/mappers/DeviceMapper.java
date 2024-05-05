package io.aekarakus.domain.mappers;

import io.aekarakus.domain.ProfileInfo;
import io.aekarakus.domain.dtos.DeviceDto;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import org.mapstruct.*;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DeviceMapper {

    @Mapping(target = "profileInfo", source = "profile")
    DeviceDto  deviceToDeviceDto(Device device);
    ProfileInfo profileToProfileInfo(Profile profile);
}