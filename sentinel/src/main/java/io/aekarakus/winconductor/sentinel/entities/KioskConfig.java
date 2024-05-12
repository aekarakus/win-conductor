package io.aekarakus.winconductor.sentinel.entities;

import io.aekarakus.winconductor.sentinel.entities.xmblocks.packageConfig.PackageConfigType;
import io.aekarakus.winconductor.sentinel.entities.xmblocks.windowsCustomizations.SettingsType;

import javax.xml.bind.annotation.*;

import lombok.Getter;
import lombok.Setter;


@XmlRootElement(name = "WindowsCustomizations")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "packageConfig",
        "settings"
})
public class KioskConfig {
    @XmlElement(name = "PackageConfig", required = true, namespace = "urn:schemas-Microsoft-com:Windows-ICD-Package-Config.v1.0")
    protected PackageConfigType packageConfig;
    @XmlElement(name = "Settings", required = true, namespace = "urn:schemas-microsoft-com:windows-provisioning")
    protected SettingsType settings;

    public PackageConfigType getPackageConfig() {
        return packageConfig;
    }

    public void setPackageConfig(PackageConfigType packageConfig) {
        this.packageConfig = packageConfig;
    }

    public SettingsType getSettings() {
        return settings;
    }

    public void setSettings(SettingsType settings) {
        this.settings = settings;
    }
}
