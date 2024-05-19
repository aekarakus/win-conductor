package io.aekarakus.winconductor.sentinel.services;

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
import java.io.StringWriter;
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
//        Command command = commandContext.getCommand();

        WinRmTool tool = WinRmTool.Builder.builder(device.getAddress(), device.getUserName(), device.getPassword())
                .authenticationScheme(AuthSchemes.NTLM)
                .port(5985)
                .context(context)
                .build();

        System.out.println(buildXMLConfig());

        String command = """
                New-Item -ItemType Directory -Path C:\\Users\\vagrant\\Desktop\\kiosk -Force
                Copy-Item -Path "C:\\Program Files (x86)\\Windows Kits\\10\\Assessment and Deployment Kit\\Imaging and Configuration Designer\\x86\\Microsoft-Desktop-Provisioning.dat" -Destination C:\\Users\\vagrant\\Desktop\\kiosk\\Microsoft-Desktop-Provisioning.dat
                $xml = @"
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <WindowsCustomizations xmlns:ns2="urn:schemas-microsoft-com:windows-provisioning" xmlns:ns3="urn:schemas-Microsoft-com:Windows-ICD-Package-Config.v1.0">
                    <ns3:PackageConfig>
                        <ns3:ID>{7bf5c3f8-79f7-468e-bc9d-aa4be0f66ecf}</ns3:ID>
                        <ns3:Name>Project_1731293055</ns3:Name>
                        <ns3:Version>1.1</ns3:Version>
                        <ns3:OwnerType>OEM</ns3:OwnerType>
                        <ns3:Rank>0</ns3:Rank>
                        <ns3:Notes></ns3:Notes>
                    </ns3:PackageConfig>
                    <ns2:Settings>
                        <ns2:Customizations>
                            <ns2:Common>
                                <ns2:Accounts>
                                    <ns2:Users>
                                        <ns2:User UserName="kiosk">
                                            <ns2:Password>kiosk</ns2:Password>
                                            <ns2:UserGroup>Standard Users</ns2:UserGroup>
                                        </ns2:User>
                                    </ns2:Users>
                                </ns2:Accounts>
                                <ns2:DevDetail>
                                    <ns2:DNSComputerName>kiosk-1</ns2:DNSComputerName>
                                </ns2:DevDetail>
                                <ns2:OOBE>
                                    <ns2:Desktop>
                                        <ns2:HideOobe>True</ns2:HideOobe>
                                    </ns2:Desktop>
                                </ns2:OOBE>
                                <ns2:Policies>
                                    <ns2:ApplicationManagement>
                                        <ns2:AllowAllTrustedApps>Yes</ns2:AllowAllTrustedApps>
                                    </ns2:ApplicationManagement>
                                </ns2:Policies>
                                <ns2:ProvisioningCommands>
                                    <ns2:PrimaryContext>
                                        <ns2:Command>
                                            <ns2:CommandConfig Name="chrome">
                                                <ns2:CommandFile>C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe</ns2:CommandFile>
                                                <ns2:CommandLine>cmd /c "chrome.exe"</ns2:CommandLine>
                                                <ns2:ContinueInstall>True</ns2:ContinueInstall>
                                                <ns2:RestartRequired>False</ns2:RestartRequired>
                                                <ns2:ReturnCodeRestart>1010</ns2:ReturnCodeRestart>
                                                <ns2:ReturnCodeSuccess>0</ns2:ReturnCodeSuccess>
                                            </ns2:CommandConfig>
                                            <ns2:CommandConfig Name="InstallShellLauncher">
                                                <ns2:CommandLine>dism /online /enable-feature /FeatureName:Client-EmbeddedShellLauncher /all</ns2:CommandLine>
                                            </ns2:CommandConfig>
                                        </ns2:Command>
                                    </ns2:PrimaryContext>
                                </ns2:ProvisioningCommands>
                                <ns2:SMISettings>
                                    <ns2:AutoLogon>
                                        <ns2:Enable>ENABLE</ns2:Enable>
                                        <ns2:Password>kiosk</ns2:Password>
                                        <ns2:UserName>kiosk</ns2:UserName>
                                    </ns2:AutoLogon>
                                    <ns2:HideAutologonUI>False</ns2:HideAutologonUI>
                                    <ns2:ShellLauncher>
                                        <ns2:Enable>ENABLE</ns2:Enable>
                                        <ns2:UserSpecificSettings>
                                            <ns2:LocalUserSpecificSettings>
                                                <ns2:LocalUserShellSetting Username="LocalUserShellSetting">
                                                    <ns2:UserCustomShell>C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe</ns2:UserCustomShell>
                                                </ns2:LocalUserShellSetting>
                                                <ns2:LocalUserShellSetting Username="kiosk">
                                                    <ns2:UserCustomShell>C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe</ns2:UserCustomShell>
                                                </ns2:LocalUserShellSetting>
                                            </ns2:LocalUserSpecificSettings>
                                        </ns2:UserSpecificSettings>
                                    </ns2:ShellLauncher>
                                </ns2:SMISettings>
                            </ns2:Common>
                        </ns2:Customizations>
                    </ns2:Settings>
                </WindowsCustomizations>
                "@
                $xml | Out-File C:\\Users\\vagrant\\Desktop\\kiosk\\customizations.xml -Encoding utf8 -Force
                $cmd="C:\\Program Files (x86)\\Windows Kits\\10\\Assessment and Deployment Kit\\Imaging and Configuration Designer\\x86\\ICD.exe"
                & $cmd /Build-ProvisioningPackage /PackagePath:C:\\Users\\vagrant\\Desktop\\kiosk\\kiosk /CustomizationXML:C:\\Users\\vagrant\\Desktop\\kiosk\\customizations.xml /StoreFile:'C:\\Users\\vagrant\\Desktop\\kiosk\\Microsoft-Desktop-Provisioning.dat'
                Add-ProvisioningPackage -Path C:\\Users\\vagrant\\Desktop\\kiosk\\kiosk\\kiosk.ppkg -QuietInstall
                """;

        System.out.println(command);

        WinRmToolResponse response = tool.executePs(command);

        System.out.println("Out:" + response.getStdOut());
        System.out.println("status:" + response.getStatusCode());
        System.out.println("Err:" + response.getStdErr());

        context.shutdown();
    }

    private static String buildXMLConfig() throws JAXBException {
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
        ComputerAccountType computerAccountType = windowsCustomizationsObjectFactory.createComputerAccountType();
        computerAccountType.setComputerName("kiosk-1");
        accountsType.setUsers(usersType);
        accountsType.setComputerAccount(computerAccountType);


        DesktopType desktopType = windowsCustomizationsObjectFactory.createDesktopType();
        desktopType.setHideOobe("True");

        OOBEType oobeType = windowsCustomizationsObjectFactory.createOOBEType();
        oobeType.setDesktop(desktopType);

        ApplicationManagementType applicationManagementType = windowsCustomizationsObjectFactory.createApplicationManagementType();
        applicationManagementType.setAllowAllTrustedApps("Yes");

        PoliciesType policiesType = windowsCustomizationsObjectFactory.createPoliciesType();
        policiesType.setApplicationManagement(applicationManagementType);

        CommandConfigType commandConfigType1 = windowsCustomizationsObjectFactory.createCommandConfigType();
        commandConfigType1.setName("chrome");
        commandConfigType1.setCommandFile("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        commandConfigType1.setCommandLine("cmd /c \"chrome.exe\"");
        commandConfigType1.setContinueInstall("True");
        commandConfigType1.setRestartRequired("False");
        commandConfigType1.setReturnCodeRestart("3010");
        commandConfigType1.setReturnCodeSuccess("0");
//        List<JAXBElement<String>> content = commandConfigType1.getContent();

//        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeCommandFile("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"));
//        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeCommandLine("cmd /c \"chrome.exe\""));
//        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeContinueInstall("True"));
//        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeRestartRequired("False"));
//        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeReturnCodeRestart("1010"));
//        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeReturnCodeSuccess("0"));

        CommandConfigType commandConfigType2 = windowsCustomizationsObjectFactory.createCommandConfigType();
        commandConfigType2.setName("InstallShellLauncher");
        commandConfigType2.setCommandLine("dism /online /enable-feature /FeatureName:Client-EmbeddedShellLauncher /all");
//        content = commandConfigType2.getContent();
//        content.add(windowsCustomizationsObjectFactory.createCommandConfigTypeCommandLine("dism /online /enable-feature /FeatureName:Client-EmbeddedShellLauncher /all"));

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

        StringWriter sw = new StringWriter();
        marshaller.marshal(kioskConfig, sw);
        return sw.toString();
//        marshaller.marshal(kioskConfig, new File("./test.xml"));

    }
}
