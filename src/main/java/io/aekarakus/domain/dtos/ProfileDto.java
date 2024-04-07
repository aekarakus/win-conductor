package io.aekarakus.domain.dtos;

import io.aekarakus.domain.DeviceInfo;
import io.aekarakus.domain.models.Device;

import java.util.List;

public class ProfileDto {
    private Long id;
    private String name;
    private List<DeviceInfo> deviceInfos;
}
