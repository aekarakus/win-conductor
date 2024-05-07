package io.aekarakus.winconductor.sentinel.services;

import io.aekarakus.winconductor.sentinel.entities.Application;
import io.aekarakus.winconductor.sentinel.entities.Command;
import io.aekarakus.winconductor.sentinel.entities.Device;
import io.aekarakus.winconductor.sentinel.entities.dtos.CommandContext;
import io.aekarakus.winconductor.sentinel.entities.dtos.KioskCommandContext;
import io.aekarakus.winconductor.sentinel.entities.xmblocks.packageConfig.PackageConfigType;
import io.cloudsoft.winrm4j.client.WinRmClientContext;
import io.cloudsoft.winrm4j.winrm.WinRmTool;
import io.cloudsoft.winrm4j.winrm.WinRmToolResponse;
import org.apache.http.client.config.AuthSchemes;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PowerShellServiceImpl implements PowerShellService {
    @Override
    public void sendCommandToMachine(CommandContext commandContext) {

        WinRmClientContext context = WinRmClientContext.newInstance();

        Device device = commandContext.getDevice();
        Command command = commandContext.getCommand();

        WinRmTool tool = WinRmTool.Builder.builder(device.getAddress(), device.getUserName(), device.getPassword())
                .authenticationScheme(AuthSchemes.NTLM)
                .port(5985)
                .context(context)
                .build();

        WinRmToolResponse response = tool.executePs(command.getContent());

        context.shutdown();
    }

    @Override
    public void enfoceKioskMode(KioskCommandContext commandContext) {
        WinRmClientContext context = WinRmClientContext.newInstance();

        Device device = commandContext.getDevice();
        Command command = commandContext.getCommand();
        Application application = commandContext.getApplication();

        WinRmTool tool = WinRmTool.Builder.builder(device.getAddress(), device.getUserName(), device.getPassword())
                .authenticationScheme(AuthSchemes.NTLM)
                .port(5985)
                .context(context)
                .build();

        PackageConfigType packageConfigType = new PackageConfigType();
        packageConfigType.setID("{" + UUID.randomUUID() + "}");

        WinRmToolResponse response = tool.executePs(command.getContent());

        System.out.println("Out:" + response.getStdOut());
        System.out.println("status:" + response.getStatusCode());
        System.out.println("Err:" + response.getStdErr());

        context.shutdown();
    }
}
