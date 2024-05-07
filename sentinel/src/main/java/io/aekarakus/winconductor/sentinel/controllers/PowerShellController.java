package io.aekarakus.winconductor.sentinel.controllers;

import io.aekarakus.winconductor.sentinel.entities.Device;
import io.aekarakus.winconductor.sentinel.entities.dtos.CommandContext;
import io.aekarakus.winconductor.sentinel.services.PowerShellService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class PowerShellController{

    private final PowerShellService powerShellService;

    @PostMapping("/command")
    String sendCommand(@RequestBody CommandContext context){
        System.out.println(context.toString());
        System.out.println("klalsda");
        powerShellService.sendCommandToMachine(context);
        return "Ok";
    }
}
