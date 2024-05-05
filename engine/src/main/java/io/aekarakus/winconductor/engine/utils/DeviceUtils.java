package io.aekarakus.winconductor.engine.utils;

import io.aekarakus.winconductor.engine.domain.models.Device;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

@Component
public class DeviceUtils {

    public boolean isDeviceReachable(Device device) throws IOException {

        InetAddress address = InetAddress.getByName(device.getAddress());

        return address.isReachable(3_000);
    }
}
