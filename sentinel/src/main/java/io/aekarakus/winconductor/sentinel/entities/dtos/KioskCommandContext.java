package io.aekarakus.winconductor.sentinel.entities.dtos;

import io.aekarakus.winconductor.sentinel.entities.Application;
import io.aekarakus.winconductor.sentinel.entities.Command;
import io.aekarakus.winconductor.sentinel.entities.Device;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KioskCommandContext extends CommandContext{

    private Application application;

}
