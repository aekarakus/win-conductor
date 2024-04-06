package io.aekarakus.controllers;

import io.aekarakus.exceptions.DeviceNotReachableException;
import io.aekarakus.domain.models.Device;
import io.aekarakus.services.DeviceService;
import io.aekarakus.utils.DeviceUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/devices")
@RestController
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/register")
    ResponseEntity<Device> registerDevice(@RequestBody Device device) throws IOException {
        Device savedDevice = deviceService.registerDevice(device);
        return ResponseEntity.ok().body(savedDevice);
    }

    @PostMapping("/deregister")
    void deregisterDevice(@RequestBody String address){
        deviceService.deregisterDevice(address);
    }

    @GetMapping("/list")
    ResponseEntity<List<Device>> listDevices(){
        List<Device> devices = deviceService.listRegisteredDevices();
        return ResponseEntity.ok().body(devices);
    }
}
