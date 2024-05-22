package io.aekarakus.winconductor.sentinel.entities;

import lombok.Data;

@Data
public class Device {
    private String deviceId;
    private String deviceName;
    private String address;
    private String userName;
    private String password;
}
