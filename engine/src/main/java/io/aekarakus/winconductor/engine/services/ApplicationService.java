package io.aekarakus.winconductor.engine.services;

import io.aekarakus.winconductor.engine.domain.models.Application;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplicationService {

    Application createApplication(Application application);
    void deleteApplication(String chocolateyName);
    List<Application> listApplications();
}
