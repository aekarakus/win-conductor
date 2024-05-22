package io.aekarakus.winconductor.engine.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.aekarakus.winconductor.engine.domain.models.Device;
import io.aekarakus.winconductor.engine.domain.repositories.DeviceRepository;
import io.aekarakus.winconductor.engine.services.KioskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class KioskServiceImpl implements KioskService {

    private final DeviceRepository deviceRepository;

    @Override
    public void enforceKioskModeOnDevice(Long deviceId) throws JsonProcessingException {
        Device device = deviceRepository.findById(deviceId).orElseThrow();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();

        ObjectNode deviceNode = mapper.createObjectNode();
        deviceNode.put("address", device.getAddress());
        deviceNode.put("userName", "Vagrant");
        deviceNode.put("password", "vagrant");

        rootNode.set("device", deviceNode);

        ObjectNode commandNode = mapper.createObjectNode();
        commandNode.put("content", "echo hi");

        rootNode.set("command", commandNode);

        ObjectNode applicationNode = mapper.createObjectNode();
        commandNode.put("application", "echo hi");
        applicationNode.put("id", "1L");
        applicationNode.put("name", "chrome");
        applicationNode.put("chocolateyName", "chrome");
        applicationNode.put("path", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        
        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:5001/kiosk", entity, String.class);
    }
}
