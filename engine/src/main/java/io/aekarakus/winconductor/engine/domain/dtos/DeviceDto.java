package io.aekarakus.winconductor.engine.domain.dtos;

import io.aekarakus.winconductor.engine.domain.ProfileInfo;
import lombok.Data;

@Data
public class DeviceDto {
    private Long id;
    private String name;
    private String address;
    private ProfileInfo profileInfo;
}
