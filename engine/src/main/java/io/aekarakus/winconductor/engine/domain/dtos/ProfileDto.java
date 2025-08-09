package io.aekarakus.winconductor.engine.domain.dtos;

import io.aekarakus.winconductor.engine.domain.DeviceInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private Long id;
    private String name;
    private List<DeviceInfo> deviceInfos;
}
