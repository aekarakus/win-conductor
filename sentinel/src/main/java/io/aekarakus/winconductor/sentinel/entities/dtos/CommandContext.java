package io.aekarakus.winconductor.sentinel.entities.dtos;

import io.aekarakus.winconductor.sentinel.entities.Command;
import io.aekarakus.winconductor.sentinel.entities.Device;
import lombok.Data;

@Data
public class CommandContext {

    private Device device;
    private Command command;

}
