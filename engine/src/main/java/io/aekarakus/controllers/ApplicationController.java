package io.aekarakus.controllers;

import io.aekarakus.domain.models.Application;
import io.aekarakus.domain.models.Device;
import io.aekarakus.services.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/applications")
@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/create")
    public ResponseEntity<Application> createApplication(@RequestBody Application application){

        Application createdApplication = applicationService.createApplication(application);

        return ResponseEntity.ok().body(createdApplication);

    }

    @DeleteMapping("/delete")
    public void deleteApplication(@RequestBody String chocolateyName){

        applicationService.deleteApplication(chocolateyName);
    }

    @GetMapping("/list")
    ResponseEntity<List<Application>> listApplications(){
        List<Application> applications = applicationService.listApplications();
        return ResponseEntity.ok().body(applications);
    }
}
