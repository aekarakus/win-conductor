package io.aekarakus.winconductor.engine.domain.mappers;

import io.aekarakus.winconductor.engine.domain.ProfileInfo;
import io.aekarakus.winconductor.engine.domain.dtos.DeviceDto;
import io.aekarakus.winconductor.engine.domain.models.Device;
import io.aekarakus.winconductor.engine.domain.models.Profile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-05T22:59:21+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Private Build)"
)
@Component
public class DeviceMapperImpl implements DeviceMapper {

    @Override
    public DeviceDto deviceToDeviceDto(Device device) {
        if ( device == null ) {
            return null;
        }

        DeviceDto deviceDto = new DeviceDto();

        deviceDto.setProfileInfo( profileToProfileInfo( device.getProfile() ) );
        deviceDto.setId( device.getId() );
        deviceDto.setName( device.getName() );
        deviceDto.setAddress( device.getAddress() );

        return deviceDto;
    }

    @Override
    public ProfileInfo profileToProfileInfo(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileInfo profileInfo = new ProfileInfo();

        profileInfo.setId( profile.getId() );
        profileInfo.setName( profile.getName() );

        return profileInfo;
    }
}
