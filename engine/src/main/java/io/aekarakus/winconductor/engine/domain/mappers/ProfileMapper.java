package io.aekarakus.winconductor.engine.domain.mappers;

import io.aekarakus.winconductor.engine.domain.DeviceInfo;
import io.aekarakus.winconductor.engine.domain.dtos.ProfileDto;
import io.aekarakus.winconductor.engine.domain.models.Device;
import io.aekarakus.winconductor.engine.domain.models.Profile;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProfileMapper {

     @Mapping(target = "deviceInfos", source = "deviceList")
     ProfileDto profileToProfileDto(Profile profile);
     DeviceInfo deviceToDeviceInfo(Device device);
}

