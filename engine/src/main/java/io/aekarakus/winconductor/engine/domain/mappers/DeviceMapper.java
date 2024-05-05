package io.aekarakus.winconductor.engine.domain.mappers;

import io.aekarakus.winconductor.engine.domain.ProfileInfo;
import io.aekarakus.winconductor.engine.domain.dtos.DeviceDto;
import io.aekarakus.winconductor.engine.domain.models.Device;
import io.aekarakus.winconductor.engine.domain.models.Profile;
import org.mapstruct.*;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DeviceMapper {

    @Mapping(target = "profileInfo", source = "profile")
    DeviceDto  deviceToDeviceDto(Device device);
    ProfileInfo profileToProfileInfo(Profile profile);
}