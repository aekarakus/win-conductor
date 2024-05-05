package io.aekarakus.winconductor.engine.domain.mappers;

import io.aekarakus.winconductor.engine.domain.DeviceInfo;
import io.aekarakus.winconductor.engine.domain.dtos.ProfileDto;
import io.aekarakus.winconductor.engine.domain.models.Device;
import io.aekarakus.winconductor.engine.domain.models.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-05T22:59:21+0300",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 21.0.2 (Private Build)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileDto profileToProfileDto(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDto profileDto = new ProfileDto();

        profileDto.setDeviceInfos( deviceListToDeviceInfoList( profile.getDeviceList() ) );
        profileDto.setId( profile.getId() );
        profileDto.setName( profile.getName() );

        return profileDto;
    }

    @Override
    public DeviceInfo deviceToDeviceInfo(Device device) {
        if ( device == null ) {
            return null;
        }

        DeviceInfo deviceInfo = new DeviceInfo();

        deviceInfo.setId( device.getId() );
        deviceInfo.setName( device.getName() );
        deviceInfo.setAddress( device.getAddress() );

        return deviceInfo;
    }

    protected List<DeviceInfo> deviceListToDeviceInfoList(List<Device> list) {
        if ( list == null ) {
            return null;
        }

        List<DeviceInfo> list1 = new ArrayList<DeviceInfo>( list.size() );
        for ( Device device : list ) {
            list1.add( deviceToDeviceInfo( device ) );
        }

        return list1;
    }
}
