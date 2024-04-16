package io.aekarakus.domain.dtos;

import io.aekarakus.domain.ProfileInfo;
import lombok.Data;

@Data
public class DeviceDto {
    private Long id;
    private String name;
    private String address;
    private ProfileInfo profileInfo;
}
