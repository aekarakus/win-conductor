package io.aekarakus.winconductor.sentinel.services;

import io.aekarakus.winconductor.sentinel.entities.Application;
import io.aekarakus.winconductor.sentinel.entities.Command;
import io.aekarakus.winconductor.sentinel.entities.Device;
import io.aekarakus.winconductor.sentinel.entities.KioskConfig;
import io.aekarakus.winconductor.sentinel.entities.dtos.CommandContext;
import io.aekarakus.winconductor.sentinel.entities.dtos.KioskCommandContext;
import io.aekarakus.winconductor.sentinel.entities.xmblocks.packageConfig.PackageConfigType;
import io.aekarakus.winconductor.sentinel.entities.xmblocks.windowsCustomizations.*;
import io.cloudsoft.winrm4j.client.WinRmClientContext;
import io.cloudsoft.winrm4j.client.shell.Shell;
import io.cloudsoft.winrm4j.winrm.WinRmTool;
import io.cloudsoft.winrm4j.winrm.WinRmToolResponse;
import javax.xml.bind.JAXBElement;
import org.apache.http.client.config.AuthSchemes;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
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
    public void enforceKioskMode(KioskCommandContext commandContext) throws JAXBException {
        WinRmClientContext context = WinRmClientContext.newInstance();

        Device device = commandContext.getDevice();
        Command command = commandContext.getCommand();
        Application application = commandContext.getApplication();

        WinRmTool tool = WinRmTool.Builder.builder(device.getAddress(), device.getUserName(), device.getPassword())
                .authenticationScheme(AuthSchemes.NTLM)
                .port(5985)
                .context(context)
                .build();


        ObjectFactory objectFactory = new ObjectFactory();
        Random random = new Random();

        PackageConfigType packageConfigType = new PackageConfigType();
        packageConfigType.setID("{" + UUID.randomUUID() + "}");
        packageConfigType.setName("Project_" + random.nextInt(Integer.MAX_VALUE));
        packageConfigType.setVersion("1.1");
        packageConfigType.setOwnerType("OEM");
        packageConfigType.setRank("0");
        packageConfigType.setNotes("");

        UserType userType = objectFactory.createUserType();
        userType.setPassword("kiosk");
        userType.setUserGroup("Standard Users");
        userType.setUserName("kiosk");

        UsersType usersType = objectFactory.createUsersType();
        usersType.setUser(userType);

        AccountsType accountsType = objectFactory.createAccountsType();
        accountsType.setUsers(usersType);


        DesktopType desktopType = objectFactory.createDesktopType();
        desktopType.setHideOobe("True");

        OOBEType oobeType = objectFactory.createOOBEType();
        oobeType.setDesktop(desktopType);

        DevDetailType devDetailType = objectFactory.createDevDetailType();
        devDetailType.setDnsComputerName("kiosk-1");

        ApplicationManagementType applicationManagementType = objectFactory.createApplicationManagementType();
        applicationManagementType.setAllowAllTrustedApps("Yes");

        PoliciesType policiesType = objectFactory.createPoliciesType();
        policiesType.setApplicationManagement(applicationManagementType);

        CommandConfigType commandConfigType1 = objectFactory.createCommandConfigType();
        commandConfigType1.setName("chrome");
        List<JAXBElement<String>> content = commandConfigType1.getContent();
        content.add(objectFactory.createCommandConfigTypeCommandFile("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"));
        content.add(objectFactory.createCommandConfigTypeCommandLine("cmd /c \"chrome.exe\""));
        content.add(objectFactory.createCommandConfigTypeContinueInstall("True"));
        content.add(objectFactory.createCommandConfigTypeRestartRequired("False"));
        content.add(objectFactory.createCommandConfigTypeReturnCodeRestart("1010"));
        content.add(objectFactory.createCommandConfigTypeReturnCodeSuccess("0"));

        CommandConfigType commandConfigType2 = objectFactory.createCommandConfigType();
        commandConfigType2.setName("InstallShellLauncher");
        content = commandConfigType2.getContent();
        content.add(objectFactory.createCommandConfigTypeCommandLine("dism /online /enable-feature /FeatureName:Client-EmbeddedShellLauncher /all"));

        CommandType commandType = objectFactory.createCommandType();
        commandType.setCommandConfig(List.of(commandConfigType1, commandConfigType2));


        PrimaryContextType primaryContextType = objectFactory.createPrimaryContextType();
        primaryContextType.setCommand(commandType);

        ProvisioningCommandsType provisioningCommandsType = objectFactory.createProvisioningCommandsType();
        provisioningCommandsType.setPrimaryContext(primaryContextType);

        AutoLogonType autoLogonType = objectFactory.createAutoLogonType();
        autoLogonType.setEnable("ENABLE");
        autoLogonType.setUserName("kiosk");
        autoLogonType.setPassword("kiosk");

        LocalUserShellSettingType localUserShellSettingType1 = objectFactory.createLocalUserShellSettingType();
        localUserShellSettingType1.setUsername("LocalUserShellSetting");
        localUserShellSettingType1.setUserCustomShell("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        LocalUserShellSettingType localUserShellSettingType2 = objectFactory.createLocalUserShellSettingType();
        localUserShellSettingType2.setUsername("kiosk");
        localUserShellSettingType2.setUserCustomShell("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        LocalUserSpecificSettingsType localUserSpecificSettingsType = objectFactory.createLocalUserSpecificSettingsType();
        localUserSpecificSettingsType.setLocalUserShellSetting(List.of(localUserShellSettingType1, localUserShellSettingType2));

        UserSpecificSettingsType userSpecificSettingsType = objectFactory.createUserSpecificSettingsType();
        userSpecificSettingsType.setLocalUserSpecificSettings(localUserSpecificSettingsType);

        ShellLauncherType shellLauncherType = objectFactory.createShellLauncherType();
        shellLauncherType.setEnable("ENABLE");
        shellLauncherType.setUserSpecificSettings(userSpecificSettingsType);

        SMISettingsType smiSettingsType = objectFactory.createSMISettingsType();
        smiSettingsType.setAutoLogon(autoLogonType);
        smiSettingsType.setHideAutologonUI("False");
        smiSettingsType.setShellLauncher(shellLauncherType);

        CommonType commonType = objectFactory.createCommonType();
        commonType.setProvisioningCommands(provisioningCommandsType);
        commonType.setAccounts(accountsType);
        commonType.setDevDetail(devDetailType);
        commonType.setOobe(oobeType);
        commonType.setPolicies(policiesType);
        commonType.setSmiSettings(smiSettingsType);

        CustomizationsType customizationsType = objectFactory.createCustomizationsType();
        customizationsType.setCommon(commonType);

        SettingsType settingsType = objectFactory.createSettingsType();
        settingsType.setCustomizations(customizationsType);

        KioskConfig kioskConfig = new KioskConfig();
        kioskConfig.setPackageConfig(packageConfigType);
        kioskConfig.setSettings(settingsType);

        JAXBContext jaxbContext = JAXBContext.newInstance(KioskConfig.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(kioskConfig, new File("./test.xml"));

        WinRmToolResponse response = tool.executePs(command.getContent());

        System.out.println("Out:" + response.getStdOut());
        System.out.println("status:" + response.getStatusCode());
        System.out.println("Err:" + response.getStdErr());

        context.shutdown();
    }
}
