package io.aekarakus.winconductor.engine.controllers;

import io.aekarakus.winconductor.engine.domain.dtos.DeviceDto;
import io.aekarakus.winconductor.engine.domain.models.Device;
import io.aekarakus.winconductor.engine.services.KioskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RequestMapping("/api/kiosk")
@RestController
public class KioskController {

    private final KioskService kioskService;

    @GetMapping("/enforce/{id}")
    ResponseEntity<String> enforceKioskModeOnDevice(@PathVariable Long id) throws IOException {
        kioskService.enforceKioskModeOnDevice(id);
        return ResponseEntity.ok().body("success!");
    }
}
