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
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class DeviceMapper {

    @Autowired
    ProfileMapper profileMapper;

    @Mapping(source = "profile", target = "profileInfo", qualifiedByName = "profileInfoProducer")
    public abstract DeviceDto  deviceToDeviceDto(Device device);

    public abstract DeviceInfo deviceToDeviceInfo(Device device);

    @Named("profileInfoProducer")
    public ProfileInfo generateProfileInfo(Profile profile){
        return profileMapper.profileToProfileInfo(profile);
    }
}
