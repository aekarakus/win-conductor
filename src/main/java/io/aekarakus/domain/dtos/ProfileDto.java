package io.aekarakus.domain.dtos;

import io.aekarakus.domain.DeviceInfo;
import io.aekarakus.domain.models.Device;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private Long id;
    private String name;
    private List<DeviceInfo> deviceInfos;
}
