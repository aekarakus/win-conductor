package io.aekarakus.domain.mappers;

import io.aekarakus.domain.DeviceInfo;
import io.aekarakus.domain.ProfileInfo;
import io.aekarakus.domain.dtos.ProfileDto;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProfileMapper {

     @Mapping(target = "deviceInfos", source = "deviceList")
     ProfileDto profileToProfileDto(Profile profile);
     DeviceInfo deviceToDeviceInfo(Device device);
}

