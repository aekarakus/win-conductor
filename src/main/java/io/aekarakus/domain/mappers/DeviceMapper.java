package io.aekarakus.domain.mappers;

import io.aekarakus.domain.DeviceInfo;
import io.aekarakus.domain.ProfileInfo;
import io.aekarakus.domain.dtos.DeviceDto;
import io.aekarakus.domain.dtos.ProfileDto;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DeviceMapper {

    @Mapping(target = "profileInfo", source = "profile")
    DeviceDto  deviceToDeviceDto(Device device);
    ProfileInfo profileToProfileInfo(Profile profile);
}
