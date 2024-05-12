package io.aekarakus.winconductor.sentinel.services;

import io.aekarakus.winconductor.sentinel.entities.Application;
import io.aekarakus.winconductor.sentinel.entities.Command;
import io.aekarakus.winconductor.sentinel.entities.Device;
import io.aekarakus.winconductor.sentinel.entities.KioskConfig;
import io.aekarakus.winconductor.sentinel.entities.dtos.CommandContext;
import io.aekarakus.winconductor.sentinel.entities.dtos.KioskCommandContext;
import io.aekarakus.winconductor.sentinel.entities.xmblocks.packageConfig.PackageConfigObjectFactory;
import io.aekarakus.winconductor.sentinel.entities.xmblocks.packageConfig.PackageConfigType;
import io.aekarakus.winconductor.sentinel.entities.xmblocks.windowsCustomizations.*;
import io.cloudsoft.winrm4j.client.WinRmClientContext;
import io.cloudsoft.winrm4j.winrm.WinRmTool;
import io.cloudsoft.winrm4j.winrm.WinRmToolResponse;
import javax.xml.bind.JAXBElement;
import org.apache.http.client.config.AuthSchemes;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
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


        PackageConfigObjectFactory packageConfigObjectFactory = new PackageConfigObjectFactory();
        WindowsCustomizationsObjectFactory windowsCustomizationsObjectFactory = new WindowsCustomizationsObjectFactory();
        Random random = new Random();

        PackageConfigType packageConfigType = packageConfigObjectFactory.createPackageConfigType();

        packageConfigType.setID("{" + UUID.randomUUID() + "}");
        packageConfigType.setName("Project_" + random.nextInt(Integer.MAX_VALUE));
        packageConfigType.setVersion("1.1");
        packageConfigType.setOwnerType("OEM");
        packageConfigType.setRank("0");
        packageConfigType.setNotes("");

        UserType userType = windowsCustomizationsObjectFactory.createUserType();
        userType.setPassword("kiosk");
        userType.setUserGroup("Standard Users");
        userType.setUserName("kiosk");

        UsersType usersType = windowsCustomizationsObjectFactory.createUsersType();
        usersType.setUser(userType);

        AccountsType accountsType = windowsCustomizationsObjectFactory.createAccountsType();
        accountsType.setUsers(usersType);


        DesktopType desktopType = windowsCustomizationsObjectFactory.createDesktopType();
        desktopType.setHideOobe("True");

        OOBEType oobeType = windowsCustomizationsObjectFactory.createOOBEType();
        oobeType.setDesktop(desktopType);

        DevDetailType devDetailType = windowsCustomizationsObjectFactory.createDevDetailType();
        devDetailType.setDnsComputerName("kiosk-1");

        ApplicationManagementType applicationManagementType = windowsCustomizationsObjectFactory.createApplicationManagementType();
        applicationManagementType.setAllowAllTrustedApps("Yes");

        PoliciesType policiesType = windowsCustomizationsObjectFactory.createPoliciesType();
        policiesType.setApplicationManagement(applicationManagementType);

        CommandConfigType commandConfigType1 = windowsCustomizationsObjectFactory.createCommandConfigType();
        commandConfigType1.setName("chrome");
        List<JAXBElement<String>> content = commandConfigType1.getContent();
        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeCommandFile("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"));
        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeCommandLine("cmd /c \"chrome.exe\""));
        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeContinueInstall("True"));
        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeRestartRequired("False"));
        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeReturnCodeRestart("1010"));
        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeReturnCodeSuccess("0"));

        CommandConfigType commandConfigType2 = windowsCustomizationsObjectFactory.createCommandConfigType();
        commandConfigType2.setName("InstallShellLauncher");
        content = commandConfigType2.getContent();
        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeCommandLine("dism /online /enable-feature /FeatureName:Client-EmbeddedShellLauncher /all"));

        CommandType commandType = windowsCustomizationsObjectFactory.createCommandType();
        commandType.setCommandConfig(List.of(commandConfigType1, commandConfigType2));


        PrimaryContextType primaryContextType = windowsCustomizationsObjectFactory.createPrimaryContextType();
        primaryContextType.setCommand(commandType);

        ProvisioningCommandsType provisioningCommandsType = windowsCustomizationsObjectFactory.createProvisioningCommandsType();
        provisioningCommandsType.setPrimaryContext(primaryContextType);

        AutoLogonType autoLogonType = windowsCustomizationsObjectFactory.createAutoLogonType();
        autoLogonType.setEnable("ENABLE");
        autoLogonType.setUserName("kiosk");
        autoLogonType.setPassword("kiosk");

        LocalUserShellSettingType localUserShellSettingType1 = windowsCustomizationsObjectFactory.createLocalUserShellSettingType();
        localUserShellSettingType1.setUsername("LocalUserShellSetting");
        localUserShellSettingType1.setUserCustomShell("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        LocalUserShellSettingType localUserShellSettingType2 = windowsCustomizationsObjectFactory.createLocalUserShellSettingType();
        localUserShellSettingType2.setUsername("kiosk");
        localUserShellSettingType2.setUserCustomShell("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        LocalUserSpecificSettingsType localUserSpecificSettingsType = windowsCustomizationsObjectFactory.createLocalUserSpecificSettingsType();
        localUserSpecificSettingsType.setLocalUserShellSetting(List.of(localUserShellSettingType1, localUserShellSettingType2));

        UserSpecificSettingsType userSpecificSettingsType = windowsCustomizationsObjectFactory.createUserSpecificSettingsType();
        userSpecificSettingsType.setLocalUserSpecificSettings(localUserSpecificSettingsType);

        ShellLauncherType shellLauncherType = windowsCustomizationsObjectFactory.createShellLauncherType();
        shellLauncherType.setEnable("ENABLE");
        shellLauncherType.setUserSpecificSettings(userSpecificSettingsType);

        SMISettingsType smiSettingsType = windowsCustomizationsObjectFactory.createSMISettingsType();
        smiSettingsType.setAutoLogon(autoLogonType);
        smiSettingsType.setHideAutologonUI("False");
        smiSettingsType.setShellLauncher(shellLauncherType);

        CommonType commonType = windowsCustomizationsObjectFactory.createCommonType();
        commonType.setProvisioningCommands(provisioningCommandsType);
        commonType.setAccounts(accountsType);
        commonType.setDevDetail(devDetailType);
        commonType.setOobe(oobeType);
        commonType.setPolicies(policiesType);
        commonType.setSmiSettings(smiSettingsType);

        CustomizationsType customizationsType = windowsCustomizationsObjectFactory.createCustomizationsType();
        customizationsType.setCommon(commonType);

        SettingsType settingsType = windowsCustomizationsObjectFactory.createSettingsType();
        settingsType.setCustomizations(customizationsType);

        KioskConfig kioskConfig = new KioskConfig();
        kioskConfig.setPackageConfig(packageConfigType);
        kioskConfig.setSettings(settingsType);

        JAXBContext jaxbContext = JAXBContext.newInstance(KioskConfig.class, PackageConfigObjectFactory.class, WindowsCustomizationsObjectFactory.class);
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
