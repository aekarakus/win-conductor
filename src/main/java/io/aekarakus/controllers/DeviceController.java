package io.aekarakus.controllers;

import io.aekarakus.domain.dtos.DeviceDto;
import io.aekarakus.domain.models.Device;
import io.aekarakus.services.DeviceService;
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
    ResponseEntity<DeviceDto> registerDevice(@RequestBody Device device) throws IOException {
        DeviceDto savedDevice = deviceService.registerDevice(device);
        return ResponseEntity.ok().body(savedDevice);
    }

    @DeleteMapping("/deregister")
    void deregisterDevice(@RequestBody String address){
        deviceService.deregisterDevice(address);
    }

    @GetMapping("/list")
    ResponseEntity<List<DeviceDto>> listDevices(){
        List<DeviceDto> devices = deviceService.listRegisteredDevices();
        return ResponseEntity.ok().body(devices);
    }

    @GetMapping("/list/profile")
    ResponseEntity<List<DeviceDto>> listDevicesByProfile(@RequestParam Long profileId){
        List<DeviceDto> devices = deviceService.getDevicesByProfile(profileId);
        return ResponseEntity.ok().body(devices);
    }
}
