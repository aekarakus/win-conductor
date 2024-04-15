package io.aekarakus.domain.mappers;

import io.aekarakus.domain.DeviceInfo;
import io.aekarakus.domain.ProfileInfo;
import io.aekarakus.domain.dtos.ProfileDto;
import io.aekarakus.domain.models.Device;
import io.aekarakus.domain.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public abstract class ProfileMapper {

    DeviceMapperImpl deviceMapper = new DeviceMapperImpl();

    @Mapping(source = "deviceList", target = "deviceInfos", qualifiedByName = "deviceInfoListProducer")
    public abstract ProfileDto profileToProfileDto(Profile profile);

    public abstract ProfileInfo profileToProfileInfo(Profile profile);

    @Named("deviceInfoListProducer")
    public List<DeviceInfo> generateDeviceInfoList(List<Device> deviceList) {

        return deviceList.stream().map(deviceMapper::deviceToDeviceInfo).toList();
    };
}
