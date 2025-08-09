package io.aekarakus.winconductor.engine.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.aekarakus.winconductor.engine.domain.models.Device;

public interface KioskService {

    void enforceKioskModeOnDevice(Long deviceId) throws JsonProcessingException;
}
