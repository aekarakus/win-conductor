package io.aekarakus.winconductor.sentinel.services;

import io.aekarakus.winconductor.sentinel.entities.Device;
import io.aekarakus.winconductor.sentinel.entities.dtos.CommandContext;
import io.aekarakus.winconductor.sentinel.entities.dtos.KioskCommandContext;

public interface PowerShellService {

    void sendCommandToMachine(CommandContext commandContext);
    void enfoceKioskMode(KioskCommandContext commandContext);


}
